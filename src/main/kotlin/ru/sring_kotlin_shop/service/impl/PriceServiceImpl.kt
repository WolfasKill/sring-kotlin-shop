package ru.sring_kotlin_shop.service.impl

import org.springframework.stereotype.Service
import ru.shopkotlin.sring_kotlin.dto.PriceDto
import ru.shopkotlin.sring_kotlin.mapper.MapperPrice
import ru.sring_kotlin_shop.repository.PriceRepository

@Service
class PriceServiceImpl(val priceRepository: PriceRepository) {
    fun findAllByOrderByNAsc(): List<PriceDto> {
        val dtos = mutableListOf<PriceDto>()
        priceRepository.findAllByOrderByNAsc().forEach({ dtos.add(MapperPrice.mapFromEntityToDto(it)) })
        return dtos
    }
}