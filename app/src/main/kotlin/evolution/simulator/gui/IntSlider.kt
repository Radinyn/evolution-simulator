package evolution.simulator.gui;

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
        slider.setSnapToTicks(true)
        slider.setShowTickMarks(true)
        slider.setShowTickLabels(true)
    }

    public fun asNode(): Slider {
        return slider
    }

    public fun getValue(): Int {
        return slider.value.toInt()
    }
}
