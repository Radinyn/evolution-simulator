package evolution.simulator

import evolution.simulator.gui.Resources
import javafx.scene.Node
import javafx.scene.image.ImageView


/* class that handles map entities on same map position
handles mating and eating by strongest animal (one with the highest energy) also aging and plant growth on this tile
 */

class MapTile ( private val growthProbFunc: (Int) -> Double, private val resources: Resources): IDisplay {
    val animals = HashSet<Animal>()
    private val animalEnterBuffer: ArrayList<Animal> = ArrayList()
    private val animalLeaveBuffer: ArrayList<Animal> = ArrayList()
    private var corpses = 0
    private var plant = false

    val growthProbability: Double
        get() {
            return if (!plant) {growthProbFunc(corpses)} else {0.0}
        }

    val hasPlant: Boolean
        get() {
            return plant
        }

    val animalsSorted: List<Animal>
        get() {
            return animals.toList().sortedDescending()
        }

    fun animalEnterBuffer(animal: Animal) {
        animalEnterBuffer.add(animal)
    }

    fun animalEnterBufferApply() {
        animalEnterBuffer.stream().forEach { animals.add(it) }
        animalEnterBuffer.clear()
    }

    fun animalLeaveBuffer(animal: Animal) {
        animalLeaveBuffer.add(animal)
    }

    fun animalLeaveBufferApply() {
        animalLeaveBuffer.stream().forEach { animals.remove(it) }
        animalLeaveBuffer.clear()
    }

    fun growPlant() {
        plant = true
    }

    fun matingPhase() {
        if (animals.size < 2) return // no two mating candidates
        val animalsToMate = animals.toList().sortedDescending().filter{it.ableToMate()}
        if (animalsToMate.size < 2) return // no two mating candidates
        animals.add(animalsToMate[0].mate(animalsToMate[1]))
    }

    fun eatingPhase() {
        if (animals.isEmpty() || !hasPlant) return // no eating candidate
        val animalToEat = animals.max()
        animals.remove(animalToEat)
        animalToEat.eat()
        animals.add(animalToEat)
        plant = false
    }

    fun agePhase() {
        if (animals.isEmpty()) return // no ageing candidate
        for (animal in animals) {
            animal.age()
        }
    }

    fun deathPhase(iterationNumber: ULong) {
        if (animals.isEmpty()) return // no death candidate
        val dead = animals.filter{it.isDead()}.toList()
        for (animal in dead) {
            animal.deathDay = iterationNumber
            animals.remove(animal)
        }
    }

    override fun display(): Collection<Node> {
        val nodes = ArrayList<Node>()
        if (plant) {
            val imageView = ImageView(resources.plantImage)
            imageView.fitWidth = resources.plantImageWidth
            imageView.fitHeight = resources.plantImageHeight
            nodes.add(imageView)
        }

        for (animal in animals) {
            val imageView = ImageView(resources.animalImage)
            imageView.fitWidth = resources.animalImageWidth
            imageView.fitHeight = resources.animalImageHeight
            imageView.rotate = animal.animalOrientation.toDegrees()
            nodes.add(imageView)
        }
        return nodes
    }
}