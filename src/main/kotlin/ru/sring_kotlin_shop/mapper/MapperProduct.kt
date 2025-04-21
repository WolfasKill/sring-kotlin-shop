package ru.shopkotlin.sring_kotlin.mapper

import ru.shopkotlin.sring_kotlin.dto.ProductDto
import ru.shopkotlin.sring_kotlin.dto.ProductDtoNew
import ru.shopkotlin.sring_kotlin.entity.ProductEntity
import ru.shopkotlin.sring_kotlin.entity.ProductEntityNew

object MapperProduct {
    fun mapFromDtoToEntity(dto: ProductDtoNew) = ProductEntityNew(
        dto.n,
        dto.name,
        dto.groupDtoN
    )
    fun mapFromEntityToDto(entity: ProductEntityNew) = ProductDtoNew(
        entity.n,
        entity.name,
        entity.groupProductN
    )
}