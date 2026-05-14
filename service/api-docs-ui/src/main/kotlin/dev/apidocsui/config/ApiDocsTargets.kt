package dev.apidocsui.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api-docs")
data class ApiDocsTargets(
    val targets: Map<String, String> = mapOf(),
)
