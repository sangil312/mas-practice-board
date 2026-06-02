package dev.event

import dev.event.payload.ArticleCreatedEventPayload

enum class EventType(
    val payloadClass: Class<out EventPayload>,
    val topic: String
) {
    ARTICLE_CREATED(ArticleCreatedEventPayload::class.java, Topic.BOARD_ARTICLE),
    ;

    class Topic {
        companion object {
            const val BOARD_ARTICLE = "board-article"
        }
    }
}
