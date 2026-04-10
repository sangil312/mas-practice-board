package dev.article.service

import dev.article.domain.Article
import dev.article.repository.ArticleRepository
import dev.article.service.request.CreateArticle
import dev.article.service.request.UpdateArticle
import dev.article.service.response.ArticleList
import dev.article.support.OffsetLimit
import dev.article.support.auth.User
import dev.article.support.error.ApiException
import dev.article.support.error.ErrorType
import dev.snowflake.Snowflake
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
    private val snowflake: Snowflake
) {
    fun create(request: CreateArticle) : Article {
        return articleRepository.save(
            Article(
                id = snowflake.nextId(),
                title = request.title,
                content = request.content,
                boardId = request.boardId,
                userId = request.userId
            )
        )
    }

    @Transactional
    fun update(request: UpdateArticle): Article {
        val article = articleRepository.findActiveByIdAndUserId(request.articleId, request.userId)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        article.update(request.title, request.content)

        return article
    }

    fun findArticle(articleId: Long): Article {
        return articleRepository.findActiveById(articleId)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)
    }

    fun findArticles(boardId: Long, offsetLimit: OffsetLimit): ArticleList {
        return ArticleList(
            articleRepository.findAll(boardId, offsetLimit.offset, offsetLimit.limit),
            articleRepository.count(boardId)
        )
    }

    @Transactional
    fun delete(user: User, articleId: Long) {
        val article = articleRepository.findActiveByIdAndUserId(articleId, user.id)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        article.delete()
    }
}
