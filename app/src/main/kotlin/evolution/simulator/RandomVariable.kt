package evolution.simulator

import kotlin.random.Random

class RandomVariable(private var dist: MutableList<Double>) {
    lateinit var distFunc: Array<Double>

    init {
        normalize()
        distFunc()
    }

    private fun randomIndex(): Int {
        val random = Random.nextDouble()
        return intervalBinarySearch(random)
    }

    fun randomList(num: Int): Collection<Int> {
        val coll = ArrayList<Int>()
        var randomIndex: Int
        repeat(num) {
            if (!normalize()) return coll
            distFunc()
            randomIndex = randomIndex()
            coll.add(randomIndex)
            dist[randomIndex] = 0.0
        }
        return coll
    }

    private fun normalize(): Boolean {
        val sum = dist.sum()
        if (sum > 0.0) {
            dist = dist.map { it/sum }.toMutableList()
            return true
        }
        return false
    }

    private fun distFunc() {
        distFunc = Array(dist.size+1) {0.0}
        var sum = distFunc[0]
        dist.forEachIndexed{ index, it -> run {
            distFunc[index] = sum
            sum += it
        } }
        distFunc[dist.size] = 1.0
    }
    fun intervalBinarySearch(key: Double): Int {
        var index = 0
        var low = 0
        var high = this.dist.size
        while (low <= high) {
            val mid = low + (high - low) / 2
            if (this.distFunc[mid+1] <= key) {
                // right and left side of interval is less than key -> go right
                low = mid + 1
            } else if (this.distFunc[mid] > key) {
                // right and left side of interval is higher than key -> go left
                high = mid - 1
            } else if (this.distFunc[mid] <= key && this.distFunc[mid+1] > key ) {
                index = mid
                break
            }
        }
        return index
    }
}
