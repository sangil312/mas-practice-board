package dev.article.service

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface BoardArticleCountRepository : JpaRepository<BoardArticleCount, Long> {

    @Modifying
    @Query(
        """
            update BoardArticleCount
            set articleCount = articleCount + 1
            where boardId = :boardId
        """
    )
    fun increase(boardId: Long): Int
}
