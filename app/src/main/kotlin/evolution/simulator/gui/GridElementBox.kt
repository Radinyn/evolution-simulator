package evolution.simulator.gui

import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.VBox

class GridElementBox(children: Collection<Node>) {
    private val vbox: VBox = VBox()

    init {
        this.vbox.children.addAll(children)
        this.vbox.alignment = Pos.CENTER
    }

    fun asNode(): VBox {
        return vbox
    }
}
