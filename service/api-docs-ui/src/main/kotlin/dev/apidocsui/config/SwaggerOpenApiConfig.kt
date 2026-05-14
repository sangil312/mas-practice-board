package dev.apidocsui.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@AutoConfiguration
@Profile("local")
class SwaggerOpenApiConfig {

    @Bean
    fun serviceUserIdOpenAPI(): OpenAPI {
        val schemeName = "Service-User-Id"
        return OpenAPI()
            .components(
                Components().addSecuritySchemes(
                    schemeName,
                    SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .`in`(SecurityScheme.In.HEADER)
                        .name(schemeName),
                ),
            )
            .addSecurityItem(SecurityRequirement().addList(schemeName))
    }
}
