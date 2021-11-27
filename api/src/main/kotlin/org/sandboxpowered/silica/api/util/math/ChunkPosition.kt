package org.sandboxpowered.silica.api.util.math

import org.joml.Vector3d

data class ChunkPosition(
    val x: Int,
    val y: Int,
    val z: Int
) {
    companion object {
        operator fun invoke(position: Position): ChunkPosition = position.toChunkPosition()

        operator fun invoke(vector: Vector3d): ChunkPosition =
            ChunkPosition(vector.x.toInt() shr 4, vector.y.toInt() shr 4, vector.z.toInt() shr 4)
    }
}
