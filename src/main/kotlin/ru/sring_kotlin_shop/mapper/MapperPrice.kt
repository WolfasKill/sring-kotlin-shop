package ru.sring_kotlin_shop.mapper

import ru.sring_kotlin_shop.dto.PriceDto
import ru.sring_kotlin_shop.entity.PriceEntity

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