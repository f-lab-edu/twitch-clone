package com.example.product.application.port.`in`

import com.example.product.application.port.out.ProductRepository
import com.example.product.application.service.FindProductService
import com.example.product.domain.model.Product

interface FindProductUseCase {
    fun findProduct(productNo: Int) : Product

    companion object {
        fun create(productRepository: ProductRepository): FindProductUseCase = FindProductService(productRepository)
    }
}