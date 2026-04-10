package dev.article.support.error

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class ErrorType(
    val status: HttpStatus,
    val message: String,
    val logLevel: LogLevel
) {
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "요청이 올바르지 않습니다.", LogLevel.INFO),
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "요청을 처리하는 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.", LogLevel.ERROR),
    NOT_FOUND_DATA(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.", LogLevel.ERROR),

}