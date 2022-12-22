package evolution.simulator

import kotlin.random.Random

class Genome(private val strategy: Strategy, collection: Collection<UInt>) : Iterable<UInt> {
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

        val minMutations = strategy.params.mutationMinNum
        val maxMutations = strategy.params.mutationMaxNum

        val indices: List<Int> = (0 until this.size)
                                .shuffled()
                                .slice(
                                    0 until (minMutations..maxMutations).random()
                                    )

        for (index in indices) {
            newGenes[index] = strategy.mutationStrategy(newGenes[index])
        }
        
        return Genome(strategy, newGenes)
    }

    override fun iterator(): GenomeCyclicIterator {
        return GenomeCyclicIterator(strategy, genes)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Genome
        if (genes != other.genes) return false
        return true
    }

    override fun hashCode(): Int {
        // Returns the hash code value for this list. The hash code of a list is defined to be the result of hashcode of its elements.
        // This ensures that list1.equals(list2) implies that list1.hashCode()==list2.hashCode() for any two lists, list1 and list2.
        return genes.hashCode()
    }

    companion object {
        fun generateRandom(strategy: Strategy): Genome {
            return Genome(strategy, (0u until strategy.params.genomeLength.toUInt()).map { it % 8u }.shuffled() )
        }
    }
}

class GenomeCyclicIterator(private val strategy: Strategy, private val genes: MutableList<UInt>): Iterator<UInt> {
    private var index: Int = -1

    val currentIndex: Int
        get() {return index}

    override fun hasNext(): Boolean {
        return true
    }

    override fun next(): UInt {
        index = strategy.animalStrategy(index, genes.size)
        return genes[index]
    }
}
