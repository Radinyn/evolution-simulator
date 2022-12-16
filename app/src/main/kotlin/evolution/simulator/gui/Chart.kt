package evolution.simulator.gui

import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart

class Chart {
    private val lineChart: LineChart<Number, Number>
    private val xAxis = NumberAxis()
    private val yAxis = NumberAxis()
    private val series = XYChart.Series<Number, Number>()

    init {
        lineChart = LineChart(xAxis, yAxis)
        xAxis.isAutoRanging = true
        yAxis.isAutoRanging = true
        lineChart.autosize()
        lineChart.data.add(series)
    }

    fun asNode(): LineChart<Number, Number> {
        return lineChart
    }

    fun addPoint(x: Number, y: Number) {
        series.data.add(XYChart.Data(x, y))
    } 
}
