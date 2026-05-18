package dev.apidocsui.controller

import dev.apidocsui.config.ApiDocsTargets
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
class ApiDocsController(
    private val targets: ApiDocsTargets,
) {
    private val restClient = RestClient.create()

    @GetMapping("/api-docs/{service}")
    fun getApiDocs(
        @PathVariable service: String,
    ): ResponseEntity<String> {
        val baseUrl = targets.targets[service]
            ?: return ResponseEntity.notFound().build()

        val body = restClient.get()
            .uri("$baseUrl/v3/api-docs")
            .retrieve()
            .body(String::class.java)

        return ResponseEntity.ok(body)
    }
}
