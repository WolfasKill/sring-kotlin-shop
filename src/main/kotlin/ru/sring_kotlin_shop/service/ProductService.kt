package ru.shopkotlin.sring_kotlin.service

import ru.shopkotlin.sring_kotlin.dto.ProductDto

interface ProductService {
    fun getAll(): List<ProductDto>
    fun getById(id: Int): ProductDto
    fun search(prefix: String): List<ProductDto>
    fun create(dto: ProductDto) : Int
    fun update(id: Int, dto: ProductDto)
    fun delete(id: Int)
}