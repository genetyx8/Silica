package org.sandboxpowered.silica.vanilla.network.packets.play.clientbound

import org.sandboxpowered.silica.api.network.PacketBuffer
import org.sandboxpowered.silica.vanilla.network.PacketHandler
import org.sandboxpowered.silica.vanilla.network.PlayContext
import org.sandboxpowered.silica.vanilla.network.packets.PacketPlay

class S2CUnlockRecipes() : PacketPlay {
    constructor(buf: PacketBuffer) : this()

    override fun write(buf: PacketBuffer) {
        buf.writeVarInt(0)
        buf.writeBoolean(false)
        buf.writeBoolean(false)
        buf.writeBoolean(false)
        buf.writeBoolean(false)
        buf.writeBoolean(false)
        buf.writeBoolean(false)
        buf.writeBoolean(false)
        buf.writeBoolean(false)
        buf.writeVarInt(0)
        buf.writeVarInt(0)
    }

    override fun handle(packetHandler: PacketHandler, context: PlayContext) {}
}