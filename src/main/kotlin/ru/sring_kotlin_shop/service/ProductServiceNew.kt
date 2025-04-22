package ru.sring_kotlin_shop.service

//import ru.sring_kotlin_shop.dto.ProductDto
import ru.sring_kotlin_shop.dto.ProductDtoNew
//import ru.sring_kotlin_shop.filter.ProductFilter

interface ProductServiceNew {
    @Throws(Exception::class)
    fun create(dto: ProductDtoNew): ProductDtoNew

    @Throws(Exception::class)
    fun update(dto: ProductDtoNew): ProductDtoNew

    @Throws(Exception::class)
    fun delete(n: Long)
    fun existByN(n: Long): Boolean
    fun getByGroupProductN(n: Long): List<ProductDtoNew>

    @Throws(Exception::class)
    fun getByN(n: Long): ProductDtoNew
    fun getByNs(nn: List<Long>): List<ProductDtoNew>
    fun getByName(name: String): List<ProductDtoNew>
    fun getCountOfProductNames(): Long
    fun getAll(): List<ProductDtoNew>
}