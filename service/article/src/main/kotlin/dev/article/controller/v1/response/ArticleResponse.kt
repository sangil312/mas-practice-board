package dev.article.controller.v1.response

import dev.article.domain.Article
import java.time.LocalDateTime

data class ArticleResponse(
    val id: Long,
    val title: String,
    val content: String,
    val boardId: Long,
    val userId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun of(article: Article): ArticleResponse {
            return ArticleResponse(
                id = article.id,
                title = article.title,
                content = article.content,
                boardId = article.boardId,
                userId = article.userId,
                createdAt = article.createdAt,
                updatedAt = article.updatedAt
            )
        }
    }
}
