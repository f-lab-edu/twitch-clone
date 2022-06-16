package com.example.product.adpter.out

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.product.application.port.out.ProductRepository
import com.example.product.domain.model.Product
import com.example.product.domain.model.ProductEntity
import org.springframework.stereotype.Repository

@Repository
internal class ProductRepositoryImpl(private val productDao: ProductDao) : ProductRepository {
    override fun findByProductNo(productNo: Int): Product {
        return productDao.findByProductNo(productNo) ?: throw CustomException(ErrorCode.PRODUCT_NOT_FOUND)
    }

    override fun save(product: Product): ProductEntity {
        return productDao.save(ProductEntity.from(product))
    }
}
