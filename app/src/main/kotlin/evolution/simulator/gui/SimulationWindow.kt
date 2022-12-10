package evolution.simulator.gui

import javafx.stage.Stage
import javafx.scene.Scene

class SimulationWindow(
    private val grid: Grid,
    private val stage: Stage
) {
    public fun start() {
        grid.update()
        val scene = Scene(grid.gridPane, grid.width*20.0, grid.height*20.0)
        stage.title = "Evolution Simulator"
        stage.scene = scene
        stage.show()
    }
}
