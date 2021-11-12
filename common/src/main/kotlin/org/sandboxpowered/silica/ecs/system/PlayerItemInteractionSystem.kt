package org.sandboxpowered.silica.ecs.system

import com.artemis.ComponentMapper
import com.artemis.annotations.All
import com.artemis.annotations.Wire
import com.artemis.systems.IteratingSystem
import org.sandboxpowered.silica.api.ecs.component.MarkForItemInteractionComponent
import org.sandboxpowered.silica.api.ecs.component.PlayerInventoryComponent
import org.sandboxpowered.silica.ecs.component.PlayerComponent

@All(PlayerComponent::class, PlayerInventoryComponent::class, MarkForItemInteractionComponent::class)
class PlayerItemInteractionSystem : IteratingSystem() {
    @Wire
    private lateinit var playerMapper: ComponentMapper<PlayerComponent>

    @Wire
    private lateinit var inventoryMapper: ComponentMapper<PlayerInventoryComponent>

    @Wire
    private lateinit var markerMapper: ComponentMapper<MarkForItemInteractionComponent>

    override fun process(entityId: Int) {


        markerMapper.remove(entityId)
    }
}