package evolution.simulator

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MapTileTests {

    private val params = SimulationParameters(
        animalBehavior = AnimalBehavior.RANDOM,
        genomeLength = 10,
        initAnimalEnergy = 10,
        initAnimalNum = 10,
        initPlantsNum = 10,
        mapType = MapType.GLOBE,
        mutationMaxNum = 10,
        mutationMinNum = 0,
        mutationType = MutationType.RANDOM,
        plantEnergy = 10,
        plantGrowthRate = 10,
        plantGrowthType = GrowthType.CORPSES,
        reproductionCost = 3,
        width = 0,
        height = 0,
        stuffedThreshold = 5,
    )

    @Test fun deathPhase() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, Genome.generateRandom(params), params)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, Genome.generateRandom(params), params)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, Genome.generateRandom(params), params)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, Genome.generateRandom(params), params)

        val tile = MapTile()

        tile.animalEnter(animal1)
        tile.animalEnter(animal2)
        tile.animalEnter(animal3)
        tile.animalEnter(animal4)

        tile.deathPhase()

        val animals = tile.animalsSorted
        assertEquals(2, animals.size)
        assertContentEquals(listOf(animal3, animal1), animals)
    }

    @Test fun agePhase() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, Genome.generateRandom(params), params)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, Genome.generateRandom(params), params)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, Genome.generateRandom(params), params)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, Genome.generateRandom(params), params)

        val tile = MapTile()

        tile.animalEnter(animal1)
        tile.animalEnter(animal2)
        tile.animalEnter(animal3)
        tile.animalEnter(animal4)

        var animals = tile.animalsSorted
        assertEquals(4, animals.size)
        assertEquals(-3, animals[3].animalEnergy)
        assertEquals(-1, animals[2].animalEnergy)
        assertEquals(1, animals[1].animalEnergy)
        assertEquals(7, animals[0].animalEnergy)

        tile.agePhase()

        animals = tile.animalsSorted
        assertEquals(4, animals.size)
        assertEquals(-4, animals[3].animalEnergy)
        assertEquals(-2, animals[2].animalEnergy)
        assertEquals(0, animals[1].animalEnergy)
        assertEquals(6, animals[0].animalEnergy)
    }

    @Test fun eatingPhase() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, Genome.generateRandom(params), params)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, Genome.generateRandom(params), params)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, Genome.generateRandom(params), params)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, Genome.generateRandom(params), params)

        val tile = MapTile()

        tile.animalEnter(animal1)
        tile.animalEnter(animal2)
        tile.animalEnter(animal3)
        tile.animalEnter(animal4)

        var animals = tile.animalsSorted
        assertEquals(4, animals.size)
        assertEquals(-3, animals[3].animalEnergy)
        assertEquals(-1, animals[2].animalEnergy)
        assertEquals(1, animals[1].animalEnergy)
        assertEquals(7, animals[0].animalEnergy)

        tile.eatingPhase()

        animals = tile.animalsSorted
        assertEquals(4, animals.size)
        assertEquals(-3, animals[3].animalEnergy)
        assertEquals(-1, animals[2].animalEnergy)
        assertEquals(1, animals[1].animalEnergy)
        assertEquals(7+params.plantEnergy, animals[0].animalEnergy)
    }

    @Test fun matingPhaseNoCandidate() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, Genome.generateRandom(params), params)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, Genome.generateRandom(params), params)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, Genome.generateRandom(params), params)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, Genome.generateRandom(params), params)

        val tile = MapTile()

        tile.animalEnter(animal1)
        tile.animalEnter(animal2)
        tile.animalEnter(animal3)
        tile.animalEnter(animal4)

        var animals = tile.animalsSorted
        assertEquals(4, animals.size)
        assertEquals(-3, animals[3].animalEnergy)
        assertEquals(-1, animals[2].animalEnergy)
        assertEquals(1, animals[1].animalEnergy)
        assertEquals(7, animals[0].animalEnergy)

        tile.matingPhase()

        animals = tile.animalsSorted
        assertEquals(4, animals.size)
        assertEquals(-3, animals[3].animalEnergy)
        assertEquals(-1, animals[2].animalEnergy)
        assertEquals(1, animals[1].animalEnergy)
        assertEquals(7, animals[0].animalEnergy)
    }

    @Test fun matingPhase() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 6, 0, 0, Genome.generateRandom(params), params)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, Genome.generateRandom(params), params)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, Genome.generateRandom(params), params)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, Genome.generateRandom(params), params)

        val tile = MapTile()

        tile.animalEnter(animal1)
        tile.animalEnter(animal2)
        tile.animalEnter(animal3)
        tile.animalEnter(animal4)

        var animals = tile.animalsSorted
        assertEquals(4, animals.size)
        assertEquals(-3, animals[3].animalEnergy)
        assertEquals(-1, animals[2].animalEnergy)
        assertEquals(6, animals[1].animalEnergy)
        assertEquals(7, animals[0].animalEnergy)

        tile.matingPhase()

        animals = tile.animalsSorted
        assertEquals(5, animals.size)
        assertEquals(-3, animals[4].animalEnergy)
        assertEquals(-1, animals[3].animalEnergy)
        assertEquals(3, animals[2].animalEnergy)
        assertEquals(4, animals[1].animalEnergy)
        assertEquals(6, animals[0].animalEnergy)
    }
}