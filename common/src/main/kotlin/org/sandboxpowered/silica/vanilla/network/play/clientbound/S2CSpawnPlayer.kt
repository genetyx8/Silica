package org.sandboxpowered.silica.vanilla.network.play.clientbound

import org.sandboxpowered.silica.vanilla.network.PacketByteBuf
import org.sandboxpowered.silica.vanilla.network.PacketHandler
import org.sandboxpowered.silica.vanilla.network.PacketPlay
import org.sandboxpowered.silica.vanilla.network.PlayContext
import java.util.*

class S2CSpawnPlayer(
    private val entityId: Int,
    private val uuid: UUID,
    private val x: Double,
    private val y: Double,
    private val z: Double,
    private val yaw: Byte,
    private val pitch: Byte
) : PacketPlay {
    constructor(buf: PacketByteBuf) : this(
        buf.readVarInt(),
        buf.readUUID(),
        buf.readDouble(),
        buf.readDouble(),
        buf.readDouble(),
        buf.readByte(),
        buf.readByte()
    )

    override fun write(buf: PacketByteBuf) {
        buf.writeVarInt(entityId)
        buf.writeUUID(uuid)
        buf.writeDouble(x)
        buf.writeDouble(y)
        buf.writeDouble(z)
        buf.writeByte(yaw)
        buf.writeByte(pitch)
    }

    override fun handle(packetHandler: PacketHandler, context: PlayContext) {
        TODO("Not yet implemented")
    }
}