package org.sandboxpowered.silica.vanilla.network.play.serverbound

import org.sandboxpowered.silica.vanilla.network.PacketByteBuf
import org.sandboxpowered.silica.vanilla.network.PacketHandler
import org.sandboxpowered.silica.vanilla.network.PacketPlay
import org.sandboxpowered.silica.vanilla.network.PlayContext

class PlayerPosition(
    private var x: Double,
    private var y: Double,
    private var z: Double,
    private var onGround: Boolean,
) : PacketPlay {
    constructor(buf: PacketByteBuf) : this(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readBoolean())

    override fun write(buf: PacketByteBuf) {
        buf.writeDouble(x)
        buf.writeDouble(y)
        buf.writeDouble(z)
        buf.writeBoolean(onGround)
    }

    override fun handle(packetHandler: PacketHandler, context: PlayContext) {
        context.mutatePlayer { it.wantedPosition[x, y] = z }
    }
}