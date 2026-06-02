package dev.article.service

import dev.article.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "board_article_count")
class BoardArticleCount(
    @Id
    val boardId: Long,
    val articleCount: Long
) : BaseEntity()
