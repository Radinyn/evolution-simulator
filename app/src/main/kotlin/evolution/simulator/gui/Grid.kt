package evolution.simulator.gui;

import evolution.simulator.Vector2d

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

class Grid {
    private val map: MutableMap<Vector2d, ArrayList<GridElementBox>> = HashMap()
    public val gridPane = GridPane()

    public val height: Int
        get() {
            return gridPane.rowCount
        }

    public val width: Int
        get() {
            return gridPane.columnCount
        }
    
    constructor (width: Int, height: Int) {
        for (i in 1..height) {
            this.gridPane.rowConstraints.add(RowConstraints(20.0))
        }
        for (i in 1..width) {
            this.gridPane.columnConstraints.add(ColumnConstraints(20.0))
        }
    }

    public fun update() {
        Platform.runLater({
            gridPane.children.clear()
            for (i in 0..width-1) {
                for (j in 0..height-1) {
                    gridPane.add(GridElementBox(i.toString() + " , "+ j.toString()).vbox, i, j)
                }
            }

            // for ((vec2, nodeList) in this.map) {
            //     gridPane.add(nodeList[0].vbox, vec2.x, vec2.y)
            // }
        })
    }

}
