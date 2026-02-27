package dev.article.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "article")
class Article(
    @Id
    val id: Long = 0,
    var title: String,
    var content: String,
    val boardId: Long,
    val userId: Long,
) : BaseEntity() {
    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}