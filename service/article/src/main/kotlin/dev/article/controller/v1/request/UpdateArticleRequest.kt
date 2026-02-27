package dev.article.controller.v1.request

import dev.article.service.request.UpdateArticle
import dev.article.support.auth.User
import jakarta.validation.constraints.NotBlank

data class UpdateArticleRequest(
    @field:NotBlank
    val title: String,

    @field:NotBlank
    val content: String
) {
    fun toUpdateArticle(articleId: Long, user: User) = UpdateArticle(articleId, user.id, title, content)
}
