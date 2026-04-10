package dev.article.config

import dev.snowflake.Snowflake
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(SnowflakeProperties::class)
class SnowflakeConfig {
    @Bean
    fun snowflake(properties: SnowflakeProperties): Snowflake {
        return Snowflake(
            nodeId = properties.nodeId,
            startTimeMillis = properties.startTimeMillis
        )
    }
}
