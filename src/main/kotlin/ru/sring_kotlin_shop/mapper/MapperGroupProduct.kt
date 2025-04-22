package ru.sring_kotlin_shop.mapper

import ru.sring_kotlin_shop.dto.GroupProductDto
import ru.sring_kotlin_shop.entity.GroupProductEntity

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