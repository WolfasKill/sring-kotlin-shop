package ru.sring_kotlin_shop.service.impl

import org.springframework.stereotype.Service
import ru.shopkotlin.sring_kotlin.dto.PriceTypeDto
import ru.shopkotlin.sring_kotlin.entity.PriceTypeEntity
import ru.sring_kotlin_shop.repository.PriceTypeRepository
import ru.sring_kotlin_shop.service.PriceTypeService

@Service
class PriceTypeServiceImpl(val priceTypeRepository: PriceTypeRepository) : PriceTypeService {

    override fun create(dto: PriceTypeDto): PriceTypeDto {
        val created = priceTypeRepository.save(PriceTypeEntity(dto.n, dto.name))
        return PriceTypeDto(created.n, created.name)
    }

    override fun findAllByOrderByNAsc(): List<PriceTypeDto> {
        return priceTypeRepository.findAllByOrderByNAsc()
            .map { e -> PriceTypeDto(e.n, e.name) }
    }

    override fun getByN(n: Long): PriceTypeDto {
        val priceTypeEntity =
            priceTypeRepository.findById(n).orElseThrow { Exception(String.format("PriceType with n=%s not exist", n)) }
        return PriceTypeDto(priceTypeEntity.n, priceTypeEntity.name)
    }
}