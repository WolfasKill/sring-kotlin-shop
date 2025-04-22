package ru.sring_kotlin_shop.mapper

import ru.sring_kotlin_shop.dto.ProductDto
import ru.sring_kotlin_shop.dto.ProductDtoNew
import ru.sring_kotlin_shop.entity.ProductEntityNew

object MapperProduct {
    fun mapFromDtoToEntity(dto: ProductDtoNew) = ProductEntityNew(        dto.n,
        dto.name,
        dto.groupDtoN
    )
    fun mapFromEntityToDto(entity: ProductEntityNew) = ProductDtoNew(
        entity.n,
        entity.name,
        entity.groupProductN
    )
}