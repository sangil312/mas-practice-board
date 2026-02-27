package dev.article.repository

import dev.article.domain.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ArticleRepository : JpaRepository<Article, Long> {
    @Query(
        """
            select *
            from article
            where id = :articleId
              and user_id = :userId
              and state = 'ACTIVE'
        """,
        nativeQuery = true
    )
    fun findActiveByIdAndUserId(
        @Param("articleId") articleId: Long,
        @Param("userId") userId: Long
    ): Article?

    @Query(
        """
            select *
            from article
            where id = :articleId
              and state = 'ACTIVE'
        """,
        nativeQuery = true
    )
    fun findActiveById(
        @Param("articleId") articleId: Long
    ): Article?

    @Query(
        """
            select *
            from article
            where board_id = :boardId
              and state = 'ACTIVE'
            order by id desc
            limit :limit offset :offset
        """,
        nativeQuery = true
    )
    fun findAll(
        @Param("boardId") boardId: Long,
        @Param("offset") offset: Long,
        @Param("limit") limit: Long
    ): List<Article>

    @Query(
        """
            select count(*)
            from article
            where board_id = :boardId
              and state = 'ACTIVE'
        """,
        nativeQuery = true
    )
    fun count(
        @Param("boardId") boardId: Long
    ): Long
}
