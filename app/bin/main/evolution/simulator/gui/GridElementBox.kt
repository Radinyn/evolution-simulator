package evolution.simulator.gui;

import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.geometry.Pos

import java.io.FileInputStream

class GridElementBox {
    public val vbox: VBox = VBox()

    constructor(text: String) {
        val image = Image(FileInputStream("src/main/resources/test.jpeg"));
        val imageView = ImageView(image);
        imageView.setFitWidth(80.0);
        imageView.setFitHeight(60.0);

        val label = Label(text)
        this.vbox.children.addAll(imageView, label)
        this.vbox.alignment = Pos.CENTER
    }
}
