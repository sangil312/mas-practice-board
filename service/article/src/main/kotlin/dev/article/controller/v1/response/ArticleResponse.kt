package dev.article.controller.v1.response

import com.fasterxml.jackson.annotation.JsonFormat
import dev.article.domain.Article
import java.time.LocalDateTime

data class ArticleResponse(
    val id: Long,
    val title: String,
    val content: String,
    val boardId: Long,
    val userId: Long,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
