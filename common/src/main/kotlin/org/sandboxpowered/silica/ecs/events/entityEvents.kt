package org.sandboxpowered.silica.ecs.events

import com.artemis.ArchetypeBuilder
import com.artemis.Entity
import net.mostlyoriginal.api.event.common.Event
import org.sandboxpowered.silica.api.entity.EntityDefinition

abstract class EntityEvent : Event

@Deprecated("Use EntityEvents.INITIALIZE_ARCHETYPE_EVENT instead.") // Kept just in case
class InitializeArchetypeEvent(val entityDefinition: EntityDefinition, val builder: ArchetypeBuilder) : EntityEvent()
class SpawnEntityEvent(val entity: Entity) : EntityEvent()
class RemoveEntitiesEvent(val entityIds: IntArray) : EntityEvent()