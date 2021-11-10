package org.sandboxpowered.silica.vanilla.network

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.buffer.ByteBufInputStream
import io.netty.buffer.ByteBufOutputStream
import io.netty.handler.codec.DecoderException
import io.netty.handler.codec.EncoderException
import io.netty.util.ByteProcessor
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import org.sandboxpowered.silica.nbt.NBTCompound
import org.sandboxpowered.silica.nbt.readNbt
import org.sandboxpowered.silica.nbt.write
import org.sandboxpowered.silica.util.Identifier
import org.sandboxpowered.silica.util.math.Position
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.Integer.min
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.nio.channels.GatheringByteChannel
import java.nio.channels.ScatteringByteChannel
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.experimental.and

class PacketByteBuf(private val source: ByteBuf) : ByteBuf() {
    override fun capacity(): Int = source.capacity()

    override fun capacity(i: Int): PacketByteBuf {
        source.capacity(i)
        return this
    }

    override fun maxCapacity(): Int = source.maxCapacity()

    override fun alloc(): ByteBufAllocator = source.alloc()

    @Deprecated("Deprecated in interface")
    @Suppress("DEPRECATION")
    override fun order(): ByteOrder = source.order()

    @Deprecated("Deprecated in interface")
    @Suppress("DEPRECATION")
    override fun order(byteOrder: ByteOrder): PacketByteBuf {
        source.order(byteOrder)
        return this
    }

    override fun unwrap(): ByteBuf = source

    override fun isDirect(): Boolean = source.isDirect

    override fun isReadOnly(): Boolean = source.isReadOnly

    override fun asReadOnly(): PacketByteBuf {
        return PacketByteBuf(source.asReadOnly())
    }

    override fun readerIndex(): Int = source.readerIndex()

    override fun readerIndex(i: Int): PacketByteBuf {
        source.readerIndex(i)
        return this
    }

    override fun writerIndex(): Int = source.writerIndex()

    override fun writerIndex(i: Int): PacketByteBuf {
        source.writerIndex(i)
        return this
    }

    override fun setIndex(i: Int, j: Int): PacketByteBuf {
        source.setIndex(i, j)
        return this
    }

    override fun readableBytes(): Int = source.readableBytes()

    override fun writableBytes(): Int = source.writableBytes()

    override fun maxWritableBytes(): Int = source.maxWritableBytes()

    override fun isReadable(): Boolean = source.isReadable

    override fun isReadable(i: Int): Boolean = source.isReadable(i)

    override fun isWritable(): Boolean = source.isWritable

    override fun isWritable(i: Int): Boolean = source.isWritable(i)

    override fun clear(): PacketByteBuf {
        source.clear()
        return this
    }

    override fun markReaderIndex(): PacketByteBuf {
        source.markReaderIndex()
        return this
    }

    override fun resetReaderIndex(): PacketByteBuf {
        source.resetReaderIndex()
        return this
    }

    override fun markWriterIndex(): PacketByteBuf {
        source.markWriterIndex()
        return this
    }

    override fun resetWriterIndex(): PacketByteBuf {
        source.resetWriterIndex()
        return this
    }

    override fun discardReadBytes(): PacketByteBuf {
        source.discardReadBytes()
        return this
    }

    override fun discardSomeReadBytes(): PacketByteBuf {
        source.discardSomeReadBytes()
        return this
    }

    override fun ensureWritable(i: Int): PacketByteBuf {
        source.ensureWritable(i)
        return this
    }

    override fun ensureWritable(i: Int, bl: Boolean): Int = source.ensureWritable(i, bl)

    override fun getBoolean(i: Int): Boolean = source.getBoolean(i)

    override fun getByte(i: Int): Byte = source.getByte(i)

    override fun getUnsignedByte(i: Int): Short = source.getUnsignedByte(i)

    override fun getShort(i: Int): Short = source.getShort(i)

    override fun getShortLE(i: Int): Short = source.getShortLE(i)

    override fun getUnsignedShort(i: Int): Int = source.getUnsignedShort(i)

    override fun getUnsignedShortLE(i: Int): Int = source.getUnsignedShortLE(i)

    override fun getMedium(i: Int): Int = source.getMedium(i)

    override fun getMediumLE(i: Int): Int = source.getMediumLE(i)

    override fun getUnsignedMedium(i: Int): Int = source.getUnsignedMedium(i)

    override fun getUnsignedMediumLE(i: Int): Int = source.getUnsignedMediumLE(i)

    override fun getInt(i: Int): Int = source.getInt(i)

    override fun getIntLE(i: Int): Int = source.getIntLE(i)

    override fun getUnsignedInt(i: Int): Long = source.getUnsignedInt(i)

    override fun getUnsignedIntLE(i: Int): Long = source.getUnsignedIntLE(i)

    override fun getLong(i: Int): Long = source.getLong(i)

    override fun getLongLE(i: Int): Long = source.getLongLE(i)

    override fun getChar(i: Int): Char = source.getChar(i)

    override fun getFloat(i: Int): Float = source.getFloat(i)

    override fun getDouble(i: Int): Double = source.getDouble(i)

    override fun getBytes(i: Int, byteBuf: ByteBuf): PacketByteBuf {
        source.getBytes(i, byteBuf)
        return this
    }

    override fun getBytes(i: Int, byteBuf: ByteBuf, j: Int): PacketByteBuf {
        source.getBytes(i, byteBuf, j)
        return this
    }

    override fun getBytes(i: Int, byteBuf: ByteBuf, j: Int, k: Int): PacketByteBuf {
        source.getBytes(i, byteBuf, j, k)
        return this
    }

    override fun getBytes(i: Int, bs: ByteArray): PacketByteBuf {
        source.getBytes(i, bs)
        return this
    }

    override fun getBytes(i: Int, bs: ByteArray, j: Int, k: Int): PacketByteBuf {
        source.getBytes(i, bs, j, k)
        return this
    }

    override fun getBytes(i: Int, byteBuffer: ByteBuffer): PacketByteBuf {
        source.getBytes(i, byteBuffer)
        return this
    }

    @Throws(IOException::class)
    override fun getBytes(i: Int, outputStream: OutputStream, j: Int): PacketByteBuf {
        source.getBytes(i, outputStream, j)
        return this
    }

    @Throws(IOException::class)
    override fun getBytes(i: Int, bc: GatheringByteChannel, j: Int): Int = source.getBytes(i, bc, j)

    @Throws(IOException::class)
    override fun getBytes(i: Int, fc: FileChannel, l: Long, j: Int): Int = source.getBytes(i, fc, l, j)

    override fun getCharSequence(i: Int, j: Int, charset: Charset): CharSequence = source.getCharSequence(i, j, charset)

    override fun setBoolean(i: Int, bl: Boolean): PacketByteBuf {
        source.setBoolean(i, bl)
        return this
    }

    override fun setByte(i: Int, j: Int): PacketByteBuf {
        source.setByte(i, j)
        return this
    }

    override fun setShort(i: Int, j: Int): PacketByteBuf {
        source.setShort(i, j)
        return this
    }

    override fun setShortLE(i: Int, j: Int): PacketByteBuf {
        source.setShortLE(i, j)
        return this
    }

    override fun setMedium(i: Int, j: Int): PacketByteBuf {
        source.setMedium(i, j)
        return this
    }

    override fun setMediumLE(i: Int, j: Int): PacketByteBuf {
        source.setMediumLE(i, j)
        return this
    }

    override fun setInt(i: Int, j: Int): PacketByteBuf {
        source.setInt(i, j)
        return this
    }

    override fun setIntLE(i: Int, j: Int): PacketByteBuf {
        source.setIntLE(i, j)
        return this
    }

    override fun setLong(i: Int, l: Long): PacketByteBuf {
        source.setLong(i, l)
        return this
    }

    override fun setLongLE(i: Int, l: Long): PacketByteBuf {
        source.setLongLE(i, l)
        return this
    }

    override fun setChar(i: Int, j: Int): PacketByteBuf {
        source.setChar(i, j)
        return this
    }

    override fun setFloat(i: Int, f: Float): PacketByteBuf {
        source.setFloat(i, f)
        return this
    }

    override fun setDouble(i: Int, d: Double): PacketByteBuf {
        source.setDouble(i, d)
        return this
    }

    override fun setBytes(i: Int, byteBuf: ByteBuf): PacketByteBuf {
        source.setBytes(i, byteBuf)
        return this
    }

    override fun setBytes(i: Int, byteBuf: ByteBuf, j: Int): PacketByteBuf {
        source.setBytes(i, byteBuf, j)
        return this
    }

    override fun setBytes(i: Int, byteBuf: ByteBuf, j: Int, k: Int): PacketByteBuf {
        source.setBytes(i, byteBuf, j, k)
        return this
    }

    override fun setBytes(i: Int, bs: ByteArray): PacketByteBuf {
        source.setBytes(i, bs)
        return this
    }

    override fun setBytes(i: Int, bs: ByteArray, j: Int, k: Int): PacketByteBuf {
        source.setBytes(i, bs, j, k)
        return this
    }

    override fun setBytes(i: Int, byteBuffer: ByteBuffer): PacketByteBuf {
        source.setBytes(i, byteBuffer)
        return this
    }

    @Throws(IOException::class)
    override fun setBytes(i: Int, inputStream: InputStream, j: Int): Int = source.setBytes(i, inputStream, j)

    @Throws(IOException::class)
    override fun setBytes(i: Int, sbc: ScatteringByteChannel, j: Int): Int = source.setBytes(i, sbc, j)

    @Throws(IOException::class)
    override fun setBytes(i: Int, fc: FileChannel, l: Long, j: Int): Int = source.setBytes(i, fc, l, j)

    override fun setZero(i: Int, j: Int): PacketByteBuf {
        source.setZero(i, j)
        return this
    }

    override fun setCharSequence(i: Int, charSequence: CharSequence, charset: Charset): Int =
        source.setCharSequence(i, charSequence, charset)

    override fun readBoolean(): Boolean = source.readBoolean()

    override fun readByte(): Byte = source.readByte()

    override fun readUnsignedByte(): Short = source.readUnsignedByte()

    override fun readShort(): Short = source.readShort()

    override fun readShortLE(): Short = source.readShortLE()

    override fun readUnsignedShort(): Int = source.readUnsignedShort()

    fun readUByte(): UByte = readUnsignedByte().toUByte()

    fun writeUByte(b: UByte): PacketByteBuf {
        writeByte(b.toInt())
        return this
    }

    fun readUShort(): UShort = readUnsignedShort().toUShort()

    fun writeUShort(s: UShort): PacketByteBuf {
        writeShort(s.toInt())
        return this
    }

    fun readUInt(): UInt = readVarInt().toUInt()

    fun writeUInt(i: UInt): PacketByteBuf {
        writeVarInt(i.toInt())
        return this
    }

    fun readULong(): ULong = readVarLong().toULong()

    fun writeULong(l: ULong): PacketByteBuf {
        writeVarLong(l.toLong())
        return this
    }

    override fun readUnsignedShortLE(): Int = source.readUnsignedShortLE()

    override fun readMedium(): Int = source.readMedium()

    override fun readMediumLE(): Int = source.readMediumLE()

    override fun readUnsignedMedium(): Int = source.readUnsignedMedium()

    override fun readUnsignedMediumLE(): Int = source.readUnsignedMediumLE()

    override fun readInt(): Int = source.readInt()

    override fun readIntLE(): Int = source.readIntLE()

    override fun readUnsignedInt(): Long = source.readUnsignedInt()

    override fun readUnsignedIntLE(): Long = source.readUnsignedIntLE()

    override fun readLong(): Long = source.readLong()

    override fun readLongLE(): Long = source.readLongLE()

    override fun readChar(): Char = source.readChar()

    override fun readFloat(): Float = source.readFloat()

    override fun readDouble(): Double = source.readDouble()

    override fun readBytes(i: Int): PacketByteBuf = PacketByteBuf(source.readBytes(min(readableBytes(), i)))

    override fun readSlice(i: Int): PacketByteBuf = PacketByteBuf(source.readSlice(i))

    override fun readRetainedSlice(i: Int): PacketByteBuf = PacketByteBuf(source.readRetainedSlice(i))

    override fun readBytes(byteBuf: ByteBuf): PacketByteBuf {
        source.readBytes(byteBuf)
        return this
    }

    override fun readBytes(byteBuf: ByteBuf, i: Int): PacketByteBuf {
        source.readBytes(byteBuf, i)
        return this
    }

    override fun readBytes(byteBuf: ByteBuf, i: Int, j: Int): PacketByteBuf {
        source.readBytes(byteBuf, i, j)
        return this
    }

    override fun readBytes(bs: ByteArray): PacketByteBuf {
        source.readBytes(bs)
        return this
    }

    override fun readBytes(bs: ByteArray, i: Int, j: Int): PacketByteBuf {
        source.readBytes(bs, i, j)
        return this
    }

    override fun readBytes(byteBuffer: ByteBuffer): PacketByteBuf {
        source.readBytes(byteBuffer)
        return this
    }

    @Throws(IOException::class)
    override fun readBytes(outputStream: OutputStream, i: Int): PacketByteBuf {
        source.readBytes(outputStream, i)
        return this
    }

    @Throws(IOException::class)
    override fun readBytes(gatheringByteChannel: GatheringByteChannel, i: Int): Int =
        source.readBytes(gatheringByteChannel, i)

    override fun readCharSequence(i: Int, charset: Charset): CharSequence = source.readCharSequence(i, charset)

    @Throws(IOException::class)
    override fun readBytes(fileChannel: FileChannel, l: Long, i: Int): Int = source.readBytes(fileChannel, l, i)

    override fun skipBytes(i: Int): PacketByteBuf {
        source.skipBytes(i)
        return this
    }

    override fun writeBoolean(bl: Boolean): PacketByteBuf {
        source.writeBoolean(bl)
        return this
    }

    override fun writeByte(i: Int): PacketByteBuf {
        source.writeByte(i)
        return this
    }

    fun writeByte(b: Byte): PacketByteBuf {
        source.writeByte(b.toInt())
        return this
    }

    override fun writeShort(i: Int): PacketByteBuf {
        source.writeShort(i)
        return this
    }

    fun writeShort(s: Short): PacketByteBuf {
        source.writeShort(s.toInt())
        return this
    }

    override fun writeShortLE(i: Int): PacketByteBuf {
        source.writeShortLE(i)
        return this
    }

    override fun writeMedium(i: Int): PacketByteBuf {
        source.writeMedium(i)
        return this
    }

    override fun writeMediumLE(i: Int): PacketByteBuf {
        source.writeMediumLE(i)
        return this
    }

    override fun writeInt(i: Int): PacketByteBuf {
        source.writeInt(i)
        return this
    }

    override fun writeIntLE(i: Int): PacketByteBuf {
        source.writeIntLE(i)
        return this
    }

    override fun writeLong(l: Long): PacketByteBuf {
        source.writeLong(l)
        return this
    }

    override fun writeLongLE(l: Long): PacketByteBuf {
        source.writeLongLE(l)
        return this
    }

    override fun writeChar(i: Int): PacketByteBuf {
        source.writeChar(i)
        return this
    }

    override fun writeFloat(f: Float): PacketByteBuf {
        source.writeFloat(f)
        return this
    }

    override fun writeDouble(d: Double): PacketByteBuf {
        source.writeDouble(d)
        return this
    }

    override fun writeBytes(byteBuf: ByteBuf): PacketByteBuf {
        source.writeBytes(byteBuf)
        return this
    }

    override fun writeBytes(byteBuf: ByteBuf, i: Int): PacketByteBuf {
        source.writeBytes(byteBuf, i)
        return this
    }

    override fun writeBytes(byteBuf: ByteBuf, i: Int, j: Int): PacketByteBuf {
        source.writeBytes(byteBuf, i, j)
        return this
    }

    override fun writeBytes(bs: ByteArray): PacketByteBuf {
        source.writeBytes(bs)
        return this
    }

    override fun writeBytes(bs: ByteArray, i: Int, j: Int): PacketByteBuf {
        source.writeBytes(bs, i, j)
        return this
    }

    override fun writeBytes(byteBuffer: ByteBuffer): PacketByteBuf {
        source.writeBytes(byteBuffer)
        return this
    }

    @Throws(IOException::class)
    override fun writeBytes(inputStream: InputStream, i: Int): Int = source.writeBytes(inputStream, i)

    @Throws(IOException::class)
    override fun writeBytes(bc: ScatteringByteChannel, i: Int): Int = source.writeBytes(bc, i)

    @Throws(IOException::class)
    override fun writeBytes(fc: FileChannel, l: Long, i: Int): Int = source.writeBytes(fc, l, i)

    override fun writeZero(i: Int): PacketByteBuf {
        source.writeZero(i)
        return this
    }

    override fun writeCharSequence(cs: CharSequence, charset: Charset): Int = source.writeCharSequence(cs, charset)

    override fun indexOf(i: Int, j: Int, b: Byte): Int = source.indexOf(i, j, b)

    override fun bytesBefore(b: Byte): Int = source.bytesBefore(b)

    override fun bytesBefore(i: Int, b: Byte): Int = source.bytesBefore(i, b)

    override fun bytesBefore(i: Int, j: Int, b: Byte): Int = source.bytesBefore(i, j, b)

    override fun forEachByte(byteProcessor: ByteProcessor): Int = source.forEachByte(byteProcessor)

    override fun forEachByte(i: Int, j: Int, bp: ByteProcessor): Int = source.forEachByte(i, j, bp)

    override fun forEachByteDesc(bp: ByteProcessor): Int = source.forEachByteDesc(bp)

    override fun forEachByteDesc(i: Int, j: Int, bp: ByteProcessor): Int = source.forEachByteDesc(i, j, bp)

    override fun copy(): PacketByteBuf = PacketByteBuf(source.copy())

    override fun copy(i: Int, j: Int): PacketByteBuf = PacketByteBuf(source.copy(i, j))

    override fun slice(): PacketByteBuf = PacketByteBuf(source.slice())

    override fun retainedSlice(): PacketByteBuf = PacketByteBuf(source.retainedSlice())

    override fun slice(i: Int, j: Int): PacketByteBuf = PacketByteBuf(source.slice(i, j))

    override fun retainedSlice(i: Int, j: Int): PacketByteBuf = PacketByteBuf(source.retainedSlice(i, j))

    override fun duplicate(): PacketByteBuf = PacketByteBuf(source.duplicate())

    override fun retainedDuplicate(): PacketByteBuf = PacketByteBuf(source.retainedDuplicate())

    override fun nioBufferCount(): Int = source.nioBufferCount()

    override fun nioBuffer(): ByteBuffer = source.nioBuffer()

    override fun nioBuffer(i: Int, j: Int): ByteBuffer = source.nioBuffer(i, j)

    override fun internalNioBuffer(i: Int, j: Int): ByteBuffer = source.internalNioBuffer(i, j)

    override fun nioBuffers(): Array<ByteBuffer> = source.nioBuffers()

    override fun nioBuffers(i: Int, j: Int): Array<ByteBuffer> = source.nioBuffers(i, j)

    override fun hasArray(): Boolean = source.hasArray()

    override fun array(): ByteArray = source.array()

    override fun arrayOffset(): Int = source.arrayOffset()

    override fun hasMemoryAddress(): Boolean = source.hasMemoryAddress()

    override fun memoryAddress(): Long = source.memoryAddress()

    override fun toString(charset: Charset): String = source.toString(charset)

    override fun toString(i: Int, j: Int, charset: Charset): String = source.toString(i, j, charset)

    override fun hashCode(): Int = source.hashCode()

    override fun equals(other: Any?): Boolean = source == other

    override fun compareTo(other: ByteBuf): Int = source.compareTo(other)

    override fun toString(): String = source.toString()

    override fun retain(i: Int): PacketByteBuf = PacketByteBuf(source.retain(i))

    override fun retain(): PacketByteBuf = PacketByteBuf(source.retain())

    override fun touch(): PacketByteBuf {
        source.touch()
        return this
    }

    override fun touch(o: Any): PacketByteBuf {
        source.touch(o)
        return this
    }

    override fun refCnt(): Int = source.refCnt()

    override fun release(): Boolean = source.release()

    override fun release(i: Int): Boolean = source.release(i)

    fun readVarInt(): Int {
        var i = 0
        var j = 0

        var b: Byte
        do {
            b = readByte()
            i = i or ((b and 127).toInt() shl j++ * 7)
            if (j > 5) {
                throw RuntimeException("VarInt too big")
            }
        } while (b.toInt() and 128 == 128)

        return i
    }

    fun readVarLong(): Long {
        var l = 0L
        var i = 0
        var b: Int
        do {
            b = readByte().toInt()
            l = l or ((b and 127).toLong() shl i++ * 7)
            if (i > 10) {
                throw RuntimeException("VarLong too big")
            }
        } while (b and 128 == 128)
        return l
    }

    @JvmOverloads
    fun readString(maxSize: Int = 32767): String {
        val j = readVarInt()
        return if (j > maxSize * 4) {
            throw DecoderException("The received encoded string buffer length is longer than maximum allowed (" + j + " > " + maxSize * 4 + ")")
        } else if (j < 0) {
            throw DecoderException("The received encoded string buffer length is less than zero! Weird string!")
        } else {
            val string = this.toString(this.readerIndex(), j, StandardCharsets.UTF_8)
            this.readerIndex(this.readerIndex() + j)
            if (string.length > maxSize) {
                throw DecoderException("The received string length is longer than maximum allowed ($j > $maxSize)")
            } else {
                string
            }
        }
    }

    fun readOptionalString(maxSize: Int): String? = if (readBoolean()) readString(maxSize) else null

    fun writeVarInt(i: Int): PacketByteBuf {
        var ii = i
        while (ii and -128 != 0) {
            writeByte(ii and 127 or 128)
            ii = ii ushr 7
        }
        writeByte(ii)
        return this
    }

    // hard to "guess" max size with var ints
    fun readVarIntArray(maxSize: Int = -1): IntArray {
        val length = readVarInt()
        if (maxSize in 1 until length) throw DecoderException("IntArray with size $length is bigger than allowed $maxSize")
        return IntArray(length) { readVarInt() }
    }

    fun writeVarIntArray(array: IntArray): PacketByteBuf {
        writeVarInt(array.size)
        array.forEach(this::writeVarInt)
        return this
    }

    fun readLongArray(maxSize: Int = -1): LongArray {
        val length = readVarInt()
        if (maxSize in 1 until length) throw DecoderException("LongArray with size $length is bigger than allowed $maxSize")
        return LongArray(length) { readLong() }
    }

    fun writeLongArray(array: LongArray): PacketByteBuf {
        writeVarInt(array.size)
        array.forEach(this::writeLong)
        return this
    }

    @JvmOverloads
    fun writeString(string: String, maxLength: Int = 32767): PacketByteBuf {
        val bs = string.toByteArray(StandardCharsets.UTF_8)
        return if (bs.size > maxLength) {
            throw EncoderException("String too big (was " + bs.size + " bytes encoded, max " + maxLength + ")")
        } else {
            writeVarInt(bs.size)
            this.writeBytes(bs)
            this
        }
    }

    @JvmOverloads
    @JvmName("writeOptionalString")
    fun writeString(string: String?, maxLength: Int = 32767): PacketByteBuf {
        writeBoolean(string !== null)
        string?.let { this.writeString(it, maxLength) }
        return this
    }

    @JvmOverloads
    fun readByteArray(maxSize: Int = readableBytes()): ByteArray {
        val length = readVarInt()
        return if (length > maxSize) {
            throw DecoderException("ByteArray with size $length is bigger than allowed $maxSize")
        } else {
            val bs = ByteArray(length)
            this.readBytes(bs)
            bs
        }
    }

    fun writeByteArray(array: ByteArray): PacketByteBuf {
        writeVarInt(array.size)
        this.writeBytes(array)
        return this
    }

    fun readUUID(): UUID = UUID(readLong(), readLong())

    fun writeUUID(uuid: UUID): PacketByteBuf {
        writeLong(uuid.mostSignificantBits)
        writeLong(uuid.leastSignificantBits)
        return this
    }

    fun readIdentity(): Identifier = Identifier(readString())

    fun writeIdentity(identity: Identifier): PacketByteBuf {
        writeString(identity.toString())
        return this
    }

    fun readNBT(): NBTCompound? {
        val i = readerIndex()
        val b = readByte()
        return if (b.toInt() == 0) {
            null
        } else {
            readerIndex(i)
            val stream = ByteBufInputStream(this)
            stream.readNbt()
        }
    }

    fun writeNBT(tag: NBTCompound?): PacketByteBuf {
        if (tag == null)
            writeByte(0)
        else {
            val out = ByteBufOutputStream(this)
            out.write(tag)
        }
        return this
    }

    fun writeVarLong(i: Long): PacketByteBuf {
        var l = i
        while (l and -128L != 0L) {
            writeByte((l and 127L).toInt() or 128)
            l = l ushr 7
        }

        writeByte(l.toInt())
        return this
    }

    fun readPosition(): Position = Position.unpack(readLong())

    fun writePosition(pos: Position): PacketByteBuf {
        writeLong(pos.packed)
        return this
    }

    fun writeText(reason: Component): PacketByteBuf {
        writeString(GsonComponentSerializer.gson().serialize(reason))
        return this
    }

    fun readText(): Component {
        return GsonComponentSerializer.gson().deserialize(readString())
    }

    inline fun <T> readCollection(maxSize: Int, transform: (PacketByteBuf) -> T): Collection<T> {
        val size = readVarInt()
        require(maxSize == -1 || size <= maxSize) { "Read collection size was $size, expected max $maxSize" }
        return List(size) { transform(this) }
    }

    inline fun <T> readCollection(transform: (PacketByteBuf) -> T): Collection<T> = readCollection(-1, transform)

    inline fun <T> writeCollection(
        collection: Collection<T>,
        transform: PacketByteBuf.(T) -> PacketByteBuf
    ): PacketByteBuf {
        writeVarInt(collection.size)
        return collection.fold(this, transform)
    }

    companion object {
        @JvmStatic
        fun getVarIntSize(i: Int): Int {
            for (j in 1..4) {
                if (i and -1 shl j * 7 == 0) {
                    return j
                }
            }
            return 5
        }

        @JvmStatic
        inline fun readVarInt(next: () -> Byte): Int {
            var i = 0
            var j = 0
            var b: Int
            do {
                b = next().toInt()
                i = i or ((b and 127) shl j++ * 7)
                if (j > 5) {
                    throw RuntimeException("VarInt too big")
                }
            } while ((b and 128) == 128)
            return i
        }
    }
}