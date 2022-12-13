package evolution.simulator.gui

import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.stage.Stage
import java.io.FileInputStream

class MenuWindow(
    private val app: EvolutionSimulator,
    private val stage: Stage
) {

    fun start() {
        val vbox = VBox()

        val label = Label("wprowadz parametry") 

        val button = Button("RUSZAMY")

        val menu = ParameterMenu()

        button.onAction = EventHandler {
            app.newSimulation(menu.get())
        }


        vbox.children.addAll(label, menu.asNode(), button)
        vbox.alignment = Pos.CENTER

        val backgroundImage = BackgroundImage(
            Image(FileInputStream("src/main/resources/test2.jpeg")),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize(1.0, 1.0, true, true, false, false)
        )
        vbox.background = Background(backgroundImage)

        val scene = Scene(vbox, 400.0, 600.0)
        stage.title = "Evolution Simulator - Menu"
        stage.scene = scene
        stage.show()
    }

}
