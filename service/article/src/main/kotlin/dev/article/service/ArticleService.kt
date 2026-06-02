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
    private val snowflake: Snowflake,
    private val articleRepository: ArticleRepository,
    private val boardArticleCountRepository: BoardArticleCountRepository,
    private val articleEventHandler: ArticleEventHandler
) {
    fun create(request: CreateArticle) : Article {
        val article = articleRepository.save(
            Article(
                id = snowflake.nextId(),
                title = request.title,
                content = request.content,
                boardId = request.boardId,
                userId = request.userId
            )
        )

        val result = boardArticleCountRepository.increase(request.boardId)
        if (result == 0) {
            boardArticleCountRepository.save(
                BoardArticleCount(boardId = request.boardId, articleCount = 1)
            )
        }

        articleEventHandler.createdArticleEvent(article)

        return article
    }

    @Transactional
    fun update(request: UpdateArticle): Article {
        val article = articleRepository.findByIdAndUserIdAndState(request.articleId, request.userId)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        article.update(request.title, request.content)

        return article
    }

    fun findArticle(articleId: Long): Article {
        return articleRepository.findByIdAndState(articleId)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)
    }

    fun findArticles(boardId: Long, offsetLimit: OffsetLimit): ArticleList {
        return ArticleList(
            articleRepository.findAll(boardId, offsetLimit.offset, offsetLimit.limit),
            articleRepository.count(boardId, offsetLimit.calculatePageLimit())
        )
    }

    @Transactional
    fun delete(user: User, articleId: Long) {
        val article = articleRepository.findByIdAndUserIdAndState(articleId, user.id)
            ?: throw ApiException(ErrorType.NOT_FOUND_DATA)

        article.delete()
    }
}
