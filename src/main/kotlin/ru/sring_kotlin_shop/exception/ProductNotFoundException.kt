package ru.sring_kotlin_shop.exception

import org.springframework.http.HttpStatus

class ProductNotFoundException(productId: Int):BaseException(
    HttpStatus.NOT_FOUND,
    ApiErr(
        errorCode = "product.not.found",
        errorMessage = "Product not found with id $productId",
    ),
) {
}