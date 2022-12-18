package evolution.simulator

import evolution.simulator.gui.Resources
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
        plantGrowthRate = 100,
        plantGrowthType = GrowthType.EQUATOR,
        reproductionCost = 10,
        width = 30,
        height = 30,
        stuffedThreshold = 5,
    ))

    @Test fun moveBufferNoBoundryExceed() {
        val map = Map(strategy, Resources())
    }

    @Test fun plantGrowth() {
        val map = Map(strategy, Resources())
        map.plantGrowthPhase()
        for (y in 0 until strategy.params.height){
            for (x in 0 until strategy.params.width){
                print(if (map.getTile(Vector2d(x,y)).hasPlant) " * " else "   ")
            }
            println(" ")
        }
    }
}
