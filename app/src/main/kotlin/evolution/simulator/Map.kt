package evolution.simulator

import evolution.simulator.gui.GridElementBox
import evolution.simulator.gui.Resources
import javafx.scene.Node

class Map(private val strategy: Strategy, private val resources: Resources): IDisplay {
    private val tiles: Array<Array<MapTile>> = Array(strategy.params.width) {x -> Array(strategy.params.height) {y -> MapTile(strategy.plantStrategy(Vector2d(x,y)), resources)} }

    fun getTile(pos: Vector2d): MapTile {
        return tiles[pos.x][pos.y]
    }

    fun place(animal: Animal) {
        val pos = animal.animalPosition
        tiles[pos.x][pos.y].animalEnterBuffer(animal)
        tiles[pos.x][pos.y].animalEnterBufferApply()
    }

    fun movePhase() {
        for (x in 0 until strategy.params.width) {
            for (y in 0 until strategy.params.height) {
                tiles[x][y].animals.forEach {
                        animal -> run {
                        val oldPos = animal.animalPosition
                        val newPos = animal.move(strategy.mapStrategy)
                        if (newPos != oldPos) {
                            tiles[newPos.x][newPos.y].animalEnterBuffer(animal)
                            tiles[x][y].animalLeaveBuffer(animal)
                        }
                    }
                }
            }
        }

        for (x in 0 until strategy.params.width) {
            for (y in 0 until strategy.params.height) {
                tiles[x][y].animalLeaveBufferApply()
                tiles[x][y].animalEnterBufferApply()
            }
        }
    }

    fun plantGrowthPhase() {
        val tilesFlat = tiles.asSequence().flatMap { column -> column.asSequence() }.toList()
        val x = RandomVariable(tilesFlat.map { it.growthProbability }.toMutableList())
        x.randomList(strategy.params.plantGrowthRate).forEach { tilesFlat[it].growPlant() }
    }

    fun matingPhase() {
        for (x in 0 until strategy.params.width) {
            for (y in 0 until strategy.params.height) {
                tiles[x][y].matingPhase()
            }
        }
    }

    fun eatingPhase() {
        for (x in 0 until strategy.params.width) {
            for (y in 0 until strategy.params.height) {
                tiles[x][y].eatingPhase()
            }
        }
    }

    fun agePhase() {
        for (x in 0 until strategy.params.width) {
            for (y in 0 until strategy.params.height) {
                tiles[x][y].agePhase()
            }
        }
    }

    fun deathPhase(iterationNumber: ULong) {
        for (x in 0 until strategy.params.width) {
            for (y in 0 until strategy.params.height) {
                tiles[x][y].deathPhase(iterationNumber)
            }
        }
    }

    fun statistics(): SimulationStatistics {
        val stats = SimulationStatistics()
        val genomeMap = HashMap<Genome, Int>()
        for (x in 0 until strategy.params.width) {
            for (y in 0 until strategy.params.height) {
                tiles[x][y].animals.forEach{
                    stats.animalCount += 1u
                    stats.averageEnergy += it.animalEnergy
                    stats.averageLifespan += it.animalAge
                    val genomeCounter = genomeMap.getOrDefault(it.animalGenome, 0) + 1
                    genomeMap[it.animalGenome] = genomeCounter
                }

                if (tiles[x][y].hasPlant) {
                    stats.plantCount += 1u
                } else {
                    if (tiles[x][y].animals.isEmpty()) {
                        stats.emptyTiles += 1u
                    }
                }
            }
        }
        stats.averageEnergy /= stats.animalCount.toInt()
        stats.averageLifespan /= stats.animalCount.toInt()
        stats.bestGenome = genomeMap.entries.sortedBy { it.value }.map{ it.key }.takeLast(5)
        return stats
    }

    override fun display(): List<Node> {
        val nodes = ArrayList<Node>()
        for (x in 0 until strategy.params.width) {
            for (y in 0 until strategy.params.height) {
                val tileNodes = getTile(Vector2d(x,y)).display()
                val gridElementBox = GridElementBox(tileNodes).asNode()
                nodes.add(gridElementBox)
            }
        }
        return nodes
    }
}
