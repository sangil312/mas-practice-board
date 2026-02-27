package dev.article.service

import dev.article.IntegrationTestSupport
import dev.article.repository.ArticleRepository
import dev.article.service.request.CreateArticle
import dev.article.service.request.UpdateArticle
import dev.article.support.OffsetLimit
import dev.article.support.auth.User
import dev.article.support.error.ApiException
import dev.article.support.error.ErrorType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional

@Transactional
class ArticleServiceTest(
    private val articleService: ArticleService,
    private val articleRepository: ArticleRepository
) : IntegrationTestSupport() {

    @Test
    fun create() {
        val article = articleService.create(
            CreateArticle(
                title = "title",
                content = "content",
                boardId = 1L,
                userId = 10L
            )
        )
        articleRepository.flush()

        val foundArticle = articleRepository.findById(article.id).orElseThrow()

        assertThat(foundArticle.id).isPositive()
        assertThat(foundArticle.title).isEqualTo("title")
        assertThat(foundArticle.content).isEqualTo("content")
        assertThat(foundArticle.boardId).isEqualTo(1L)
        assertThat(foundArticle.userId).isEqualTo(10L)
    }

    @Test
    fun update() {
        val article = createArticle()

        val updatedArticle = articleService.update(
            UpdateArticle(
                articleId = article.id,
                userId = article.userId,
                title = "updated title",
                content = "updated content"
            )
        )
        articleRepository.flush()

        val foundArticle = articleRepository.findById(article.id).orElseThrow()

        assertThat(updatedArticle.id).isEqualTo(article.id)
        assertThat(foundArticle.title).isEqualTo("updated title")
        assertThat(foundArticle.content).isEqualTo("updated content")
    }

    @Test
    fun update_notFoundData() {
        val article = createArticle()

        assertThatThrownBy {
            articleService.update(
                UpdateArticle(
                    articleId = article.id,
                    userId = article.userId + 1,
                    title = "updated title",
                    content = "updated content"
                )
            )
        }
            .isInstanceOf(ApiException::class.java)
            .hasMessage(ErrorType.NOT_FOUND_DATA.message)
    }

    @Test
    fun findArticle() {
        val article = createArticle(
            title = "find title",
            content = "find content"
        )

        val foundArticle = articleService.findArticle(article.id)

        assertThat(foundArticle.id).isEqualTo(article.id)
        assertThat(foundArticle.title).isEqualTo("find title")
        assertThat(foundArticle.content).isEqualTo("find content")
        assertThat(foundArticle.boardId).isEqualTo(article.boardId)
        assertThat(foundArticle.userId).isEqualTo(article.userId)
    }

    @Test
    fun findArticle_notFoundData() {
        assertThatThrownBy {
            articleService.findArticle(999999L)
        }
            .isInstanceOf(ApiException::class.java)
            .hasMessage(ErrorType.NOT_FOUND_DATA.message)
    }

    @Test
    fun findArticles() {
        val firstArticle = createArticle(title = "first", boardId = 1L)
        val secondArticle = createArticle(title = "second", boardId = 1L)
        val thirdArticle = createArticle(title = "third", boardId = 1L)
        createArticle(title = "other board", boardId = 2L)

        val articleList = articleService.findArticles(1L, OffsetLimit(page = 1, size = 2))

        assertThat(articleList.totalCount).isEqualTo(3L)
        assertThat(articleList.articles).hasSize(2)
        assertThat(articleList.articles.map { it.id }).containsExactly(thirdArticle.id, secondArticle.id)
        assertThat(articleList.articles.map { it.title }).containsExactly("third", "second")
        assertThat(articleList.articles.map { it.boardId }).containsOnly(1L)
        assertThat(articleList.articles.map { it.id }).doesNotContain(firstArticle.id)
    }

    @Test
    fun delete() {
        val article = createArticle(boardId = 3L, userId = 30L)

        articleService.delete(User(article.userId), article.id)
        articleRepository.flush()

        assertThatThrownBy {
            articleService.findArticle(article.id)
        }
            .isInstanceOf(ApiException::class.java)
            .hasMessage(ErrorType.NOT_FOUND_DATA.message)
        assertThat(articleService.findArticles(3L, OffsetLimit(page = 1, size = 10)).articles).isEmpty()
        assertThat(articleService.findArticles(3L, OffsetLimit(page = 1, size = 10)).totalCount).isZero()
    }

    @Test
    fun delete_notFoundData() {
        val article = createArticle()

        assertThatThrownBy {
            articleService.delete(User(article.userId + 1), article.id)
        }
            .isInstanceOf(ApiException::class.java)
            .hasMessage(ErrorType.NOT_FOUND_DATA.message)
    }

    private fun createArticle(
        title: String = "title",
        content: String = "content",
        boardId: Long = 1L,
        userId: Long = 10L
    ) = articleService.create(
        CreateArticle(
            title = title,
            content = content,
            boardId = boardId,
            userId = userId
        )
    ).also {
        articleRepository.flush()
    }
}
