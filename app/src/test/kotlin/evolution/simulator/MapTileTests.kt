package evolution.simulator

import evolution.simulator.gui.Resources
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MapTileTests {

    private val strategy = Strategy(SimulationParameters(
        animalBehavior = AnimalBehavior.STRICT,
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
        reproductionCost = 5,
        width = 0,
        height = 0,
        stuffedThreshold = 5,
    ))

    @Test fun deathPhase() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, 0, Genome.generateRandom(strategy), strategy)

        val tile = MapTile(strategy.plantStrategy(Vector2d(0,0)), Resources())

        tile.animalEnterBuffer(animal1)
        tile.animalEnterBuffer(animal2)
        tile.animalEnterBuffer(animal3)
        tile.animalEnterBuffer(animal4)
        tile.animalEnterBufferApply()

        tile.deathPhase(0u)

        val animals = tile.animalsSorted
        assertEquals(2, animals.size)
        assertContentEquals(listOf(animal3, animal1), animals)
    }

    @Test fun agePhase() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, 0, Genome.generateRandom(strategy), strategy)

        val tile = MapTile(strategy.plantStrategy(Vector2d(0,0)), Resources())

        tile.animalEnterBuffer(animal1)
        tile.animalEnterBuffer(animal2)
        tile.animalEnterBuffer(animal3)
        tile.animalEnterBuffer(animal4)
        tile.animalEnterBufferApply()

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
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, 0, Genome.generateRandom(strategy), strategy)

        val tile = MapTile(strategy.plantStrategy(Vector2d(0,0)), Resources())
        tile.growPlant()

        tile.animalEnterBuffer(animal1)
        tile.animalEnterBuffer(animal2)
        tile.animalEnterBuffer(animal3)
        tile.animalEnterBuffer(animal4)
        tile.animalEnterBufferApply()

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
        assertEquals(7 + strategy.params.plantEnergy, animals[0].animalEnergy)
    }

    @Test fun matingPhaseNoCandidate() {
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 1, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, 0, Genome.generateRandom(strategy), strategy)

        val tile = MapTile(strategy.plantStrategy(Vector2d(0,0)), Resources())

        tile.animalEnterBuffer(animal1)
        tile.animalEnterBuffer(animal2)
        tile.animalEnterBuffer(animal3)
        tile.animalEnterBuffer(animal4)
        tile.animalEnterBufferApply()

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
        val animal1 = Animal(Vector2d(0,0), Orientation.NORTH, 6, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal2 = Animal(Vector2d(0,0), Orientation.NORTH, -1, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal3 = Animal(Vector2d(0,0), Orientation.NORTH, 7, 0, 0, 0, Genome.generateRandom(strategy), strategy)
        val animal4 = Animal(Vector2d(0,0), Orientation.NORTH, -3, 0, 0, 0, Genome.generateRandom(strategy), strategy)

        val tile = MapTile(strategy.plantStrategy(Vector2d(0,0)), Resources())

        tile.animalEnterBuffer(animal1)
        tile.animalEnterBuffer(animal2)
        tile.animalEnterBuffer(animal3)
        tile.animalEnterBuffer(animal4)
        tile.animalEnterBufferApply()

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
        assertEquals(1, animals[2].animalEnergy)
        assertEquals(2, animals[1].animalEnergy)
        assertEquals(10, animals[0].animalEnergy)
    }
}