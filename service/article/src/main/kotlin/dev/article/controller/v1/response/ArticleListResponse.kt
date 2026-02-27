package dev.article.controller.v1.response

import dev.article.service.response.ArticleList

data class ArticleListResponse(
    val articles: List<ArticleResponse>,
    val totalCount: Long
) {
    companion object {
        fun of(articleList: ArticleList): ArticleListResponse {
            return ArticleListResponse(
                articleList.articles.map { ArticleResponse.of(it) },
                articleList.totalCount
            )
        }
    }
}
