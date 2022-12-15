package evolution.simulator.gui

import javafx.stage.Stage
import javafx.scene.Scene

import javafx.scene.layout.HBox

class SimulationWindow(private val grid: Grid, private val stage: Stage) {

    fun start() {
        grid.update()

        val chart = Chart()
        chart.addPoint(0, 0)
        chart.addPoint(20, 14)
        chart.addPoint(60, 43)
        chart.addPoint(80, 32)
        chart.addPoint(100, 43)

        
        val hbox = HBox()
        hbox.children.addAll(grid.asNode(), chart.asNode())

        val scene = Scene(hbox, grid.width*40.0 + 400, grid.height*40.0)

        stage.title = "Evolution Simulator"
        stage.scene = scene
        stage.show()
    }
}
