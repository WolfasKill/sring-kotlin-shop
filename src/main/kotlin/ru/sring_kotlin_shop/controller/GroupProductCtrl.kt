package ru.sring_kotlin_shop.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.ConstraintViolation
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.validation.Validator
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.shopkotlin.sring_kotlin.dto.GroupProductDto
import ru.shopkotlin.sring_kotlin.exception.ErrMessages
import ru.sring_kotlin_shop.service.GroupProductService
import ru.sring_kotlin_shop.service.ProductServiceNew
import java.lang.String.format

@RestController
@RequestMapping("/group_product")
@Tag(name = "group-product-rest")
@Validated
/**
 * Определяет ТОЛЬКО интерфейсы доступа к сервису. Маппинг в DTO делается в сервисе
 * (уход от lazy проблем, независимость от способа получения самих DTO и т.п.).
 */

class GroupProductCtrl(
    val groupProductService: GroupProductService,
    val productService: ProductServiceNew,
    private val validator: Validator
) {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)


    @GetMapping("/echo/{mes}")
    @Operation(method = "Simple echo test")
    fun echoMessage(
        @Parameter(
            description = "Any string. will be returned in response."
        )
        @PathVariable mes: String
    ): String {
        return mes
    }

    @PostMapping
    @Operation(method = "Create GroupProduct from DTO")
    @CacheEvict(value = ["group_products", "allGroupProductDTO"], allEntries = true)
    fun create(
        @Parameter(
            description = "DTO of GroupProduct."
        )
        @RequestBody groupProductDTO: GroupProductDto
    ): GroupProductDto {
        val violations: MutableSet<ConstraintViolation<GroupProductDto>> =
            validator.validate(groupProductDTO)

        if (violations.size > 0) {
            var messageError = ""
            violations.forEach { violation ->
                messageError = messageError.plus(violation.message + "\n")
            }
            throw Exception("$groupProductDTO has errors: $messageError")
        }
        return groupProductService.create(groupProductDTO)
    }

    @GetMapping("/{n}")
    @Cacheable("group_products")
    @Operation(method = "Get GroupProduct by ID")
    fun getById(
        @Parameter(
            description = "ID of GroupProduct."
        )
        @PathVariable n: Long
    ): GroupProductDto {
        if (n < 0) {
            throw Exception(ErrMessages.ID_MUST_POSITIVE)
        }
        try {
            return groupProductService.getByN(n)
        } catch (excpt: Exception) {
            throw Exception(excpt.message)
        }
    }

    /**
     * get all groups product
     */
    @GetMapping("/")
    @Cacheable("allGroupProductDTO")
    @Operation(method = "Get all groups of product")
    fun all(): List<GroupProductDto> {
        logger.info("GET all GroupProductDTO")
        val groups: List<GroupProductDto> = groupProductService.findAllByOrderByNAsc()
        if (groups.isEmpty()) {
            throw Exception(ErrMessages.NOT_FOUND_ANY_GROUP)
        }
        return groups.map { entity ->
            GroupProductDto(entity.n, entity.name, entity.parentN, entity.haveChilds)
        }

//      https://www.baeldung.com/java-stream-immutable-collection.

    }

    @GetMapping("/clear_cache")
    @CacheEvict(value = ["group_products", "allGroupProductDTO"], allEntries = true)
    @Operation(method = "Clear cache application")
    fun clearCache(): String {
        return "cleared"
    }

    @GetMapping("/find")
    @Operation(method = "Find groups by name")
    fun findByName(
        @Parameter(
            description = "Name of GroupProduct."
        )
        @Valid name: String
    ): List<GroupProductDto> {
        val groups = groupProductService.findByNameContaining(name)
        if (groups.isEmpty()) {
            throw Exception(format(ErrMessages.NOT_FOUND_GROUP_BY_NAME, name))
        }
        return groups.map { entity ->
            GroupProductDto(entity.n, entity.name, entity.parentN, entity.haveChilds)
        }
    }

    @GetMapping("/{n}/subgroups")
    @Operation(method = "Get subgroups")
    @Cacheable("allGroupProductDTO")
    fun getSubGroups(
        @Parameter(
            description = "ID of GroupProduct."
        )
        @PathVariable n: Long
    ): List<GroupProductDto> {
        if (!groupProductService.existsByN(n)) {
            throw Exception("Group product not found with id: $n")
        }
        val subGroups = groupProductService.getSubGroups(n)
        return subGroups.map { entity ->
            GroupProductDto(entity.n, entity.name, entity.parentN, entity.haveChilds)
        }
    }

    @DeleteMapping("/{n}")
    @Operation(method = "Delete GroupProduct by ID")
    @CacheEvict(value = ["group_products", "allGroupProductDTO"], allEntries = true)
    fun deleteByN(
        @Parameter(
            description = "ID of GroupProduct."
        )
        @PathVariable n: Long
    ) {
        if (!groupProductService.existsByN(n)) {
            throw Exception(format(ErrMessages.NOT_FOUND_GROUP_BY_ID, n))
        }
        if (groupProductService.getSubGroups(n).isNotEmpty()) {
            throw Exception(format(ErrMessages.HAVE_SUBGROUPS, n))
        }
        if (productService.getByGroupProductN(n).isNotEmpty()) {
            throw Exception(format(ErrMessages.HAVE_PRODUCTS, n))
        }
        groupProductService.deleteByN(n)
    }
}