package evolution.simulator

import kotlin.math.abs
import kotlin.random.*

class Genome: Iterable<UInt> {
    private val genes: MutableList<UInt> = ArrayList()

    val size: Int
        get() {
            return this.genes.size
        }

    constructor(collection: Collection<UInt>) {
        this.genes.addAll(collection)
    }

    fun slice(range: IntRange): List<UInt> {
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

        // TODO: REPLACE WITH PARAMS
        val minMutations = 0
        val maxMutations = this.size-1

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
        // TODO: REPLACE WITH PARAMS
        if (true) {
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

class GenomeIterator(val genes: MutableList<UInt>, val size: Int): Iterator<UInt> {
    var index: Int = -1
    override fun hasNext(): Boolean {
        return index < size-1
    }

    override fun next(): UInt {
        index++
        return genes[index]
    }

}
