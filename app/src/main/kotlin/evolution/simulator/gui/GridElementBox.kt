package evolution.simulator.gui

import javafx.event.Event
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import java.io.FileInputStream

class GridElementBox(element: String) {
    val vbox: VBox = VBox()

    init {
        val image = Image(FileInputStream("src/main/resources/test.jpeg"))
        val imageView = ImageView(image)
        imageView.fitWidth = 20.0
        imageView.fitHeight = 20.0
        this.vbox.onMousePressed = EventHandler<Event> {
            imageView.rotate = 180.0
        }
        this.vbox.onMouseReleased = EventHandler<Event> {
            imageView.rotate = 0.0
        }
        this.vbox.children.addAll(imageView)
        this.vbox.alignment = Pos.CENTER
    }
}
