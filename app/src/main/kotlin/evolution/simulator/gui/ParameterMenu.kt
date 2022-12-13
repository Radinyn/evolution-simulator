package evolution.simulator.gui

import evolution.simulator.*
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.geometry.Pos

class ParameterBox(
    private val label: Label,
    private val slider: IntSlider
) {
    private val vbox: VBox = VBox()

    init {
        this.vbox.alignment = Pos.BASELINE_CENTER
        this.vbox.children.addAll(label, slider.asNode())
    }

    fun asNode(): VBox {
        return this.vbox
    }

    fun getValue(): Int {
        return this.slider.getValue()
    }
}

class ParameterMenu {
    private val height           = ParameterBox(Label("Height"), IntSlider(10, 50, 1, 25))
    private val width            = ParameterBox(Label("Width"), IntSlider(10, 50, 1, 25))
    private val mapType          = ParameterBox(Label("Map type"), IntSlider(0,  1,  1, 0 ))
    private val initPlantsNum    = ParameterBox(Label("Plant count"), IntSlider(0,  20, 1, 5 ))
    private val plantEnergy      = ParameterBox(Label("Plant energy"), IntSlider(1,  10, 1, 3 ))
    private val plantGrowthRate  = ParameterBox(Label("Plant growth rate"), IntSlider(1,  5,  1, 1 ))
    private val plantGrowthType  = ParameterBox(Label("Plant growth type"), IntSlider(0,  1,  1, 0 ))
    private val initAnimalNum    = ParameterBox(Label("Animal count"), IntSlider(0,  30, 1, 5 ))
    private val initAnimalEnergy = ParameterBox(Label("Animal energy"), IntSlider(0,  50, 1, 4 ))
    private val stuffedThreshold = ParameterBox(Label("Stuffed threshold"), IntSlider(0,  50, 1, 10))
    private val reproductionCost = ParameterBox(Label("Reproduction cost"), IntSlider(0,  50, 1, 5 ))
    private val mutationMinNum   = ParameterBox(Label("Minimum mutations"), IntSlider(0,  30, 1, 0 ))
    private val mutationMaxNum   = ParameterBox(Label("Maximum mutations"), IntSlider(0,  30, 1, 10))
    private val mutationType     = ParameterBox(Label("Mutation type"), IntSlider(0,  1,  1, 0 ))
    private val genomeLength     = ParameterBox(Label("Genome length"), IntSlider(1,  30, 1, 10))
    private val animalBehavior   = ParameterBox(Label("Animal behavior"), IntSlider(0,  1,  1, 0 ) )

    private val menu: HBox = HBox()

    init {

        val left = VBox()
        left.children.addAll(
            height.asNode(),
            width.asNode(),
            mapType.asNode(),
            initPlantsNum.asNode(),
            plantEnergy.asNode(),
            plantGrowthRate.asNode(),
            plantGrowthType.asNode(),
            initAnimalNum.asNode(),
        )


        val right = VBox()
        right.children.addAll(
            initAnimalEnergy.asNode(),
            stuffedThreshold.asNode(),
            reproductionCost.asNode(),
            mutationMinNum.asNode(),
            mutationMaxNum.asNode(),
            mutationType.asNode(),
            genomeLength.asNode(),
            animalBehavior.asNode(),
        )
        
        menu.children.addAll(left, right)
        this.menu.alignment = Pos.BASELINE_CENTER

    }

    fun asNode(): HBox {
        return this.menu
    }

    fun get(): SimulationParameters {
        return SimulationParameters(
            height.getValue(),
            width.getValue(),
            if (mapType.getValue() == 0) MapType.GLOBE else MapType.PORTAL ,
            initPlantsNum.getValue(),
            plantEnergy.getValue(),
            plantGrowthRate.getValue(),
            if (plantGrowthType.getValue() == 0) GrowthType.EQUATOR else GrowthType.CORPSES,
            initAnimalNum.getValue(),
            initAnimalEnergy.getValue(),
            stuffedThreshold.getValue(),
            reproductionCost.getValue(),
            mutationMinNum.getValue(),
            mutationMaxNum.getValue(),
            if (mutationType.getValue() == 0) MutationType.RANDOM else MutationType.CORRECTION,
            genomeLength.getValue(),
            if (animalBehavior.getValue() == 0) AnimalBehavior.STRICT else AnimalBehavior.RANDOM,
        )

    }
}
