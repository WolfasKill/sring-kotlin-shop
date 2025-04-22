package ru.sring_kotlin_shop.dto


import java.math.BigDecimal

data class PriceDto(
    val n: Long = -1,
    var productN: Long = -1,
    var priceTypeN: Long = -1,
    var price: BigDecimal = BigDecimal.ZERO
)

