package evolution.simulator

import kotlin.math.abs
import kotlin.random.Random

class Genome(private val params: SimulationParameters, collection: Collection<UInt>) : Iterable<UInt> {
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

        val minMutations = params.mutationMinNum
        val maxMutations = params.mutationMaxNum

        val indices: List<Int> = (0 until this.size)
                                .shuffled()
                                .slice(
                                    0 until (minMutations..maxMutations).random()
                                    )

        for (index in indices) {
            newGenes[index] = this.mutate(newGenes[index])
        }
        
        return Genome(params, newGenes)
    }

    private fun mutate(x: UInt): UInt {
        if (params.mutationType == MutationType.RANDOM) {
            return (abs(Random.nextInt()) % 8).toUInt()
        }
        return ((x.toInt()+8)+(listOf(1, -1).random()) % 8).toUInt()
    }

    override fun iterator(): GenomeIterator {
        return GenomeIterator(this.genes)
    }

    fun cyclicIterator(): GenomeCyclicIterator {
        return GenomeCyclicIterator(this.genes)
    }

    companion object {
        fun generateRandom(params: SimulationParameters): Genome {
            return Genome(params, (0u until params.genomeLength.toUInt()).shuffled() )
        }
    }
}

class GenomeIterator(private val genes: MutableList<UInt>): Iterator<UInt> {
    private var index: Int = -1
    override fun hasNext(): Boolean {
        return index < genes.size - 1
    }

    override fun next(): UInt {
        index += 1
        return genes[index]
    }
}

class GenomeCyclicIterator(private val genes: MutableList<UInt>): Iterator<UInt> {
    private var index: Int = -1
    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): UInt {
        index = (index + 1) % genes.size
        return genes[index]
    }
}
