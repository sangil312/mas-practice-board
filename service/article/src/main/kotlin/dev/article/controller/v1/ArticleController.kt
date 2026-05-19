package dev.article.controller.v1

import dev.article.controller.v1.request.CreateArticleRequest
import dev.article.controller.v1.request.UpdateArticleRequest
import dev.article.controller.v1.response.ArticleListResponse
import dev.article.controller.v1.response.ArticleResponse
import dev.article.support.response.Response
import dev.article.service.ArticleService
import dev.article.support.OffsetLimit
import dev.article.support.auth.User
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "게시글 API")
class ArticleController(
    private val articleService: ArticleService
) {
    @PostMapping("/v1/articles")
    @Operation(summary = "게시글 생성")
    fun createArticle(
        user: User,
        @Valid @RequestBody request: CreateArticleRequest
    ): Response<ArticleResponse> {
        val article = articleService.create(request.toCreateArticle(user))
        return Response.success(ArticleResponse.of(article))
    }

    @GetMapping("/v1/articles/{articleId}")
    @Operation(summary = "게시글 상세 조회")
    fun findArticle(
        @PathVariable articleId: Long
    ): Response<ArticleResponse>  {
        val article = articleService.findArticle(articleId)
        return Response.success(ArticleResponse.of(article))
    }

    @GetMapping("/v1/articles")
    @Operation(summary = "게시글 목록 조회")
    fun findArticles(
        @RequestParam boardId: Long,
        @RequestParam page: Int,
        @RequestParam size: Int
    ): Response<ArticleListResponse>  {
        val articleList = articleService.findArticles(boardId, OffsetLimit.of(page, size))
        return Response.success(ArticleListResponse.of(articleList))
    }

    @PutMapping("/v1/articles/{articleId}")
    @Operation(summary = "게시글 수정")
    fun updateArticle(
        user: User,
        @PathVariable articleId: Long,
        @Valid @RequestBody request: UpdateArticleRequest
    ): Response<ArticleResponse> {
        val article = articleService.update(request.toUpdateArticle(articleId, user))
        return Response.success(ArticleResponse.of(article))
    }

    @DeleteMapping("/v1/articles/{articleId}")
    @Operation(summary = "게시글 삭제")
    fun deleteArticle(
        user: User,
        @PathVariable articleId: Long
    ): Response<Any> {
        articleService.delete(user, articleId)
        return Response.success()
    }
}