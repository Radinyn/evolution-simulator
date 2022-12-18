package evolution.simulator.gui

<<<<<<< HEAD
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
=======
import javafx.scene.chart.LineChart; 
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.XYChart; 
>>>>>>> radinyn/dec15

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

<<<<<<< HEAD
    fun asNode(): LineChart<Number, Number> {
        return lineChart
    }

    fun addPoint(x: Number, y: Number) {
=======
    public fun asNode(): LineChart<Number, Number> {
        return lineChart
    }

    public fun addPoint(x: Number, y: Number) {
>>>>>>> radinyn/dec15
        series.data.add(XYChart.Data(x, y))
    } 
}
