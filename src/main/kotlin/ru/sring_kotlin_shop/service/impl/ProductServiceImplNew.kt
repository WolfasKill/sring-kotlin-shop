package ru.sring_kotlin_shop.service.impl


import org.springframework.stereotype.Service
import ru.shopkotlin.sring_kotlin.dto.ProductDtoNew
import ru.shopkotlin.sring_kotlin.entity.ProductEntityNew
import ru.shopkotlin.sring_kotlin.exception.ErrMessages
import ru.sring_kotlin_shop.repository.ProductRepositoryNew
import ru.sring_kotlin_shop.service.GroupProductService
import ru.sring_kotlin_shop.service.ProductServiceNew

@Service
class ProductServiceImplNew(
    var productRepository: ProductRepositoryNew,
    var groupService: GroupProductService,
    private val productRepositoryNew: ProductRepositoryNew
) : ProductServiceNew {

    @Throws(Exception::class)
    override fun create(dto: ProductDtoNew): ProductDtoNew {
        if (!groupService.existsByN(dto.groupDtoN)) {
            throw Exception(String.format("GroupProduct with n=%s not exist", dto.groupDtoN))
        }
        val n = productRepository.getNextN()
        val savedProduct = productRepository.save(ProductEntityNew(n, dto.name, dto.groupDtoN))
        return ProductDtoNew(savedProduct.n, savedProduct.name, savedProduct.groupProductN)
    }

    override fun getByGroupProductN(n: Long): List<ProductDtoNew> {
        return productRepositoryNew.findAllByGroupProductNOrderByNAsc(n)
            .map { ProductDtoNew(it.n, it.name, it.groupProductN) }
    }

    @Throws(Exception::class)
    override fun getByN(n: Long): ProductDtoNew {
        val product = productRepository.findById(n)
        if (product.isPresent) {
            val entity = product.get()
            return ProductDtoNew(entity.n, entity.name, entity.groupProductN)
        } else {
            throw Exception(String.format(ErrMessages.NOT_FOUND_PRODUCT_BY_ID, n))
        }
    }

    override fun getByNs(ids: List<Long>): List<ProductDtoNew> {
        return productRepository.findAllById(ids).map { ProductDtoNew(it.n, it.name, it.groupProductN) }
    }


//    override fun getByFilter(filter: ProductFilter): List<ProductDtoNew> {
//        var booleanBuilder = BooleanBuilder()
//
//        if (!filter.listN.isEmpty()) {
//            booleanBuilder = booleanBuilder.and(QProductEntity.productEntity.n.`in`(filter.listN))
//        }
//        if (!filter.name.isEmpty()) {
//            booleanBuilder = booleanBuilder.and(QProductEntity.productEntity.name.like("%" + filter.name + "%"))
//        }
//
//        val entities = productRepository.findAll(booleanBuilder, QSort.by(filter.listSortBy.joinToString(",")))
//
//        val dtos = entities.map { ProductDtoNew(it.n, it.name, it.groupProductN) }
//        return dtos
//    }


    override fun getByName(name: String): List<ProductDtoNew> {
        return productRepository.findByNameContainingOrderByName(name)
            .map { ProductDtoNew(it.n, it.name, it.groupProductN) }

    }

    override fun getCountOfProductNames(): Long {
        return productRepository.getCountOfProductNames()
    }

    override fun getAll(): List<ProductDtoNew> {
        return productRepository.findAllByOrderByNAsc()
            .map { ProductDtoNew(it.n, it.name, it.groupProductN) }
    }

    override fun existByN(n: Long): Boolean {
        return productRepository.findById(n).isPresent
    }

    @Throws(Exception::class)
    override fun update(dto: ProductDtoNew): ProductDtoNew {
        if (!groupService.existsByN(dto.groupDtoN)) {
            throw Exception(String.format("GroupProduct with n=%s not exist", dto.groupDtoN))
        }
        if (!existByN(dto.n)) {
            throw Exception(String.format("Product with n=%s not exist", dto.n))
        }
        val savedProduct = productRepository.save(ProductEntityNew(dto.n, dto.name, dto.groupDtoN))
        return ProductDtoNew(savedProduct.n, savedProduct.name, savedProduct.groupProductN)
    }

    @Throws(Exception::class)
    override fun delete(n: Long) {
        if (!existByN(n)) {
            throw Exception(String.format("Product with n=%s not exist", n))
        }
        productRepository.deleteById(n)
    }
}