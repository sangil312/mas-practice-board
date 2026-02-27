package dev.article.config

import dev.snowflake.Snowflake
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "snowflake")
data class SnowflakeProperties(
    val nodeId: Long,
    val startTimeMillis: Long = Snowflake.DEFAULT_START_TIME_MILLIS
)
