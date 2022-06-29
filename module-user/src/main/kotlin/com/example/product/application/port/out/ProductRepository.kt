package com.example.product.application.port.out

import com.example.product.domain.model.Product

interface ProductRepository {
    fun findByProductNo(productNo: Int) : Product

    fun save(product: Product) : Product
}