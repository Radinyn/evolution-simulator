package evolution.simulator.gui

import evolution.simulator.*
import evolution.simulator.Map
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.ToggleButton
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlin.random.Random

class SimulationWindow(private val params: SimulationParameters, private val stage: Stage) {

    fun start() {
        val charts = StatisticsCharts()
        val strategy = Strategy(params)
        val map = Map(strategy, Resources())
        val grid = Grid(params.width, params.height, map)

        repeat(params.initAnimalNum) {
            map.place(Animal(
                Vector2d(Random.nextInt(params.width), Random.nextInt(params.width)),
                Orientation.NORTH_EAST, params.initAnimalEnergy,
                0, 0, 0,
                Genome.generateRandom(strategy), strategy)
            )
        }

        // TODO trzeba to wynieśc w klase która implementuje runnable itp żeby można było stopować i resumować wątek wait() i awake() na razie prowizorka
        val simThreadHandle = Thread {
            var cnt: ULong = 0u
            while (true){
                Thread.sleep(100)
                map.plantGrowthPhase()
                map.movePhase()
                map.agePhase()
                map.eatingPhase()
                map.matingPhase()
                map.deathPhase(cnt)
                grid.update()
                val stats = map.statistics()
                stats.numOfIterations = cnt
                charts.update(stats)
                cnt += 1u
            }
        }
        
        val hbox = HBox()
        hbox.children.addAll(grid.asNode(), charts.asNode())

        val hbox2 = HBox()
        val playButton = ToggleButton("Play")
        val recordButton = ToggleButton("Record")
        hbox2.children.addAll(playButton, recordButton)

        playButton.onAction = EventHandler {

        }

        val vbox = VBox(hbox, hbox2)
        val scene = Scene(vbox, grid.width*40.0 + 400, grid.height*40.0 + 20)

        stage.title = "Evolution Simulator"
        stage.scene = scene
        stage.show()

        simThreadHandle.start()
    }
}
