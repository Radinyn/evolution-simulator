package evolution.simulator.gui;

import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.geometry.Pos
import javafx.event.*

import java.io.FileInputStream

class GridElementBox {
    public val vbox: VBox = VBox()

    constructor(element: String) {
        val image = Image(FileInputStream("src/main/resources/test.jpeg"));
        val imageView = ImageView(image);
        imageView.setFitWidth(20.0);
        imageView.setFitHeight(20.0);

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
