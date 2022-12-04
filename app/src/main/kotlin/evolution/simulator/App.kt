package evolution.simulator

import evolution.simulator.gui.EvolutionSimulator
import javafx.application.Application

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main(args: Array<String>) {
    Application.launch(EvolutionSimulator::class.java, *args)
}