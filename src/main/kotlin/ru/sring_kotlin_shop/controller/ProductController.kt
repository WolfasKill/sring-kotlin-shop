package ru.sring_kotlin_shop.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.InternalResourceViewResolver
import ru.sring_kotlin_shop.dto.ProductDto
import ru.sring_kotlin_shop.service.ProductService


@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService,
    private val internalResourceViewResolver: InternalResourceViewResolver,
) {
    @GetMapping
    fun getAll(): List<ProductDto> =
        productService.getAll()
    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ProductDto =
        productService.getById(id)
    @GetMapping("/search")
    fun searchProducts(@RequestParam("prefix") prefix: String): List<ProductDto> =
        productService.search(prefix)
    @PostMapping
    fun create(@RequestBody dto: ProductDto): Int{
    return  productService.create(dto)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: ProductDto) {
    productService.update(id, dto)
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
    productService.delete(id)
    }

}

