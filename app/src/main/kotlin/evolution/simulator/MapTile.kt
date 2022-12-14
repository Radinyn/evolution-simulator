package evolution.simulator


/* class that handles map entities on same map position
handles mating and eating by strongest animal (one with the highest energy) also aging and plant growth on this tile
 */

class MapTile (var plant: Boolean, var favor: Int, val corpsePenalty: Int){
    private val animalEnterBuffer: ArrayList<Animal> = ArrayList()
    val animals = HashSet<Animal>()
    private var corpses: Int = 0

    val growthProbability: Double
        get() {
            return if (!plant) {(1.0 + favor.toDouble())/(1.0 + corpsePenalty * corpses.toDouble())} else {0.0}
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

    fun animalEnter(animal: Animal) {
        animals.add(animal)
    }

    fun animalLeave(animal: Animal) {
        animals.remove(animal)
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
        if (animals.isEmpty()) return // no eating candidate
        val animalToEat = animals.max()
        animals.remove(animalToEat)
        animalToEat.eat()
        animals.add(animalToEat)
        plant = false
    }

    fun agePhase() {
        if (animals.isEmpty()) return // no ageing candidate
        val animalsToAge = animals.iterator()
        while (animalsToAge.hasNext()) {
            animalsToAge.next().age()
        }
    }

    fun deathPhase() {
        if (animals.isEmpty()) return // no death candidate
        val dead = animals.filter{it.isDead()}.toList()
        for (animal in dead) {
            animals.remove(animal)
        }
    }
}