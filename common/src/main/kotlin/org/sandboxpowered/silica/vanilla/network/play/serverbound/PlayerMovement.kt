package org.sandboxpowered.silica.vanilla.network.play.serverbound

import org.sandboxpowered.silica.vanilla.network.PacketByteBuf
import org.sandboxpowered.silica.vanilla.network.PacketHandler
import org.sandboxpowered.silica.vanilla.network.PacketPlay
import org.sandboxpowered.silica.vanilla.network.PlayContext

class PlayerMovement(private var onGround: Boolean) : PacketPlay {

    constructor(buf: PacketByteBuf) : this(buf.readBoolean())

    override fun write(buf: PacketByteBuf) {
        buf.writeBoolean(onGround)
    }

    override fun handle(packetHandler: PacketHandler, context: PlayContext) {}
}