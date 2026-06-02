package dev.dataserializer

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DataSerializer {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(javaClass)
        private val objectMapper: ObjectMapper = initialize()

        private fun initialize(): ObjectMapper {
            return ObjectMapper()
                .registerModule(JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }

        fun <T> deserialize(data: String, clazz: Class<T>): T? {
            try {
                return objectMapper.readValue<T>(data, clazz)
            } catch (e: JsonProcessingException) {
                log.error("[DataSerializer.deserialize] data={}, class={}", data, clazz, e)
                return null
            }
        }

        fun <T> deserialize(data: Any, clazz: Class<T>): T? {
            return objectMapper.convertValue<T>(data, clazz)
        }

        fun serialize(obj: Any): String? {
            try {
                return objectMapper.writeValueAsString(obj)
            } catch (e: JsonProcessingException) {
                log.error("[DataSerializer.serialize] object={}", obj, e)
                return null
            }
        }
    }
}