package dev.article.support.response

import dev.article.support.error.ErrorType

data class ErrorMessage private constructor(
    val code: String,
    val message: String
) {
    constructor(errorType: ErrorType): this(
        code = errorType.name,
        message = errorType.message
    )
}
