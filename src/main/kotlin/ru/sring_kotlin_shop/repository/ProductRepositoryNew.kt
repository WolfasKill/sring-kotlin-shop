package ru.sring_kotlin_shop.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.shopkotlin.sring_kotlin.entity.ProductEntityNew

@Repository
@Transactional
interface ProductRepositoryNew : JpaRepository<ProductEntityNew, Long>,
    JpaSpecificationExecutor<ProductEntityNew>, QuerydslPredicateExecutor<ProductEntityNew> {

    fun findAllByOrderByNAsc(): List<ProductEntityNew>
    fun findAllByGroupProductNOrderByNAsc(groupProductN: Long): List<ProductEntityNew>

    @Query(value = "select max(p.n)+1 from product p", nativeQuery = true)
    fun getNextN(): Long

    fun findByNameContainingOrderByName(name: String): List<ProductEntityNew>

    @Query(
        "select count(*) from ProductEntity"
    )
    fun getCountOfProductNames(): Long
}