package evolution.simulator

import kotlin.test.Test
import kotlin.test.assertEquals

class GenomeTest {

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
        reproductionCost = 10,
        width = 0,
        height = 0,
        stuffedThreshold = 5,
    ))

    @Test fun size() {
        var genome: Genome
        repeat(1000) {
            genome = Genome.generateRandom(strategy)
            assertEquals(strategy.params.genomeLength, genome.size)
        }
    }

    @Test fun cyclicIterator() {
        var genome: Genome
        repeat(1000) {
            genome = Genome.generateRandom(strategy)
            val iterator = genome.iterator()
            val items: ArrayList<UInt> = ArrayList()
            repeat(genome.size) {
                if (iterator.hasNext()) {
                    items.add(iterator.next())
                }
            }
            repeat(genome.size) {
                if (iterator.hasNext()) {
                    assertEquals(items[it], iterator.next())
                }
            }
        }
    }
}