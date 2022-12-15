package evolution.simulator

import java.util.*;

class CircularBuffer<T>(private val size: Int) {
    private val buffer: ArrayList<T> = ArrayList(size)
    private var top: Int = 0

    fun put(value: T) {
        top = (top + 1) % size
        buffer[top] = value 
    }

    fun get(index: Int): T {
        return buffer[(index+top)%size]
    }

    fun getTop(): Int {
        return top
    }
}

