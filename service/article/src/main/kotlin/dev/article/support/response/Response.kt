package dev.article.support.response

import com.fasterxml.jackson.annotation.JsonInclude
import dev.article.support.error.ErrorType

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response<T>(
    val result: ResultType,
    val data: T? = null,
    val error: ErrorMessage? = null
) {
    companion object {
        fun success(): Response<Any> {
            return Response(ResultType.SUCCESS, null, null)
        }

        fun <T> success(data: T): Response<T> {
            return Response(ResultType.SUCCESS, data, null)
        }

        fun <T> error(error: ErrorType): Response<T> {
            return Response(ResultType.ERROR, null, ErrorMessage(error))
        }
    }
}