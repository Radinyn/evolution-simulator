package evolution.simulator.gui

import evolution.simulator.CircularBuffer
import evolution.simulator.Statistics
import javafx.scene.control.*
import kotlin.reflect.full.memberProperties
import kotlin.random.Random
import javafx.application.Platform

class StatisticsCharts {
    private val buffer = CircularBuffer<Statistics>(100)
    private val charts = HashMap<String, Chart>()
    private val tabPane = TabPane()

    init {
        for (member in Statistics::class.memberProperties) {
            if (member.name == "numOfIterations") continue
            if (member.name == "bestGenome") continue


            charts[member.name] = Chart()
            tabPane.tabs.add(Tab(member.name, charts[member.name]!!.asNode()))
        }
        
    }

    fun update(statistics: Statistics) {
        val x = statistics.numOfIterations
        for (member in Statistics::class.memberProperties) { 
            if (member.name == "numOfIterations") continue
            if (member.name == "bestGenome") continue

            val y = member.get(statistics)
            if (y is UInt) {
                charts[member.name]!!.addPoint(x.toDouble(), y.toDouble())
            } else {
                charts[member.name]!!.addPoint(x.toDouble(), (y as Number).toDouble())
            } 
        }
    }

    fun asNode(): TabPane {
        return tabPane
    }
}