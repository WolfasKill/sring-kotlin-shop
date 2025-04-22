package ru.sring_kotlin_shop.mapper

import ru.sring_kotlin_shop.dto.PriceTypeDto
import ru.sring_kotlin_shop.entity.PriceTypeEntity

object MapperPriceType {
    fun mapFromDtoToEntity(priceTypeDTO: PriceTypeDto) = PriceTypeEntity(
        priceTypeDTO.n,
        priceTypeDTO.name
    )
    fun mapFromEntityToDto(priceTypeEntity: PriceTypeEntity) = PriceTypeDto(
        priceTypeEntity.n,
        priceTypeEntity.name
    )
}