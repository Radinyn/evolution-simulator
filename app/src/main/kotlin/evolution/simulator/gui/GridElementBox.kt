package evolution.simulator.gui

import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.VBox

class GridElementBox(children: Collection<Node>) {
    private val vbox: VBox = VBox()

    init {
<<<<<<< HEAD
//        val image = Image(FileInputStream("src/main/resources/test.jpeg"))
//        val imageView = ImageView(image)
//        imageView.fitWidth = 40.0
//        imageView.fitHeight = 40.0
//        this.vbox.onMousePressed = EventHandler<Event> {
//            imageView.rotate = (imageView.rotate + 90) % 360
//        }
        // this.vbox.onMouseReleased = EventHandler<Event> {
        //     imageView.rotate = 0.0
        // }
        this.vbox.children.addAll(children)
=======
        val image = Image(FileInputStream("src/main/resources/test.jpeg"))
        val imageView = ImageView(image)
        imageView.fitWidth = 40.0
        imageView.fitHeight = 40.0
        this.vbox.onMousePressed = EventHandler<Event> {
            imageView.rotate = (imageView.rotate + 90) % 360
        }
        // this.vbox.onMouseReleased = EventHandler<Event> {
        //     imageView.rotate = 0.0
        // }
        this.vbox.children.addAll(imageView)
>>>>>>> radinyn/dec15
        this.vbox.alignment = Pos.CENTER
    }

    fun asNode(): VBox {
        return vbox
    }
}
