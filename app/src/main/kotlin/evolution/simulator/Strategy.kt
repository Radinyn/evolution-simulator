package evolution.simulator

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class Strategy( val params: SimulationParameters ) {
    // describes how much equator fields are favored in growingPhase
    private val equatorFavorCoefficient = 10

    // depends on animalBehavior takes current index and len of genome return index according to chosen strategy
    val animalStrategy: (index: Int, len: Int) -> Int

    // depends on plantGrowthType returns function that will be evaluated by each mapTile when asked about its probability of growth
    val plantStrategy: (pos: Vector2d) -> ((corpses: Int) -> Double)

    // depends on mutationType mutates given gene according to chosen strategy
    val mutationStrategy: (index: UInt) -> UInt

    // depends on mapType takes position and applies transformation according to Map variant, returns new position and cost of movement
    val mapStrategy: (pos: Vector2d) -> Pair<Vector2d, Int>

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
                        { _ -> 1.0 + equatorFavorCoefficient }
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
            // map start from coordinates 0,0 at lower left corner
            MapType.GLOBE -> {
                // no cost per move, loop around left and right (x), no access to poles (y)
                { pos -> Pair(Vector2d((pos.x + params.width) % params.width, min(max(pos.y, 0), params.height-1)), 0) }
            }
            MapType.PORTAL -> {
                { pos ->
                    // when try to go out of map
                    if (pos.x < 0 || pos.x >= params.width || pos.y < 0 || pos.y >= params.height) {
                        // random pos and penalty
                        Pair(Vector2d( abs(Random.nextInt() % params.width), abs(Random.nextInt() % params.height)), params.reproductionCost)
                    }else{
                        // otherwise normal movement no cost per move
                        Pair(pos, 0)
                    }
                }
            }
        }
    }
    private fun isEquator(pos: Vector2d): Boolean {
        // 1/3 width belt 1/3 away from top
        return pos.y > params.height/3 && pos.y < 2*params.height/3
    }
}