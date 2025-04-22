package ru.sring_kotlin_shop.service.impl

import org.springframework.stereotype.Service
import ru.sring_kotlin_shop.dto.PriceDto
import ru.sring_kotlin_shop.mapper.MapperPrice
import ru.sring_kotlin_shop.repository.PriceRepository

@Service
class PriceServiceImpl(val priceRepository: PriceRepository) {
    fun findAllByOrderByNAsc(): List<PriceDto> {
        val dtos = mutableListOf<PriceDto>()
        priceRepository.findAllByOrderByNAsc().forEach({ dtos.add(MapperPrice.mapFromEntityToDto(it)) })
        return dtos
    }
}