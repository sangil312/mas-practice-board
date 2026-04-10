package dev.article.support

data class OffsetLimit private constructor(
    val offset: Long,
    val limit: Long,
) {
    companion object {
        operator fun invoke(page: Long, size: Long) = OffsetLimit(
            offset = (page - 1) * size,
            limit = size,
        )
    }
}