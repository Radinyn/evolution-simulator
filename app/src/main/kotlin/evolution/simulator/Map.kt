package evolution.simulator

import kotlin.streams.asStream

class Map(private val strategy: Strategy) {
    private val tiles: Array<Array<MapTile>> = Array(strategy.params.width) {x -> Array(strategy.params.height) {y -> MapTile(strategy.plantStrategy(Vector2d(x,y)))} }

    fun getTile(pos: Vector2d): MapTile {
        return tiles[pos.x][pos.y]
    }

    fun place(animal: Animal) {
        val pos = animal.animalPosition
        tiles[pos.x][pos.y].animalEnterBuffer(animal)
        tiles[pos.x][pos.y].animalEnterBufferApply()
    }

    fun movePhase() {
        tiles.iterator().forEach { column ->
            column.iterator().forEach {
                it.animals.iterator().forEach {
                        animal -> run {
                        val oldPos = animal.animalPosition
                        val newPos = animal.move(strategy.mapStrategy)
                        if (newPos != oldPos) {
                            tiles[newPos.x][newPos.y].animalEnterBuffer(animal)
                            it.animalLeave(animal)
                        }
                    }
                }
        }}

        tiles.iterator().forEach { column ->
            column.iterator().forEach {
            it.animalEnterBufferApply()
        }}
    }

    fun plantGrowthPhase() {
        val tilesFlat = tiles.iterator().asSequence().asStream().flatMap { column -> column.iterator().asSequence().asStream() }.toList()
        val x = RandomVariable(tilesFlat.stream().map { it.growthProbability }.toList())
        x.randomList(strategy.params.plantGrowthRate).forEach { tilesFlat[it].growPlant() }
    }
}
