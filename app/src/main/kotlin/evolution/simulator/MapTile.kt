package evolution.simulator

import java.util.TreeSet
import java.util.stream.Stream

/* class that handles map entities on same map position
handles mating and eating by strongest animal (one with the highest energy) also aging and plant growdth on this tile
 */

class MapTile() {
    private val animals = HashSet<Animal>()
    var plant: Boolean = false
    var corpses: UInt = 0u

    val animalsSorted: List<Animal>
        get() {
            return animals.toList().sortedDescending()
        }

    fun animalEnter(animal: Animal) {
        animals.add(animal)
    }

    fun animalLeave(animal: Animal) {
        animals.remove(animal)
    }

    fun matingPhase() {
        val animalsToMate = animals.toList().sortedDescending().stream().filter{it.ableToMate()}.limit(2).toList()
        if (animalsToMate.size < 2) return // no two mating candidates
        animals.add(animalsToMate.first().mate(animalsToMate.last()))
    }

    fun eatingPhase() {
        if (animals.isEmpty()) return // no eating candidate
        val animalToEat = animals.last()
        animals.remove(animalToEat)
        animalToEat.eat()
        animals.add(animalToEat)
        plant = false
    }

    fun agePhase() {
        val animalsToAge = animals.iterator()
        while (animalsToAge.hasNext()) {
            animalsToAge.next().age()
        }
    }

    fun deathPhase() {
        val dead = animals.stream().filter{it.isDead()}.toList()
        for (animal in dead) {
            animals.remove(animal)
        }
    }
}