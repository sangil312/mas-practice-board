package dev.article.service.request

data class CreateArticle(
    val title: String,
    val content: String,
    val boardId: Long,
    val userId: Long
)