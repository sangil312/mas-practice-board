package dev.snowflake

class Snowflake(
    private val nodeId: Long,
    private val startTimeMillis: Long = DEFAULT_START_TIME_MILLIS
) {
    companion object {
        const val DEFAULT_START_TIME_MILLIS = 1704067200000L
        private const val NODE_ID_BITS = 10
        private const val SEQUENCE_BITS = 12
        private const val MAX_NODE_ID = (1L shl NODE_ID_BITS) - 1
        private const val MAX_SEQUENCE = (1L shl SEQUENCE_BITS) - 1
    }

    private var lastTimeMillis = -1L
    private var sequence = 0L

    init {
        require(nodeId in 0..MAX_NODE_ID) { "nodeId must be between 0 and $MAX_NODE_ID" }
    }

    @Synchronized
    fun nextId(): Long {
        var currentTimeMillis = System.currentTimeMillis()

        if (currentTimeMillis < lastTimeMillis) throw IllegalStateException("Invalid time")

        if (currentTimeMillis == lastTimeMillis) {
            sequence = (sequence + 1) and MAX_SEQUENCE
            if (sequence == 0L) {
                currentTimeMillis = waitNextMillis(lastTimeMillis)
            }
        } else {
            sequence = 0L
        }

        val elapsedTimeMillis = currentTimeMillis - startTimeMillis
        require(elapsedTimeMillis >= 0) { "Current time must be greater than startTimeMillis" }

        lastTimeMillis = currentTimeMillis

        return (elapsedTimeMillis shl (NODE_ID_BITS + SEQUENCE_BITS)) or (nodeId shl SEQUENCE_BITS) or sequence
    }

    private fun waitNextMillis(lastTimeMillis: Long): Long {
        var currentTimeMillis = System.currentTimeMillis()

        while (currentTimeMillis <= lastTimeMillis) {
            currentTimeMillis = System.currentTimeMillis()
        }

        return currentTimeMillis
    }
}
