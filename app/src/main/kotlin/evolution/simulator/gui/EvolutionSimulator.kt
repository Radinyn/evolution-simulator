package evolution.simulator.gui

import javafx.application.Application
import javafx.stage.Stage

import evolution.simulator.SimulationParameters


class EvolutionSimulator : Application() {

    override fun start(primaryStage: Stage) {
        val window = MenuWindow(this, primaryStage)
        window.start()
    }

    public fun newSimulation(parameters: SimulationParameters) {
        val grid = Grid(parameters.width, parameters.height)
        val stage: Stage = Stage();
        val window = SimulationWindow(grid, stage)
        window.start()
    }
}
