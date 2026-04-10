package dev.article.support.error

class ApiException(
    val errorType: ErrorType
) : RuntimeException(errorType.message)
