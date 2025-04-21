package ru.shopkotlin.sring_kotlin.entity

import jakarta.persistence.*

@Entity
@Table(name = "TBL_PRODUCT_NEW")
open class ProductEntityNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    // column name must is not "id", "id" is key word
    open var n: Long = -1

    @Column(name = "name", nullable = false)
    open var name: String = ""

    @Column(name = "sid", nullable = false)
    open var groupProductN: Long = -1

    // Empty constructor needed for Hibernate
    constructor() {
    }

    constructor(n: Long, name: String, groupProductN: Long) {
        this.n = n
        this.name = name
        this.groupProductN = groupProductN
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProductEntityNew) return false

        if (n != other.n) return false
        if (name != other.name) return false
        if (groupProductN != other.groupProductN) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + groupProductN.hashCode()
        return result
    }

    override fun toString(): String {
        return "ProductEntity(n=$n, name='$name', groupProductN=$groupProductN)"
    }
}