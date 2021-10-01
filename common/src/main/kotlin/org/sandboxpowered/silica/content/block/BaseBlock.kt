package org.sandboxpowered.silica.content.block

import org.sandboxpowered.silica.content.item.Item
import org.sandboxpowered.silica.registry.Registry
import org.sandboxpowered.silica.registry.SilicaRegistries
import org.sandboxpowered.silica.util.Identifier
import org.sandboxpowered.silica.world.state.SilicaStateBuilder
import org.sandboxpowered.silica.world.state.SilicaStateFactory
import org.sandboxpowered.silica.world.state.StateProvider
import org.sandboxpowered.silica.world.state.block.BlockState

open class BaseBlock(final override val identifier: Identifier) : Block {
    override val isAir: Boolean
        get() = identifier.path == "air"

    override val item: Item? by SilicaRegistries.ITEM_REGISTRY[identifier].guaranteed

    protected open fun appendProperties(builder: StateProvider.Builder<Block, BlockState>) = Unit

    protected open fun createDefaultState(baseState: BlockState): BlockState = baseState

    override val stateProvider: StateProvider<Block, BlockState> by lazy {
        SilicaStateFactory(
            this,
            SilicaStateBuilder<Block, BlockState>(this).apply { appendProperties(this) }.getProperties(),
            BlockState.factory
        )
    }
    override val defaultState: BlockState by lazy { createDefaultState(stateProvider.baseState) }
    override val registry: Registry<Block>
        get() = SilicaRegistries.BLOCK_REGISTRY
}