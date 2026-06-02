package dev.article.service

import dev.article.domain.Article
import org.springframework.stereotype.Component

@Component
class ArticleEventHandler(

) {
    fun createdArticleEvent(article: Article) {

    }
}