package ru.sring_kotlin_shop.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.sring_kotlin_shop.entity.GroupProductEntity

@Repository
@Transactional
interface GroupProductRepository : JpaRepository<GroupProductEntity, Long>,
    JpaSpecificationExecutor<GroupProductEntity>, QuerydslPredicateExecutor<GroupProductEntity> {
    fun findAllByOrderByNAsc(): List<GroupProductEntity>
    fun findByNameContaining(name: String): List<GroupProductEntity>
    fun findAllByParentN(n: Long): List<GroupProductEntity>

    @Query(value = "SELECT max(n) + 1 FROM group_product ", nativeQuery = true)
    fun getNextN(): Long?
    fun getByN(n: Long): GroupProductEntity
    fun deleteByN(n: Long)
    fun existsByN(n: Long): Boolean
}