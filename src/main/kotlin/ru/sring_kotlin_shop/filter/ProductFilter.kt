package ru.sring_kotlin_shop.filter

data class ProductFilter(
    val listN: List<Long>,
    val name: String = "",
    var listSortBy: List<String> = listOf("name")
)
