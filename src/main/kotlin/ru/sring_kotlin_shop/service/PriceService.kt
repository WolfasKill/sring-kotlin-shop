package ru.sring_kotlin_shop.service

import ru.shopkotlin.sring_kotlin.dto.PriceDto

interface PriceService {

    @Throws(Exception::class)
    fun create(dto: PriceDto): PriceDto

    fun findAllByOrderByNAsc(): List<PriceDto>
    fun getByN(n: Long): PriceDto
}