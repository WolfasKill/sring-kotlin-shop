package ru.sring_kotlin_shop.service.impl

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import ru.sring_kotlin_shop.dto.GroupProductDto
import ru.sring_kotlin_shop.entity.GroupProductEntity
import ru.sring_kotlin_shop.exception.ErrMessages
import ru.sring_kotlin_shop.repository.GroupProductRepository
import ru.sring_kotlin_shop.service.GroupProductService
import ru.sring_kotlin_shop.service.ProductServiceNew
import java.lang.String.format


@Service
class GroupProductServiceImpl(
    @Lazy val repository: GroupProductRepository,
    @Lazy val productService: ProductServiceNew
) : GroupProductService {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    override fun create(groupProductDTO: GroupProductDto): GroupProductDto {
        logger.info(format("===================================create %s", groupProductDTO))
        logger.info(format(">>>>before count=%s",repository.count()))
        val groupForSave = GroupProductEntity(
            groupProductDTO.n,
            groupProductDTO.name,
            groupProductDTO.parentN,
            groupProductDTO.haveChilds)
        val savedGroup = repository.saveAndFlush(groupForSave)
        logger.info(format(">>>>after count=%s",repository.count()))
        return GroupProductDto(
            savedGroup.n,
            savedGroup.name,
            savedGroup.parentN,
            savedGroup.haveChilds
        )
    }



    @Throws(Exception::class)
    override fun getByN(n: Long): GroupProductDto {
        val groupProductEntityOptional = repository.findById(n)
        if (groupProductEntityOptional.isPresent) {
            val groupProductEntity = groupProductEntityOptional.get()
            return GroupProductDto(
                groupProductEntity.n,
                groupProductEntity.name,
                groupProductEntity.parentN,
                groupProductEntity.haveChilds
            )
        } else {
            throw Exception(String.format(ErrMessages.NOT_FOUND_GROUP_BY_ID, n))
        }

    }

    override fun existsByN(n: Long): Boolean {
        return repository.existsByN(n)
    }


    override fun existProductsInGroup(n: Long): Boolean {
        return !productService.getByGroupProductN(n).isEmpty()
    }

    override fun getSubGroups(n: Long): List<GroupProductDto> {
        val subGroups = repository.findAllByParentN(n)
        val ret = ArrayList<GroupProductDto>()
        for (subGroup in subGroups) {
            ret.add(GroupProductDto(subGroup.n, subGroup.name, subGroup.parentN, subGroup.haveChilds))
        }
        return ret
    }

    override fun findAllByOrderByNAsc(): List<GroupProductDto> {
        return repository.findAllByOrderByNAsc()
            .map { g -> GroupProductDto(g.n, g.name, g.parentN, g.haveChilds) }//.toList()
    }

    override fun findByNameContaining(name: String): List<GroupProductDto> {
        return repository.findByNameContaining(name)
            .map { g -> GroupProductDto(g.n, g.name, g.parentN, g.haveChilds) }//.toList()
    }


    @Throws(Exception::class)
    override fun update(groupProductDTO: GroupProductDto): GroupProductDto {
        if (!existsByN(groupProductDTO.n)) {
            throw Exception(String.format(ErrMessages.NOT_FOUND_GROUP_BY_ID, groupProductDTO.n))
        }
        val groupProductEntity = GroupProductEntity(
            groupProductDTO.n, groupProductDTO.name, groupProductDTO.parentN, groupProductDTO.haveChilds
        )
        val newGroup = repository.save(groupProductEntity)
        return GroupProductDto(
            newGroup.n,
            newGroup.name,
            newGroup.parentN,
            newGroup.haveChilds
        )
    }

    @Throws(Exception::class)
    override fun deleteByN(n: Long) {
        if (!existsByN(n)) {
            throw Exception(String.format(ErrMessages.NOT_FOUND_GROUP_BY_ID, n))
        }
        repository.deleteByN(n)
    }
}