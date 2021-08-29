@file:Suppress("unused")

package org.sandboxpowered.silica.block

import org.sandboxpowered.silica.state.property.BooleanProperty.Companion.of
import org.sandboxpowered.silica.state.property.EnumProperty.Companion.of
import org.sandboxpowered.silica.state.property.IntProperty.Companion.of
import org.sandboxpowered.silica.state.property.Property
import org.sandboxpowered.silica.util.Direction
import org.sandboxpowered.silica.util.Direction.Axis
import org.sandboxpowered.silica.util.Half
import org.sandboxpowered.silica.util.StairShape
import org.sandboxpowered.silica.util.WallShape

object SilicaBlockProperties {
    val ATTACHED: Property<Boolean> = of("attached")
    val BOTTOM: Property<Boolean> = of("bottom")
    val CONDITIONAL: Property<Boolean> = of("conditional")
    val DISARMED: Property<Boolean> = of("disarmed")
    val DRAG: Property<Boolean> = of("drag")
    val ENABLED: Property<Boolean> = of("enabled")
    val EXTENDED: Property<Boolean> = of("extended")
    val EYE: Property<Boolean> = of("eye")
    val FALLING: Property<Boolean> = of("falling")
    val HANGING: Property<Boolean> = of("hanging")
    val HAS_BOTTLE_0: Property<Boolean> = of("has_bottle_0")
    val HAS_BOTTLE_1: Property<Boolean> = of("has_bottle_1")
    val HAS_BOTTLE_2: Property<Boolean> = of("has_bottle_2")
    val HAS_RECORD: Property<Boolean> = of("has_record")
    val HAS_BOOK: Property<Boolean> = of("has_book")
    val INVERTED: Property<Boolean> = of("inverted")
    val IN_WALL: Property<Boolean> = of("in_wall")
    val LIT: Property<Boolean> = of("lit")
    val LOCKED: Property<Boolean> = of("locked")
    val OCCUPIED: Property<Boolean> = of("occupied")
    val OPEN: Property<Boolean> = of("open")
    val PERSISTENT: Property<Boolean> = of("persistent")
    val POWERED: Property<Boolean> = of("powered")
    val SHORT: Property<Boolean> = of("short")
    val SIGNAL_FIRE: Property<Boolean> = of("signal_fire")
    val SNOWY: Property<Boolean> = of("snowy")
    val TRIGGERED: Property<Boolean> = of("triggered")
    val UNSTABLE: Property<Boolean> = of("unstable")
    val WATERLOGGED: Property<Boolean> = of("waterlogged")
    val UP: Property<Boolean> = of("up")
    val DOWN: Property<Boolean> = of("down")
    val NORTH: Property<Boolean> = of("north")
    val EAST: Property<Boolean> = of("east")
    val SOUTH: Property<Boolean> = of("south")
    val WEST: Property<Boolean> = of("west")
    val FLUID_LEVEL: Property<Int> = of("level", 1, 8)
    val AGE_1: Property<Int> = of("age", 0, 1)
    val AGE_2: Property<Int> = of("age", 0, 2)
    val AGE_3: Property<Int> = of("age", 0, 3)
    val AGE_5: Property<Int> = of("age", 0, 5)
    val AGE_7: Property<Int> = of("age", 0, 7)
    val AGE_15: Property<Int> = of("age", 0, 15)
    val AGE_25: Property<Int> = of("age", 0, 25)
    val BITES: Property<Int> = of("bites", 0, 6)
    val DELAY: Property<Int> = of("delay", 1, 4)
    val DISTANCE_1_7: Property<Int> = of("distance", 1, 7)
    val EGGS: Property<Int> = of("eggs", 1, 4)
    val HATCH: Property<Int> = of("hatch", 0, 2)
    val LAYERS: Property<Int> = of("layers", 1, 8)
    val LEVEL_3: Property<Int> = of("level", 0, 3)
    val LEVEL_8: Property<Int> = of("level", 0, 8)
    val LEVEL_1_8: Property<Int> = of("level", 1, 8)
    val LEVEL_15: Property<Int> = of("level", 0, 15)
    val HONEY_LEVEL: Property<Int> = of("honey_level", 0, 5)
    val MOISTURE: Property<Int> = of("moisture", 0, 7)
    val NOTE: Property<Int> = of("note", 0, 24)
    val PICKLES: Property<Int> = of("pickles", 1, 4)
    val POWER: Property<Int> = of("power", 0, 15)
    val STAGE: Property<Int> = of("stage", 0, 1)
    val DISTANCE_0_7: Property<Int> = of("distance", 0, 7)
    val ROTATION: Property<Int> = of("rotation", 0, 15)
    val FACING: Property<Direction> = of<Direction>("facing")
    val HORIZONTAL_FACING: Property<Direction> = of("facing", Direction.Type.HORIZONTAL)
    val HOPPER_FACING: Property<Direction> = of("facing") { it != Direction.UP }
    val HORIZONTAL_AXIS: Property<Axis> = of("axis", Axis::isHorizontal)
    val AXIS: Property<Axis> = of<Axis>("axis")
    val SLAB_HALF: Property<Half> = of<Half>("type")
    val STAIR_HALF: Property<Half> = of("half") { it != Half.DOUBLE }
    val STAIR_SHAPE: Property<StairShape> = of<StairShape>("shape")
    val WALL_SHAPE_NORTH: Property<WallShape> = of<WallShape>("north")
    val WALL_SHAPE_EAST: Property<WallShape> = of<WallShape>("east")
    val WALL_SHAPE_SOUTH: Property<WallShape> = of<WallShape>("south")
    val WALL_SHAPE_WEST: Property<WallShape> = of<WallShape>("west")
}