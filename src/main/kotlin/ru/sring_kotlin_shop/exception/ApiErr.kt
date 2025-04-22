package ru.sring_kotlin_shop.exception

data class ApiErr(
    val errorCode: String,
    val errorMessage: String,
)
