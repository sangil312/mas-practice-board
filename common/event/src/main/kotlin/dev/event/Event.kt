package dev.event

import dev.dataserializer.DataSerializer

data class Event<T : EventPayload>(
    val eventId: Long,
    val type: EventType,
    val payload: T
) {
    companion object {
        fun of(eventId: Long, type: EventType, payload: EventPayload): Event<EventPayload> {
            return Event(eventId, type, payload)
        }

        fun fromJson(json: String): Event<EventPayload>? {
            val eventRaw = DataSerializer.deserialize(json, EventRaw::class.java)
                ?: return null

            val eventType = EventType.valueOf(eventRaw.type)

            return Event(
                eventId = eventRaw.eventId,
                type = eventType,
                payload = DataSerializer.deserialize(eventRaw.payload, eventType.payloadClass)
                    ?: return null
            )
        }
    }

    fun toJson(): String? {
        return DataSerializer.serialize(this)
    }
}

private data class EventRaw(
    val eventId: Long,
    val type: String,
    val payload: String
)
