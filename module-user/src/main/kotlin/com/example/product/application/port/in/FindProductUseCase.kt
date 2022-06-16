package com.example.product.application.port.`in`

import com.example.product.domain.model.Product

interface FindProductUseCase {
    fun findProduct(productNo: Int) : Product
}