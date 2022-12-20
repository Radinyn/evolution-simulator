package evolution.simulator.gui

import javafx.stage.Stage
import javafx.scene.Scene

import javafx.scene.layout.*
import javafx.scene.control.*

import evolution.simulator.*

class SimulationWindow(private val params: SimulationParameters, private val stage: Stage) {

    fun start() {
        // val chart = Chart()
        // chart.addPoint(0, 0)
        // chart.addPoint(20, 14)
        // chart.addPoint(60, 43)
        // chart.addPoint(80, 32)
        // chart.addPoint(100, 43)

        val charts = StatisticsCharts()

        // val strategy = Strategy(SimulationParameters(
        //     animalBehavior = AnimalBehavior.STRICT,
        //     genomeLength = 10,
        //     initAnimalEnergy = 10,
        //     initAnimalNum = 10,
        //     initPlantsNum = 10,
        //     mapType = MapType.GLOBE,
        //     mutationMaxNum = 10,
        //     mutationMinNum = 0,
        //     mutationType = MutationType.RANDOM,
        //     plantEnergy = 10,
        //     plantGrowthRate = 100,
        //     plantGrowthType = GrowthType.EQUATOR,
        //     reproductionCost = 10,
        //     width = 30,
        //     height = 30,
        //     stuffedThreshold = 5,
        // ))

        val strategy = Strategy(params)
        val map = Map(strategy, Resources())
        val grid = Grid(params.width, params.height, map)

        map.plantGrowthPhase()
        map.place(Animal(Vector2d(3, 3), Orientation.NORTH_EAST, params.initAnimalEnergy, 0, 0, 0, Genome(strategy, ArrayList()), strategy))
        grid.update()


        val s1 = Statistics(0u, 2u, 1u, 1u, Genome(strategy, ArrayList()), 69.0, 420.0)
        val s2 = Statistics(1u, 4u, 7u, 1u, Genome(strategy, ArrayList()), 96.0, 240.0)
        val s3 = Statistics(2u, 3u, 20u, 3u, Genome(strategy, ArrayList()), 69.0, 42.0)


        charts.update(s1)
        charts.update(s2)
        charts.update(s3)


        
        val hbox = HBox()
        hbox.children.addAll(grid.asNode(), charts.asNode())


        val hbox2 = HBox()
        val playButton = ToggleButton("Play")
        val recordButton = ToggleButton("Record")
        hbox2.children.addAll(playButton, recordButton)

        val vbox = VBox(hbox, hbox2)

        val scene = Scene(vbox, grid.width*40.0 + 400, grid.height*40.0 + 20)

        stage.title = "Evolution Simulator"
        stage.scene = scene
        stage.show()
    }
}
