package dev.article.repository

import dev.article.domain.Article
import dev.article.domain.EntityState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArticleRepository : JpaRepository<Article, Long> {
    fun findByIdAndUserIdAndState(
        articleId: Long,
        userId: Long,
        state: EntityState = EntityState.ACTIVE
    ): Article?

    fun findByIdAndState(
        articleId: Long,
        state: EntityState = EntityState.ACTIVE
    ): Article?

    @Query(
        """
            select article.*
            from article article
            join (
                select id
                from article
                where board_id = :boardId
                  and state = :state
                order by id desc
                limit :limit offset :offset
            ) sub on article.id = sub.id
            order by article.id desc
        """,
        nativeQuery = true
    )
    fun findAll(
        boardId: Long,
        offset: Int,
        limit: Int,
        state: String = EntityState.ACTIVE.name
    ): List<Article>

    @Query(
        """
            select count(*)
            from (
                select id
                from article
                where board_id = :boardId
                    and state = :state
                limit :limit
            ) sub
        """,
        nativeQuery = true
    )
    fun count(
        boardId: Long,
        limit: Int,
        state: String = EntityState.ACTIVE.name
    ): Long
}
