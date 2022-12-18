package evolution.simulator.gui

import evolution.simulator.Vector2d
import javafx.application.Platform
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.RowConstraints
import java.io.FileInputStream

class Grid(width: Int, height: Int) {
    private val map: MutableMap<Vector2d, ArrayList<GridElementBox>> = HashMap()
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
    }

    fun update() {
        Platform.runLater {
            gridPane.children.clear()
            for (i in 0 until width) {
                for (j in 0 until height) {
<<<<<<< HEAD
                    val image = Image(FileInputStream("src/main/resources/test.jpeg"))
                    val imageView = ImageView(image)
                    imageView.fitWidth = 40.0
                    imageView.fitHeight = 40.0
                    gridPane.add(GridElementBox(listOf(imageView)).asNode(), i, j)
=======
                    gridPane.add(GridElementBox("$i , $j").asNode(), i, j)
>>>>>>> radinyn/dec15
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
