package evolution.simulator.gui

import evolution.simulator.Vector2d
import evolution.simulator.Map
import javafx.application.Platform
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import java.io.FileInputStream

class Grid(width: Int, height: Int, private val map: Map) {
    private val gridPane = GridPane()

    val height: Int
        get() {
            return gridPane.rowCount
        }

    val width: Int
        get() {
            return gridPane.columnCount
        }

    init {
        for (i in 1..height) {
            this.gridPane.rowConstraints.add(RowConstraints(40.0))
        }
        for (i in 1..width) {
            this.gridPane.columnConstraints.add(ColumnConstraints(40.0))
        }

        val backgroundImage = BackgroundImage(
            Image(FileInputStream("src/main/resources/test2.jpeg")),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize(1.0, 1.0, true, true, false, false)
        )
        gridPane.background = Background(backgroundImage)
    }

    fun update() {
        Platform.runLater {
            gridPane.children.clear()
            val nodes = map.display()
            for (i in 0 until width) {
                for (j in 0 until height) {

                    // val element = map.getTile(Vector2d(i, j))

                    // val image = Image(FileInputStream("src/main/resources/test.jpeg"))
                    // val imageView = ImageView(image)imageView
                    // imageView.fitWidth = 40.0
                    // imageView.fitHeight = 40.0

                    
                    gridPane.add(nodes[i*width+j], i, j)
                }
            }

            // for ((vec2, nodeList) in this.map) {
            //     gridPane.add(nodeList[0].vbox, vec2.x, vec2.y)
            // }
        }
    }

    fun asNode(): GridPane {
        return gridPane
    }

}
