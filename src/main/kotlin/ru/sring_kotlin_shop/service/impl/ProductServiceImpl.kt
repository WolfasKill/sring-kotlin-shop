package ru.shopkotlin.sring_kotlin.service.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.shopkotlin.sring_kotlin.dto.ProductDto
import ru.shopkotlin.sring_kotlin.entity.ProductEntity
import ru.shopkotlin.sring_kotlin.exception.ProductNotFoundException
import ru.shopkotlin.sring_kotlin.repository.ProductRepository
import ru.shopkotlin.sring_kotlin.service.ProductService


@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
) : ProductService {
    override fun getAll(): List<ProductDto> {
        return productRepository.findAll().map {it.toDto()}
    }

    override fun getById(id: Int): ProductDto {
        return productRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw ProductNotFoundException(id)
    }

    override fun search(prefix: String): List<ProductDto> =
        productRepository.findByNameStartsWithIgnoreCaseOrderByName(prefix)
            .map {it.toDto()}

    override fun create(dto: ProductDto): Int {
        return productRepository.save(dto.toEntity()).id
    }

    override fun update(id: Int, dto: ProductDto) {
        val existingProduct=productRepository.findByIdOrNull(id)
            ?: throw ProductNotFoundException(id)
        existingProduct.name = dto.name
        existingProduct.quantity = dto.quantity
        productRepository.save(existingProduct)


    }

    override fun delete(id: Int) {
        val existingProduct=productRepository.findByIdOrNull(id)
            ?: throw ProductNotFoundException(id)
        productRepository.deleteById(existingProduct.id)
    }

    private fun ProductEntity.toDto(): ProductDto =
        ProductDto(
            id = this.id,
            name = this.name,
            quantity = this.quantity,

        )
    private fun ProductDto.toEntity(): ProductEntity =
        ProductEntity(
            id=0,
            name = this.name,
            quantity = this.quantity,
        )

}