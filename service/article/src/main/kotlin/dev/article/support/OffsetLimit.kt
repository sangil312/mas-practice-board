package dev.article.support

data class OffsetLimit private constructor(
    val offset: Int,
    val limit: Int,
) {
    fun calculatePageLimit(): Int = limit * MOVEABLE_PAGE_COUNT

    companion object {
        private const val MOVEABLE_PAGE_COUNT = 10

        fun of(page: Int, size: Int) = OffsetLimit(
            offset = (page - 1) * size,
            limit = size,
        )
    }
}