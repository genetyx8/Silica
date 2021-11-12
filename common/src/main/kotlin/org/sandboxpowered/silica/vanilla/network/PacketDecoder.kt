package org.sandboxpowered.silica.vanilla.network

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.sandboxpowered.silica.vanilla.network.play.serverbound.C2SKeepAliveServer
import org.sandboxpowered.silica.vanilla.network.play.serverbound.C2SPlayerPosition
import org.sandboxpowered.silica.vanilla.network.play.serverbound.C2SPlayerPositionAndRotation
import org.sandboxpowered.silica.vanilla.network.play.serverbound.C2SPlayerRotation

class PacketDecoder(private val networkFlow: NetworkFlow) : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, inBuf: ByteBuf, out: MutableList<Any>) {
        if (inBuf.readableBytes() != 0) {
            val buf = PacketByteBuf(inBuf)
            val packetId = buf.readVarInt()
            val protocol = ctx.channel().attr(Protocol.PROTOCOL_ATTRIBUTE_KEY).get()
            val packet = protocol.createPacket(networkFlow, packetId, buf)
            require(packet != null) { "Unknown packet 0x${"%02x".format(packetId)}" }
            if (packet !is C2SKeepAliveServer && packet !is C2SPlayerPosition && packet !is C2SPlayerPositionAndRotation && packet !is C2SPlayerRotation)
                println("Received [0x${"%02x".format(packetId)}] $packet")
            out.add(packet)
        }
    }
}