package org.sandboxpowered.silica.network.play.serverbound

import org.sandboxpowered.silica.network.PacketByteBuf
import org.sandboxpowered.silica.network.PacketHandler
import org.sandboxpowered.silica.network.PacketPlay
import org.sandboxpowered.silica.network.PlayContext

class KeepAliveServer(private var id: Long) : PacketPlay {

    constructor() : this(0)

    override fun read(buf: PacketByteBuf) {
        id = buf.readLong()
    }

    override fun write(buf: PacketByteBuf) {
        buf.writeLong(id)
    }

    override fun handle(packetHandler: PacketHandler, context: PlayContext) {
        packetHandler.connection.calculatePing(id)
    }
}