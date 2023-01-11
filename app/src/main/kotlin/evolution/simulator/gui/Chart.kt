package evolution.simulator.gui

import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.application.Platform

class Chart {
    private val lineChart: LineChart<Number, Number>
    private val xAxis = NumberAxis()
    private val yAxis = NumberAxis()
    private val series = XYChart.Series<Number, Number>()

    init {
        lineChart = LineChart(xAxis, yAxis)
        xAxis.isAutoRanging = true
        xAxis.isForceZeroInRange = false
        yAxis.isAutoRanging = true
        lineChart.isLegendVisible = false
        lineChart.createSymbols = false
        lineChart.autosize()
        lineChart.data.add(series)
    }

    fun asNode(): LineChart<Number, Number> {
        return lineChart
    }

    fun addPoint(x: Number, y: Number) {
        Platform.runLater {
            if (series.data.size > 100) {
                series.data.removeFirst()
            }
            

            series.data.add(XYChart.Data(x, y))
        }
    }

    fun clear() {
        Platform.runLater {
            series.data.clear()
        }
    }
}
