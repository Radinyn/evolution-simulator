package evolution.simulator.gui

import javafx.scene.control.*

class IntSlider(
    low: Int,
    high: Int,
    step: Int,
    start: Int, 
) {
    private var slider: Slider

    init {
        this.slider = Slider(low.toDouble(), high.toDouble(), start.toDouble())
        slider.blockIncrement = step.toDouble()
        slider.minorTickCount = 0
        slider.majorTickUnit = step.toDouble()
        slider.isSnapToTicks = true
        slider.isShowTickMarks = true
        slider.isShowTickLabels = true
    }

    fun asNode(): Slider {
        return slider
    }

    fun getValue(): Int {
        return slider.value.toInt()
    }
}
