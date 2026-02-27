package dev.article.service.request

data class UpdateArticle(
    val articleId: Long,
    val userId: Long,
    val title: String,
    val content: String
)
