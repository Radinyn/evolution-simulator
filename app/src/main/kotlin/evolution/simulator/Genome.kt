package evolution.simulator

import evolution.simulator.gui.EvolutionSimulator
import kotlin.math.abs
import kotlin.random.*

class Genome(collection: Collection<UInt>) : Iterable<UInt> {
    private val genes: MutableList<UInt> = ArrayList()

    val size: Int
        get() {
            return this.genes.size
        }

    init {
        this.genes.addAll(collection)
    }

    private fun slice(range: IntRange): List<UInt> {
        return this.genes.slice(range)
    }

    fun get(index: Int): UInt {
        return this.genes[index]
    }

    fun cross(other: Genome, factor: Float): Genome {
        var first: Genome = this
        var second: Genome = other
        var finalFactor: Float = factor

        if (Random.nextBoolean()) {
            first = second.also {second = first}
            finalFactor = 1-factor
        }

        assert(finalFactor in 0.0..1.0)

        val border: Int = (this.size * finalFactor).toInt()

        val newGenes: MutableList<UInt> = ArrayList()
        newGenes.addAll(first.slice(0..border))
        newGenes.addAll(second.slice((border+1) until this.size))

        val minMutations = EvolutionSimulator.params.mutationMinNum
        val maxMutations = EvolutionSimulator.params.mutationMaxNum

        val indices: List<Int> = (0 until this.size)
                                .shuffled()
                                .slice(
                                    0..(minMutations..maxMutations).random()
                                    )

        for (index in indices) {
            newGenes[index] = this.mutate(newGenes[index])
        }
        
        return Genome(newGenes)
    }

    private fun mutate(x: UInt): UInt {
        if (EvolutionSimulator.params.mutationType == MutationType.RANDOM) {
            return (abs(Random.nextInt()) % 8).toUInt()
        }
        return ((x.toInt()+8)+(listOf(1, -1).random()) % 8).toUInt()
    }

    companion object {
        fun generateRandom(size: Int): Genome {
            return Genome( (0 until size).map { (abs(Random.nextInt()) % 8).toUInt() } )
        }
    }

    override fun iterator(): Iterator<UInt> {
        return GenomeIterator(this.genes, this.size)
    }
}

class GenomeIterator(private val genes: MutableList<UInt>, private val size: Int): Iterator<UInt> {
    private var index: Int = -1
    override fun hasNext(): Boolean {
        return index < size-1
    }

    override fun next(): UInt {
        index++
        return genes[index]
    }

}
