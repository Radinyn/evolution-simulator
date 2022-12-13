package evolution.simulator

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GenomeTest {

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
        reproductionCost = 10,
        width = 0,
        height = 0,
        stuffedThreshold = 5,
    )

    @Test fun rangeAndSize() {
        var genome: Genome
        var maximum: UInt
        var minimum: UInt
        repeat(1000) {
            genome = Genome.generateRandom(params)
            maximum = genome.max()
            minimum = genome.min()
            assertEquals(params.genomeLength, genome.size)
            assertTrue( maximum <= params.genomeLength.toUInt() && minimum >= 0u )
        }
    }

    @Test fun iterator() {
        var genome: Genome
        repeat(1000) {
            genome = Genome.generateRandom(params)
            assertEquals(genome.size, genome.count())
            genome.forEachIndexed { index, value -> assertEquals(genome.get(index), value) }
        }
    }

    @Test fun cyclicIterator() {
        var genome: Genome
        repeat(1000) {
            genome = Genome.generateRandom(params)
            val iterator = genome.cyclicIterator()
            val items: ArrayList<UInt> = ArrayList()
            repeat(genome.size) {
                if (iterator.hasNext()) {
                    items.add(iterator.next())
                }
            }
            repeat(genome.size) {
                if (iterator.hasNext()) {
                    items[it].equals(iterator.next())
                }
            }
        }
    }
}