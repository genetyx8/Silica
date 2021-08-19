package org.sandboxpowered.silica.nbt

import org.sandboxpowered.silica.util.Identifier
import org.sandboxpowered.silica.util.math.Position
import java.util.*

interface NBTReadableCompound : NBT {
    val size: Int
    val keys: Collection<String>

    operator fun contains(key: String): Boolean
    fun getInt(key: String): Int
    fun getIntArray(key: String): IntArray
    fun getString(key: String): String
    fun getDouble(key: String): Double
    fun getByte(key: String): Byte
    fun getByteArray(key: String): ByteArray
    fun getLong(key: String): Long
    fun getBoolean(key: String): Boolean
    fun getUUID(key: String): UUID
    fun remove(key: String): Boolean
    fun getTag(key: String): NBT?
    fun <T> getList(key: String, tagType: Class<T>): List<T>
    fun getCompoundTag(key: String): NBTCompound
    fun getIdentifier(key: String): Identifier
    fun getPosition(key: String): Position
}