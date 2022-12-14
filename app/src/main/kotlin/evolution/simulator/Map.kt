package evolution.simulator

class Map(private val params: SimulationParameters) {
    private val tiles = Array(params.width) {Array(params.height) {MapTile()}}

    fun getTile(pos: Vector2d): MapTile {
        return tiles[pos.x][pos.y]
    }

    private fun controlPos(pos:Vector2d): Vector2d {
        return pos // TODO implement teleportation and other things dependent on MAPTYPE
    }

    fun place(animal: Animal) {
        val pos = controlPos(animal.animalPositon)
        tiles[pos.x][pos.y].animalEnterBuffer(animal)
        tiles[pos.x][pos.y].animalEnterBufferApply()
    }

    fun movePhase() {
        tiles.iterator().forEach { column ->
            column.iterator().forEach {
                it.animals.iterator().forEach {
                        animal -> run {
                        val newPos = controlPos(animal.move())
                        tiles[newPos.x][newPos.y].animalEnterBuffer(animal)
                        it.animalLeave(animal)
                    }
                }
        }}

        tiles.iterator().forEach { column ->
            column.iterator().forEach {
            it.animalEnterBufferApply()
        }}
    }

    fun plantGrowthPhasp() {

    }
}
