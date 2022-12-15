package evolution.simulator

import kotlin.test.Test
class MapTest {
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

    @Test fun moveBufferNoBoundryExceed() {
        val map = Map(strategy)
    }
}
