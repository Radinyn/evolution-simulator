package evolution.simulator

import kotlin.math.abs
import kotlin.random.Random

class Strategy( val params: SimulationParameters ) {
    private val EquatorFavorCoefficient = 1
    val animalStrategy: (index: Int, len: Int) -> Int // dependent on animalBehavior takes current index and len of genome return new index to return from
    val plantStrategy: (pos: Vector2d) -> ((corpses: Int) -> Double) // dependent on plantGrowthType returns function that will be evaluated by each mapTile when asked about its probability of growth
    val mutationStrategy: (index: UInt) -> UInt // dependent on mutationType mutates given gene
    val mapStrategy: (pos: Vector2d) -> Vector2d // dependent on mapType takes position and applies transformation dependent on MapType variant

    init {
        animalStrategy = when (params.animalBehavior) {
            AnimalBehavior.STRICT -> {
                { pos, len -> (pos + 1) % len }
            }
            AnimalBehavior.RANDOM -> {
                { pos, len ->
                    if (Random.nextDouble() < 0.8) {
                        (pos + 1) % len
                    }else{
                        abs(Random.nextInt()) % len
                    }
                }
            }
        }
        plantStrategy = when (params.plantGrowthType) {
            GrowthType.EQUATOR -> {
                { pos ->
                    if (isEquator(pos)){
                        { _ -> 1.0 + EquatorFavorCoefficient }
                    }else {
                        { _ -> 1.0 }
                    }
                }
            }
            GrowthType.CORPSES -> {
                { _ ->
                    {corpses -> 1.0/(1 + corpses)}  // drops when corpses increase
                }
            }
        }
        mutationStrategy = when (params.mutationType) {
            MutationType.RANDOM -> {
                { (abs(Random.nextInt()) % 8).toUInt() }
            }
            MutationType.CORRECTION -> {
                { index -> ((index.toInt()+8)+(listOf(1, -1).random()) % 8).toUInt() }
            }
        }
        mapStrategy = when (params.mapType) {
            MapType.GLOBE -> {
                { pos -> pos } // TODO implement actual logic for this
            }
            MapType.PORTAL -> {
                { pos -> pos } // TODO for this too
            }
        }
    }
    private fun isEquator(pos: Vector2d): Boolean {
        // 1/3 width belt 1/3 away from top
        return pos.y > params.height/3 && pos.y < 2*params.height/3
    }
}