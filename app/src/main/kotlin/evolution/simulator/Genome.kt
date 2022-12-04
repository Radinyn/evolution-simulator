package evolution.simulator

import kotlin.random.*;

class Genome {
    private val genes: MutableList<UInt> = ArrayList()

    val size: Int
        get() {
            return this.genes.size
        }

    constructor(collection: Collection<UInt>) {
        this.genes.addAll(collection)
    }

    public fun slice(range: IntRange): List<UInt> {
        return this.genes.slice(range)
    }

    public fun get(index: Int): UInt {
        return this.genes[index]
    }

    public fun cross(other: Genome, factor: Float): Genome {
        var first: Genome = this
        var second: Genome = other
        var final_factor: Float = factor

        if (Random.nextBoolean()) {
            first = second.also {second = first}
            final_factor = 1-factor
        }

        assert(0 <= final_factor && final_factor <= 1)

        var border: Int = (this.size * final_factor).toInt()

        val newGenes: MutableList<UInt> = ArrayList()
        newGenes.addAll(first.slice(0..border))
        newGenes.addAll(second.slice((border+1)..(this.size-1)))

        // TODO: REPLACE WITH PARAMS
        val minMutations = 0
        val maxMutations = this.size-1

        var indices: List<Int> = (0..(this.size-1))
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
            return (Math.abs(Random.nextInt()) % 8).toUInt()
        }
        return ((x.toInt()+8)+(listOf(1, -1).random()) % 8).toUInt()
    }
}