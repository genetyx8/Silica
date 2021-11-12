package org.sandboxpowered.silica.api.world.generation

import org.sandboxpowered.silica.api.util.Identifier

interface WorldGenerator {
    val id: Identifier

    val minWorldWidth: Int
    val maxWorldWidth: Int
    val minWorldHeight: Int
    val maxWorldHeight: Int
}