package evolution.simulator

import kotlin.math.floor
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
class RandomVariableTest {

    @Test fun intervalSearchNormalSimple() {
        val x = RandomVariable(mutableListOf(0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1))
        assertEquals(3, x.intervalBinarySearch(0.33))
    }

    @Test fun intervalSearchNormalRandom() {
        val x = RandomVariable(mutableListOf(0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1))
        repeat(10000) {
            val double = Random.nextDouble()
            assertEquals(floor(double*10).toInt(), x.intervalBinarySearch(double))
        }
    }

    @Test fun intervalSearchRandom() {
        repeat(100) {
            val unDistrib = ArrayList<Double>()
            repeat(1000) {
                unDistrib.add(Random.nextDouble(0.0, 10.0))
            }
            val x = RandomVariable(unDistrib)
            val distribFunList = x.distFunc.toList()
            repeat(1000) {
                val double = Random.nextDouble()
                var targetIdx = 0
                for (i in 0 until distribFunList.size - 1) {
                    if (distribFunList[i] < double && distribFunList[i + 1] > double) {
                        targetIdx = i
                        break
                    }
                }
                assertEquals(targetIdx, x.intervalBinarySearch(double))
            }
        }
    }
}
