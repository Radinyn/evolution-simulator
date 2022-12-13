package evolution.simulator.gui

import evolution.simulator.Vector2d
import javafx.application.Platform
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.RowConstraints

class Grid(width: Int, height: Int) {
    private val map: MutableMap<Vector2d, ArrayList<GridElementBox>> = HashMap()
    val gridPane = GridPane()

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
            this.gridPane.rowConstraints.add(RowConstraints(20.0))
        }
        for (i in 1..width) {
            this.gridPane.columnConstraints.add(ColumnConstraints(20.0))
        }
    }

    fun update() {
        Platform.runLater {
            gridPane.children.clear()
            for (i in 0 until width) {
                for (j in 0 until height) {
                    gridPane.add(GridElementBox("$i , $j").vbox, i, j)
                }
            }

            // for ((vec2, nodeList) in this.map) {
            //     gridPane.add(nodeList[0].vbox, vec2.x, vec2.y)
            // }
        }
    }

}
