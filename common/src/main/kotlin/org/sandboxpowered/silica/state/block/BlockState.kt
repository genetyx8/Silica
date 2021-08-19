package org.sandboxpowered.silica.state.block

import com.google.common.collect.ImmutableMap
import org.sandboxpowered.silica.block.Block
import org.sandboxpowered.silica.state.BaseState
import org.sandboxpowered.silica.state.property.Property

class BlockState(base: Block, properties: ImmutableMap<Property<*>, Comparable<*>>) :
    BaseState<Block, BlockState>(base, properties) {
    val block: Block
        get() = base
}