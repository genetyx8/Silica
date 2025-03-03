package org.sandboxpowered.silica.api.registry

import org.sandboxpowered.silica.api.SilicaAPI
import org.sandboxpowered.silica.api.block.Block
import org.sandboxpowered.silica.api.entity.EntityDefinition
import org.sandboxpowered.silica.api.internal.getRegistry
import org.sandboxpowered.silica.api.item.Item

object Registries {
    val BLOCKS: Registry<Block> = SilicaAPI.getRegistry()
    val ITEMS: Registry<Item> = SilicaAPI.getRegistry()
    val ENTITY_DEFINITIONS: Registry<EntityDefinition> = SilicaAPI.getRegistry()
}