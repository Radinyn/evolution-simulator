package evolution.simulator

import javafx.scene.Node

interface IDisplay {
    fun display(): Collection<Node>
}