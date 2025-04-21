package ru.sring_kotlin_shop.filter

data class GroupProductFilter(
    val listN: List<Long>, // list of GroupProduct N
    val name: String = "", // name of GroupProduct
    var listSortBy: List<String> = listOf("name") // list of sort fields
)