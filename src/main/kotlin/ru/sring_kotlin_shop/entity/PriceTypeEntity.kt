package ru.shopkotlin.sring_kotlin.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table




@Entity
@Table(name = "TBL_PRICE_TYPE")
open class PriceTypeEntity { // open - can be inherited and needed for Hibernate
    @Id
    @Column(name = "p_t_id", nullable = false)
    open var n: Long = -1
    @Column(name = "name", nullable = false)
    open var name: String = ""

    // Empty constructor needed for Hibernate
    constructor()

    constructor(n: Long, name: String) {
        this.n = n
        this.name = name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PriceTypeEntity) return false

        if (n != other.n) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "PriceTypeEntity(n=$n, name='$name')"
    }


}