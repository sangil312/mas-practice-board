package dev.outboxmessagerelay

import dev.event.Event
import dev.event.EventPayload
import dev.event.EventType
import dev.snowflake.Snowflake
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class OutboxEventPublisher(
    private val snowflake: Snowflake,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun publish(eventType: EventType, payload: EventPayload, shardKey: Long) {
        val outbox = Outbox(
            id = snowflake.nextId(),
            shardKey = shardKey,
            eventType = eventType,
            payload = Event.of(
                eventId = snowflake.nextId(),
                type = eventType,
                payload = payload
            ).toJson()
        )

        applicationEventPublisher.publishEvent(OutboxEvent(outbox))
    }
}