package org.sandboxpowered.silica.vanilla.network.ecs.system

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.annotations.Wire
import com.artemis.systems.IteratingSystem
import org.joml.Vector3d
import org.sandboxpowered.silica.api.block.Block
import org.sandboxpowered.silica.api.ecs.component.PlayerInventoryComponent
import org.sandboxpowered.silica.api.ecs.component.PositionComponent
import org.sandboxpowered.silica.api.ecs.component.RotationComponent
import org.sandboxpowered.silica.api.entity.EntityEvents
import org.sandboxpowered.silica.api.entity.InteractionContext
import org.sandboxpowered.silica.api.item.BlockItem
import org.sandboxpowered.silica.api.item.ItemStack
import org.sandboxpowered.silica.api.server.Server
import org.sandboxpowered.silica.api.util.ActionResult
import org.sandboxpowered.silica.api.util.extensions.component1
import org.sandboxpowered.silica.api.util.extensions.component2
import org.sandboxpowered.silica.api.util.extensions.component3
import org.sandboxpowered.silica.api.util.math.ChunkPosition
import org.sandboxpowered.silica.api.util.math.Position
import org.sandboxpowered.silica.api.world.World
import org.sandboxpowered.silica.vanilla.network.VanillaNetworkAdapter
import org.sandboxpowered.silica.vanilla.network.ecs.VanillaEntityContext
import org.sandboxpowered.silica.vanilla.network.ecs.component.VanillaPlayerInputComponent
import org.sandboxpowered.silica.vanilla.network.packets.PacketPlay
import org.sandboxpowered.silica.vanilla.network.packets.play.clientbound.S2CUpdateEntityHeadRotation
import org.sandboxpowered.silica.vanilla.network.packets.play.clientbound.S2CUpdateEntityRotation
import org.sandboxpowered.silica.vanilla.network.packets.play.serverbound.C2SPlayerDigging

@All(VanillaPlayerInputComponent::class, PositionComponent::class, RotationComponent::class)
class VanillaInputSystem(val server: Server) : IteratingSystem() {

    @Wire
    private lateinit var positionMapper: ComponentMapper<PositionComponent>

    @Wire
    private lateinit var playerInputMapper: ComponentMapper<VanillaPlayerInputComponent>

    @Wire
    private lateinit var rotationMapper: ComponentMapper<RotationComponent>

    @Wire
    private lateinit var inventoryMapper: ComponentMapper<PlayerInventoryComponent>

    @Wire
    private lateinit var terrain: World

    override fun process(entityId: Int) {
        // TODO: check if in range
        val input = playerInputMapper[entityId]
        val location = input.wantedPosition
        val yaw = input.wantedYaw % 360.0f
        val pitch = input.wantedPitch
        val previousLocation = positionMapper[entityId].pos
        val rot = rotationMapper[entityId]
        val (previousYaw, previousPitch) = rot

        val hasMoved = location != previousLocation
        val hasRotated = yaw != previousYaw || pitch == previousPitch

        val (x, y, z) = location

        var dx: Double = x * 32 - previousLocation.x * 32
        var dy: Double = y * 32 - previousLocation.y * 32
        var dz: Double = z * 32 - previousLocation.z * 32

        dx *= 128
        dy *= 128
        dz *= 128

        var packet: PacketPlay? = null

        if (hasRotated) {
            // move packet handled via events fired from Entity3dMap
            packet = S2CUpdateEntityRotation(entityId, yaw, pitch, false)
            rot.yaw = yaw
            rot.pitch = pitch
        }

        if (yaw != previousYaw) {
            server.network.tell(
                VanillaNetworkAdapter.VanillaCommand.SendToAllExcept(
                    input.gameProfile.id,
                    S2CUpdateEntityHeadRotation(entityId, yaw)
                )
            )
        }

        if (packet != null) {
            server.network.tell(
                VanillaNetworkAdapter.VanillaCommand.SendToAllExcept(
                    input.gameProfile.id,
                    packet
                )
            )
        }
        if (hasMoved) {
            val previousChunk = ChunkPosition(previousLocation)
            val currentChunk = ChunkPosition(location)
            if (previousChunk != currentChunk) {
                EntityEvents.CHANGE_CHUNK_EVENT.dispatcher?.invoke(entityId, previousChunk, currentChunk)
            }
        }

        previousLocation.set(location)

        this.handleTerrainInteraction(entityId, input, previousLocation, rot)
    }

    private fun handleTerrainInteraction(
        entityId: Int,
        input: VanillaPlayerInputComponent,
        position: Vector3d,
        rot: RotationComponent
    ) {
        input.interacting = performWithRangedCheck(input.interacting, position) {
            val ctx = VanillaEntityContext(input, position, rot)
            val heldItem = inventoryMapper[entityId]?.inventory?.mainHandStack ?: ItemStack.EMPTY
            val state = terrain.getBlockState(it.location)
            val block = state.block
            val result = block.onUse(terrain, it.location, state, it.hand, it.face, it.cursor, ctx)
            if (result == ActionResult.PASS) {
                val newLoc = it.location.shift(it.face)
                if (heldItem.isNotEmpty) {
                    val item = heldItem.item
                    if (item is BlockItem)
                        terrain.setBlockState(newLoc, item.block.getStateForPlacement(terrain, newLoc, it, ctx))
                }
            }
        }

        input.breaking = performWithRangedCheck(input.breaking, position) {
            val newState = Block.AIR.defaultState
            val success = terrain.setBlockState(it.location, newState)
            it.acknowldge(success, newState)
        }
    }

    private inline fun performWithRangedCheck(
        target: Position?,
        position: Vector3d,
        perform: (Position) -> Unit
    ): Position? {
        if (target != null && position.distanceSquared(target.x + .5, target.y + .5, target.z + .5) < 20) {
            perform(target)
        }
        return null
    }

    private inline fun performWithRangedCheck(
        target: InteractionContext?,
        position: Vector3d,
        perform: (InteractionContext) -> Unit
    ): InteractionContext? {
        if (target != null && position.distanceSquared(
                target.location.x + .5,
                target.location.y + .5,
                target.location.z + .5
            ) < 20
        ) {
            perform(target)
        }
        return null
    }

    private inline fun performWithRangedCheck(
        target: C2SPlayerDigging.PlayerDigging?,
        position: Vector3d,
        perform: (C2SPlayerDigging.PlayerDigging) -> Unit
    ): C2SPlayerDigging.PlayerDigging? {
        if (target != null && position.distanceSquared(
                target.location.x + .5,
                target.location.y + .5,
                target.location.z + .5
            ) < 20
        ) {
            perform(target)
        }
        return null
    }
}