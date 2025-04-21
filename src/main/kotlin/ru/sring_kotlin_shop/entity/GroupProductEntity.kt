package ru.shopkotlin.sring_kotlin.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table







@Entity
@Table(name = "TBL_GRP_PRODUCT")
open class GroupProductEntity { // open needed for Hibernate
    @Id
    @Column(name = "sid")
    open var n: Long  = -1
    @Column(name = "name", nullable = false)
    open var name: String = ""
    @Column(name = "pid", nullable = false)
    open var parentN: Long = -1
    @Column(name = "hc", nullable = false)
    open var haveChilds: Boolean = false

    // Empty constructor needed for Hibernate
    constructor() {

    }

    constructor(n: Long, name: String) {
        this.n = n
        this.name = name
    }

    constructor(n: Long, name: String, parentN: Long, haveChilds: Boolean) {
        this.n = n
        this.name = name
        this.parentN = parentN
        this.haveChilds = haveChilds
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GroupProductEntity) return false

        if (n != other.n) return false
        if (name != other.name) return false
        if (parentN != other.parentN) return false
        if (haveChilds != other.haveChilds) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + parentN.hashCode()
        result = 31 * result + haveChilds.hashCode()
        return result
    }

    override fun toString(): String {
        return "GroupProductEntity(n=$n, name='$name', parentN=$parentN, haveChilds=$haveChilds)"
    }


}