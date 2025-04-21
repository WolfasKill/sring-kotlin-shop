package ru.shopkotlin.sring_kotlin.mapper

import ru.shopkotlin.sring_kotlin.dto.PriceDto
import ru.shopkotlin.sring_kotlin.entity.PriceEntity

object MapperPrice {
    val mapperPriceType = MapperPriceType
    val mapperProduct = MapperProduct
    fun mapFromDtoToEntity(dto: PriceDto): PriceEntity {

        return PriceEntity(
            dto.n,
            dto.productN,
            dto.priceTypeN,
            dto.price
        )
    }

    fun mapFromEntityToDto(entity: PriceEntity) : PriceDto = PriceDto(
        entity.n,
        entity.productN,
        entity.priceTypeN,
        entity.price
    )
}