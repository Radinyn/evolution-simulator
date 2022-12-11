package evolution.simulator

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MapTileTests {

    @Test fun deathPhase() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, Genome(listOf(1u)))
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, Genome(listOf(1u)))
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, Genome(listOf(1u)))
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, Genome(listOf(1u)))

        val tile = MapTile()

        tile.animalEnter(animal1)
        tile.animalEnter(animal2)
        tile.animalEnter(animal3)
        tile.animalEnter(animal4)

        tile.deathPhase()

        var animals = tile.animalsSorted
        assertEquals(2, animals.size)
        assertContentEquals(listOf(animal3, animal1), animals)
    }

    @Test fun agePhase() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, Genome(listOf(1u)))
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, Genome(listOf(1u)))
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, Genome(listOf(1u)))
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, Genome(listOf(1u)))

        val tile = MapTile()

        tile.animalEnter(animal1)
        tile.animalEnter(animal2)
        tile.animalEnter(animal3)
        tile.animalEnter(animal4)

        var animals = tile.animalsSorted
        assertEquals(4, animals.size)
        assertEquals(-3, animals[3].getEnergy())
        assertEquals(-1, animals[2].getEnergy())
        assertEquals(1, animals[1].getEnergy())
        assertEquals(7, animals[0].getEnergy())

        tile.agePhase()

        animals = tile.animalsSorted
        assertEquals(4, animals.size)
        assertEquals(-4, animals[3].getEnergy())
        assertEquals(-2, animals[2].getEnergy())
        assertEquals(0, animals[1].getEnergy())
        assertEquals(6, animals[0].getEnergy())
    }
}