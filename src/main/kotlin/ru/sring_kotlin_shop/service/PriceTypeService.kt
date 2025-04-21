package ru.sring_kotlin_shop.service

import ru.shopkotlin.sring_kotlin.dto.PriceTypeDto

interface PriceTypeService {

    @Throws(Exception::class)
    fun create(dto: PriceTypeDto): PriceTypeDto

    fun findAllByOrderByNAsc(): List<PriceTypeDto>
    fun getByN(n: Long): PriceTypeDto
}