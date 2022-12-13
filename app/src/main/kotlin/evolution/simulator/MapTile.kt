package evolution.simulator

import kotlin.streams.toList

/* class that handles map entities on same map position
handles mating and eating by strongest animal (one with the highest energy) also aging and plant growdth on this tile
 */

class MapTile {
    private val animals = HashSet<Animal>()
    var plant: Boolean = false
    var corpses: UInt = 0u

    val animalsSorted: List<Animal>
        get() {
            return animals.toList().sortedDescending()
        }

    val animalStrongest: Animal
        get() {
            return animals.max()
        }

    fun animalEnter(animal: Animal) {
        animals.add(animal)
    }

    fun animalLeave(animal: Animal) {
        animals.remove(animal)
    }

    fun matingPhase() {
        val animalsToMate = animals.toList().sortedDescending().stream().filter{it.ableToMate()}.toList()
        if (animalsToMate.size < 2) return // no two mating candidates
        animals.add(animalsToMate[0].mate(animalsToMate[1]))
    }

    fun eatingPhase() {
        if (animals.isEmpty()) return // no eating candidate
        val animalToEat = animalStrongest
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