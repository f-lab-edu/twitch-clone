package com.example.product.application.service

import com.example.product.application.port.`in`.FindProductUseCase
import com.example.product.application.port.out.ProductRepository
import com.example.product.domain.model.Product

internal class FindProductService(private val productRepository: ProductRepository) : FindProductUseCase{
    override fun findProduct(productNo: Int): Product {
        return productRepository.findByProductNo(productNo)
    }
}