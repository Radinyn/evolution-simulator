package evolution.simulator.gui
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.scene.text.*
import javafx.application.Platform
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color

class DialogBox(image: Image, text: String) {
    init {
        val dialog = Stage()
        val dialogVbox = VBox()

        val textNode = Text(text)
        textNode.setFill(Color.YELLOW);

        dialogVbox.children.add(textNode)

        val backgroundImage = BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize(1.0, 1.0, true, true, false, false)
        )
        dialogVbox.background = Background(backgroundImage)

        val dialogScene = Scene(dialogVbox, 200.0, 200.0)
        dialog.setScene(dialogScene)
        dialog.show()
    }
}