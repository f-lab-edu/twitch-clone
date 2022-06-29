package com.example.product.util

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.product.application.port.out.ProductRepository
import com.example.product.domain.model.Product
import java.util.concurrent.ConcurrentHashMap

class MockProductRepository : ProductRepository {

    private val products = ConcurrentHashMap<Int, Product>()

    override fun findByProductNo(productNo: Int): Product {
        return products[productNo] ?: throw CustomException(ErrorCode.PRODUCT_NOT_FOUND)
    }

    override fun save(product: Product): Product {
        this.products[product.productNo] = product
        return product
    }
}