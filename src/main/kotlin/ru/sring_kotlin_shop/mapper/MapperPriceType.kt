package ru.shopkotlin.sring_kotlin.mapper

import ru.shopkotlin.sring_kotlin.dto.PriceTypeDTO
import ru.shopkotlin.sring_kotlin.entity.PriceTypeEntity

object MapperPriceType {
    fun mapFromDtoToEntity(priceTypeDTO: PriceTypeDTO) = PriceTypeEntity(
        priceTypeDTO.n,
        priceTypeDTO.name
    )
    fun mapFromEntityToDto(priceTypeEntity: PriceTypeEntity) = PriceTypeDTO(
        priceTypeEntity.n,
        priceTypeEntity.name
    )
}