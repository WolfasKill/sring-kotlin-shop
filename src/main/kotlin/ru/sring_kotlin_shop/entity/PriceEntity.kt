package ru.shopkotlin.sring_kotlin.entity

import jakarta.persistence.*
import java.math.BigDecimal



@Entity
@Table(name = "TBL_PRICE")
open class PriceEntity { // open - can be inherited and needed for Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    open var n: Long = -1

    @Column(name = "id", nullable = false)
    open var productN: Long = -1

    @Column(name = "p_t_id", nullable = false)
    open var priceTypeN: Long = -1

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    open var price: BigDecimal = BigDecimal.ZERO

    // Empty constructor needed for Hibernate
    constructor() {

    }

    constructor(n: Long, productN: Long, priceTypeN: Long, price: BigDecimal) : this() {
        this.n = n
        this.productN = productN
        this.priceTypeN = priceTypeN
        this.price = price
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PriceEntity) return false

        if (n != other.n) return false
        if (productN != other.productN) return false
        if (priceTypeN != other.priceTypeN) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + productN.hashCode()
        result = 31 * result + priceTypeN.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }

    override fun toString(): String {
        return "PriceEntity(n=$n, productN=$productN, priceTypeN=$priceTypeN, price=$price)"
    }

}
