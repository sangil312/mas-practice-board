package dev.apidocsui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class ApiDocsUiApplication

fun main(args: Array<String>) {
    runApplication<ApiDocsUiApplication>(*args)
}
