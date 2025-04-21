package ru.sring_kotlin_shop.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.ConstraintViolation
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.validation.Validator
import org.springframework.web.bind.annotation.*
import ru.shopkotlin.sring_kotlin.dto.ProductDtoNew
import ru.sring_kotlin_shop.service.ProductServiceNew
import java.lang.String.format



@RestController
@RequestMapping("/product")
class ProductCtrl(
    val productService: ProductServiceNew,
    private val validator: Validator
) {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)


    @GetMapping("/count_names")
    @Operation(method="Get number of product NAMES")
    fun getCountOfProductNames(): Long {
        return productService.getCountOfProductNames()
    }

    @GetMapping("/echo/{mes}")
    @Operation(method="Simple echo test")
    @Cacheable("product_echo")
    fun echoMessage(
        @Parameter(
            description = "Any string. will be returned in response."
        )
        @PathVariable mes: String
    ): String {
        logger.info(format("echo %s", mes))
        return mes
    }

    @PostMapping
    @Operation(method="Create Product from DTO")
    fun create(
        @Parameter(
            description = "DTO of Product."
        )
        @RequestBody productDTO: ProductDtoNew
    ): ProductDtoNew {
        validate(productDTO)
        return productService.create(productDTO)
    }

    @GetMapping("/{n}")
    @Operation(method="Get Product by N")
    @Cacheable("products")
    fun getByN(
        @Parameter(
            description = "N(ID) Product."
        )
        @PathVariable
        n: Long
    ): ProductDtoNew {
        return productService.getByN(n)
    }

    @GetMapping("/")
    @Operation(method="Get all products")
    @Cacheable("products")
    fun getAll(): List<ProductDtoNew> {
        return productService.getAll()
    }

    @PostMapping(path = ["/{n}"], consumes = ["application/json"], produces = ["application/json"])
//    @CacheEvict("products")
    @CacheEvict(value = ["products", "allGroupProductDTO"], allEntries = true)
//    @CacheEvict(value = ["product"], key = "#product.n")
    @Operation(method="Update Product")
    fun update(
        @PathVariable
        n: Long,
        @Parameter(
            description = "Product."
        )
        @RequestBody
        product: ProductDtoNew
    ): ProductDtoNew {
        logger.info("UPDATE:$product")
        validate(product)
        return productService.update(product)
    }

    @DeleteMapping("/{n}")
    @CacheEvict(value = ["products"], key = "#n")
    fun deleteById(
        @Parameter(
            description = "N(ID) Product."
        )
        @PathVariable
        n: Long
    ) {
        productService.delete(n)
    }

    fun validate(productDTO: ProductDtoNew) {
        val violations: MutableSet<ConstraintViolation<ProductDtoNew>> =
            validator.validate(productDTO)

        if (violations.isNotEmpty()) {
            var messageError = ""
// OLD STYLE
//            for(violation in violations) {
//                messageError = messageError.plus(violation.message + "\n")
//            }

// USED STREAM
            violations.forEach { violation ->
                messageError = messageError.plus(violation.message + "\n")
            }
            throw Exception("$productDTO has errors: $messageError")
        }
    }

}