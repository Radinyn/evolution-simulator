package evolution.simulator

import kotlin.random.Random

class Animal(private var position: Vector2d,
             private var orientation: Orientation,
             private var energy: Int,
             private var age: Int,
             private var numOfChildren: Int,
             private val genome: Genome,
             private val params: SimulationParameters)
    : Comparable<Animal> {
    private var genomeIter: GenomeCyclicIterator = genome.cyclicIterator()

    val animalEnergy: Int
        get() {return this.energy}

    val animalAge: Int
        get() {return this.age}

    val animalPositon: Vector2d
        get() {return this.position}

    val animalNumOfChildren: Int
        get() {return this.numOfChildren}

    fun age() {
        energy -= 1 // energy loss per day (fixed value)
        age += 1
    }

    fun eat() {
        energy += params.plantEnergy
    }

    fun ableToMate(): Boolean {
        return energy >= params.stuffedThreshold
    }

    fun isDead(): Boolean {
        return energy <= 0
    }

    fun move(): Vector2d {
        return position + Orientation.fromInt(genomeIter.next().toInt()).toVec()
    }

    fun mate(other: Animal): Animal {
        assert(this.ableToMate() && other.ableToMate())
        val offspringGenome: Genome = genome.cross(other.genome, energy.toFloat() / (energy.toFloat() + other.energy.toFloat()))
        this.energy -= params.reproductionCost
        this.numOfChildren += 1
        other.energy -= params.reproductionCost
        other.numOfChildren += 1
        return Animal(position,
            orientation,
            2 * params.reproductionCost,
            0,
            0,
            offspringGenome,
            params)
    }

    override fun compareTo(other: Animal): Int {
        return if (energy != other.energy) energy - other.energy
        else if (age != other.age) age - other.age
        else if (numOfChildren != other.numOfChildren) numOfChildren - other.numOfChildren
        else (Random.nextInt() % 2) - 1 // -1 or 0 or 1
    }

    fun getEnergy(): Int {
        return energy
    }
}