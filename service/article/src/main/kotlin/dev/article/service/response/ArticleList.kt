package dev.article.service.response

import dev.article.domain.Article

data class ArticleList(
    val articles: List<Article>,
    val totalCount: Long
)
