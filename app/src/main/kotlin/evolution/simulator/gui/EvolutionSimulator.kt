package evolution.simulator.gui

import javafx.application.Application
import javafx.stage.Stage

import evolution.simulator.SimulationParameters


class EvolutionSimulator : Application() {

    override fun start(primaryStage: Stage) {
        val window = MenuWindow(this, primaryStage)
        window.start()
    }

    fun newSimulation(parameters: SimulationParameters) {
        val stage = Stage()
        val window = SimulationWindow(parameters, stage)
        window.start()
    }
}
