package ru.shopkotlin.sring_kotlin.dto

data class GroupProductDto(
    var n: Long = -1,
    var name: String = "",
    var parentN: Long = -1,
    var haveChilds: Boolean = false
)
