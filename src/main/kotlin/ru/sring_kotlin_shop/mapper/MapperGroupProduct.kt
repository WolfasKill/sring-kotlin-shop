package ru.shopkotlin.sring_kotlin.mapper

import ru.shopkotlin.sring_kotlin.dto.GroupProductDto
import ru.shopkotlin.sring_kotlin.entity.GroupProductEntity

object MapperGroupProduct {
    fun mapFromDtoToEntity(dto: GroupProductDto) = GroupProductEntity(
        dto.n,
        dto.name
    )
    fun mapFromEntityToDto(entity: GroupProductEntity) = GroupProductDto(
        entity.n,
        entity.name
    )
}