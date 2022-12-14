package evolution.simulator

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
class MapTest {
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

    @Test fun moveBufferNoBoundryExceed() {
        val map = Map(params)


    }
}