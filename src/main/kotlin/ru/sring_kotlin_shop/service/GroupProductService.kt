package ru.sring_kotlin_shop.service

import ru.shopkotlin.sring_kotlin.dto.GroupProductDto
import ru.sring_kotlin_shop.filter.GroupProductFilter

interface GroupProductService {
    fun create(groupProductDTO: GroupProductDto): GroupProductDto
    fun update(groupProductDTO: GroupProductDto): GroupProductDto
    fun deleteByN(n: Long)
    fun getSubGroups(n: Long): List<GroupProductDto>
    fun findAllByOrderByNAsc(): List<GroupProductDto>
    fun findByNameContaining(name: String): List<GroupProductDto>
    fun existProductsInGroup(n: Long): Boolean

    @Throws(Exception::class)
    fun getByN(n: Long): GroupProductDto
    fun existsByN(n: Long): Boolean
    fun getByFilter(filter: GroupProductFilter): List<GroupProductDto>
}