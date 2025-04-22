package ru.sring_kotlin_shop.service

import ru.sring_kotlin_shop.dto.ProductDto

interface ProductService {
    fun getAll(): List<ProductDto>
    fun getById(id: Int): ProductDto
    fun search(prefix: String): List<ProductDto>
    fun create(dto: ProductDto) : Int
    fun update(id: Int, dto: ProductDto)
    fun delete(id: Int)
}