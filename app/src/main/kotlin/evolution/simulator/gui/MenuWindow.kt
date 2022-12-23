package evolution.simulator.gui

import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.stage.*
import java.io.*
import evolution.simulator.ParameterManager

class MenuWindow(
    private val app: EvolutionSimulator,
    private val stage: Stage
) {

    fun start() {
        val vbox = VBox()

        val label = Label("Enter the parameters") 

        val buttonHBox = HBox()

        val startButton = Button("Start")
        val saveButton = Button("Save")
        val loadButton = Button("Load")

        val parameterManager = ParameterManager()

        val menu = ParameterMenu()

        startButton.onAction = EventHandler {
            app.newSimulation(menu.get())
        }

        saveButton.onAction = EventHandler {
            val stage = Stage()
            val fileChooser = FileChooser()
            fileChooser.title = "Create output file"
            val file: File = fileChooser.showSaveDialog(stage)
            parameterManager.save(file, menu.get())
        }

        loadButton.onAction = EventHandler {
            val stage = Stage()
            val fileChooser = FileChooser()
            fileChooser.title = "Create output file"
            val file: File = fileChooser.showOpenDialog(stage)
            val params = parameterManager.load(file)
            menu.set(params)
        }

        buttonHBox.children.addAll(saveButton, startButton, loadButton)
        buttonHBox.alignment = Pos.BOTTOM_CENTER

        vbox.children.addAll(label, menu.asNode(), buttonHBox)
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
