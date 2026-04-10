package dev.article.controller.v1.request

import dev.article.service.request.CreateArticle
import dev.article.support.auth.User
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class CreateArticleRequest(
    @field:NotBlank
    val title: String,

    @field:NotBlank
    val content: String,

    @field:Positive
    val boardId: Long
) {
    fun toCreateArticle(user: User) = CreateArticle(title, content, boardId, user.id)
}
