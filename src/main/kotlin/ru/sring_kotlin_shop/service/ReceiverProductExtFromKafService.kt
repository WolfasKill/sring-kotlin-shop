package ru.sring_kotlin_shop.service

import ru.shopkotlin.sring_kotlin.dto.ProductDtoNew



interface ReceiverProductExtFromKafService {
    @Throws(Exception::class)
    fun getFromQueue(): List<ProductDtoNew>
}