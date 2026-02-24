package dev.snowflake

import java.util.random.RandomGenerator

class Snowflake {
    private val nodeIdBits = 10
    private val sequenceBits = 12
    private val maxNodeId = (1L shl nodeIdBits)
    private val maxSequence = (1L shl sequenceBits)
    private val nodeId = RandomGenerator.getDefault().nextLong(maxNodeId + 1)
    private val startTimeMillis = 1704067200000L
    private var lastTimeMillis = startTimeMillis
    private var sequence = 0L

    @Synchronized
    fun nextId(): Long {
        var currentTimeMillis = System.currentTimeMillis()

        if (currentTimeMillis < lastTimeMillis) throw IllegalStateException("Invalid Time")

        if (currentTimeMillis == lastTimeMillis) {
            sequence = (sequence + 1) and maxSequence
            if (sequence == 0L) {
                currentTimeMillis = waitNextMillis(currentTimeMillis)
            }
        } else {
            sequence = 0
        }

        lastTimeMillis = currentTimeMillis

        return (((currentTimeMillis - startTimeMillis) shl (nodeIdBits + sequenceBits))
                or (nodeId shl sequenceBits)
                or sequence)
    }

    private fun waitNextMillis(currentTimestamp: Long): Long {
        var currentTimestamp = currentTimestamp
        while (currentTimestamp <= lastTimeMillis) {
            currentTimestamp = System.currentTimeMillis()
        }
        return currentTimestamp
    }
}