package ru.sring_kotlin_shop.dto

data class GroupProductDto(
    var n: Long = -1,
    var name: String = "",
    var parentN: Long = -1,
    var haveChilds: Boolean = false
)
