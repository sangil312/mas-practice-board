package dev.snowflake

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@AutoConfiguration
@EnableConfigurationProperties(SnowflakeProperties::class)
class SnowflakeAutoConfiguration {
    @Bean
    fun snowflake(properties: SnowflakeProperties): Snowflake {
        return Snowflake(
            nodeId = properties.nodeId,
            startTimeMillis = properties.startTimeMillis
        )
    }
}
