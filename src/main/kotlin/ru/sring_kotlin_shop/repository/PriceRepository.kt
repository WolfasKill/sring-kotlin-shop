package ru.sring_kotlin_shop.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.sring_kotlin_shop.entity.PriceEntity

@Repository
@Transactional
interface PriceRepository : JpaRepository<PriceEntity, Long>,
    JpaSpecificationExecutor<PriceEntity>, QuerydslPredicateExecutor<PriceEntity> {

    fun findAllByOrderByNAsc(): List<PriceEntity>
}