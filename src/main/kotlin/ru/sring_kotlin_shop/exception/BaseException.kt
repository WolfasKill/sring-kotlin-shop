package ru.shopkotlin.sring_kotlin.exception

import org.springframework.http.HttpStatus

abstract class BaseException(
    val httpStatus: HttpStatus,
    val apiErr: ApiErr,
): RuntimeException(apiErr.errorMessage)