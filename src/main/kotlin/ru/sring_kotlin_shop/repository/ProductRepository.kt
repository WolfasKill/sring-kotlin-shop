package ru.shopkotlin.sring_kotlin.repository

import org.aspectj.weaver.patterns.PerClause.KindAnnotationPrefix
import org.springframework.data.repository.CrudRepository
import ru.shopkotlin.sring_kotlin.dto.ProductDto
import ru.shopkotlin.sring_kotlin.entity.ProductEntity

interface ProductRepository: CrudRepository<ProductEntity, Int> {
    fun findByNameStartsWithIgnoreCaseOrderByName(prefix: String): List<ProductEntity>
}
