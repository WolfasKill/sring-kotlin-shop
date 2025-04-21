package ru.shopkotlin.sring_kotlin.exception

data class ApiErr(
    val errorCode: String,
    val errorMessage: String,
)
