package evolution.simulator.gui

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane



class EvolutionSimulator : Application() {

    override fun start(primaryStage: Stage) {
        val grid = Grid(10, 10)
        grid.update()

        val scene = Scene(grid.gridPane, grid.width*80.0, grid.height*80.0)

        primaryStage.title = "Evolution Simulator"
        primaryStage.scene = scene
        primaryStage.show()
    }

}