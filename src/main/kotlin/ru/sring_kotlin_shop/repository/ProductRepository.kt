package ru.sring_kotlin_shop.repository

import org.aspectj.weaver.patterns.PerClause.KindAnnotationPrefix
import org.springframework.data.repository.CrudRepository
import ru.sring_kotlin_shop.dto.ProductDto
import ru.sring_kotlin_shop.entity.ProductEntity

interface ProductRepository: CrudRepository<ProductEntity, Int> {
    fun findByNameStartsWithIgnoreCaseOrderByName(prefix: String): List<ProductEntity>
}
