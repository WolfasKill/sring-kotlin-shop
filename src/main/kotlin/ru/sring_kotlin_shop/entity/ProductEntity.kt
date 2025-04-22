package ru.sring_kotlin_shop.entity

import jakarta.persistence.*


@Entity
@Table(name = "TBL_PRODUCT")
class ProductEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var name: String = "",
    var quantity: Int = 0,

    )