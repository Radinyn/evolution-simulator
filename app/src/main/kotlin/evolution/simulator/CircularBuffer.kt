package evolution.simulator

import java.util.*;

class CircularBuffer<T>(private val size: Int) {
    private val buffer: ArrayList<T> = ArrayList(size)
    private var top: Int = 0

    fun put(value: T) {
        buffer[top] = value
        top = (top + 1) % size
    }

    fun getCurrentIndex(): Int {
        return top-1
    }
}

