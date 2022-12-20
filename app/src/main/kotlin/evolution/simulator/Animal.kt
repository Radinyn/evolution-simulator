package evolution.simulator

import kotlin.random.Random

class Animal(private var position: Vector2d,
             private var orientation: Orientation,
             private var energy: Int,
             private var age: Int,
             private var childrenCount: Int,
             private var plantCount: Int,
             private val genome: Genome,
             private val strategy: Strategy)
    : Comparable<Animal>{
    private var genomeIterator: GenomeCyclicIterator = genome.iterator()

    val animalEnergy: Int
        get() {return this.energy}

    val animalAge: Int
        get() {return this.age}

    val animalPosition: Vector2d
        get() {return this.position}

    val animalChildrenCount: Int
        get() {return this.childrenCount}

    val animalPlantCount: Int
        get() {return this.plantCount}

    val animalOrientation: Orientation
        get() {return this.orientation}

    fun age() {
        energy -= 1 // energy loss per day (fixed value)
        age += 1
    }

    fun eat() {
        energy += strategy.params.plantEnergy
        plantCount += 1
    }

    fun ableToMate(): Boolean {
        return energy >= strategy.params.stuffedThreshold && energy >= strategy.params.reproductionCost
    }

    fun isDead(): Boolean {
        return energy <= 0
    }

    fun move(transform: (pos: Vector2d) -> Pair<Vector2d, Int>): Vector2d {
        orientation = Orientation.fromInt(genomeIterator.next().toInt())
        val pair = transform(position + orientation.toVec())
        position = pair.first
        energy -= pair.second
        return position
    }

    fun mate(other: Animal): Animal {
        assert(this.ableToMate() && other.ableToMate())
        val offspringGenome: Genome = genome.cross(other.genome, energy.toFloat() / (energy.toFloat() + other.energy.toFloat()))
        this.energy -= strategy.params.reproductionCost
        this.childrenCount += 1
        other.energy -= strategy.params.reproductionCost
        other.childrenCount += 1
        return Animal(
            position,
            orientation,
            2 * strategy.params.reproductionCost,
            0,
            0,
            0,
            offspringGenome,
            strategy)
    }

    override fun compareTo(other: Animal): Int {
        return if (energy != other.energy) energy - other.energy
        else if (age != other.age) age - other.age
        else if (childrenCount != other.childrenCount) childrenCount - other.childrenCount
        else (Random.nextInt() % 2) - 1 // -1 or 0 or 1
    }
}