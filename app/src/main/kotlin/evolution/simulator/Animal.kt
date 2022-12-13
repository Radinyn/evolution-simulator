package evolution.simulator

import evolution.simulator.gui.EvolutionSimulator
import kotlin.random.Random

class Animal(private var position: Vector2d,
             private var orientation: Orientation,
             private var energy: Int,
             private var age: Int,
             private var numOfChildren: Int,
             private val genome: Genome,
             private val params: SimulationParameters)
    : Comparable<Animal> {

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

    fun mate(other: Animal): Animal {
        assert(this.ableToMate() && other.ableToMate())
        val offspringGenome: Genome = genome.cross(other.genome, energy.toFloat()/other.energy.toFloat())
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
        if (energy != other.energy) return energy - other.energy
        else if (age != other.age) return age - other.age
        else if (numOfChildren != other.numOfChildren) return numOfChildren - other.numOfChildren
        else return (Random.nextInt() % 2) - 1 // -1 or 0 or 1
    }

    fun getEnergy(): Int {
        return energy
    }
}