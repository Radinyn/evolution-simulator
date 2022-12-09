package evolution.simulator

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GenomeTest {
    @Test fun rangeAndSize() {
        var genome: Genome
        var maximum: UInt
        var minium: UInt
        repeat(1000) {
            genome = Genome.generateRandom(1000)
            maximum = genome.max()
            minium = genome.min()
            assertEquals(1000, genome.size)
            assertTrue( maximum <= 7u && minium >= 0u )
        }
    }

    @Test fun iterator() {
        var genome: Genome
        repeat(1000) {
            genome = Genome.generateRandom(1000)
            assertEquals(genome.size, genome.count())
            genome.forEachIndexed { index, value -> assertEquals(genome.get(index), value) }
        }

    }
}