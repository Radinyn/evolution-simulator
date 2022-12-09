package evolution.simulator.gui

import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.control.*
import javafx.event.*
import javafx.geometry.Pos
import javafx.scene.image.Image

import java.io.FileInputStream

import evolution.simulator.SimulationParameters

class MenuWindow(
    private val app: EvolutionSimulator,
    private val stage: Stage
) {

    public fun start() {
        val vbox = VBox()

        val label = Label("wprowadz parametry") 

        val button = Button("RUSZAMY")

        val menu = ParameterMenu()

        button.onAction = EventHandler<ActionEvent> {
            app.newSimulation(menu.get())
        }

        // slider.showTickMarks(true)
        // slider.showTickLabels(true)

        vbox.children.addAll(label, menu.asNode(), button)
        vbox.alignment = Pos.CENTER

        val background_image = BackgroundImage(
            Image(FileInputStream("src/main/resources/test2.jpeg")),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize(1.0, 1.0, true, true, false, false)
        )
        vbox.background = Background(background_image)

        val scene = Scene(vbox, 400.0, 600.0)
        stage.title = "Evolution Simulator - Menu"
        stage.scene = scene
        stage.show()
    }

}
