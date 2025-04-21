package ru.shopkotlin.sring_kotlin.exception

import jakarta.persistence.Id
import org.springframework.http.HttpStatus

class ProductNotFoundException(productId: Int):BaseException(
    HttpStatus.NOT_FOUND,
    ApiErr(
        errorCode = "product.not.found",
        errorMessage = "Product not found with id $productId",
    ),
) {
}