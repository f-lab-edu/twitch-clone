package com.example.config.bean

import com.example.product.application.port.`in`.FindProductUseCase
import com.example.product.application.port.out.ProductRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductBeans {
    @Bean
    fun findProductUseCase(productRepository: ProductRepository) : FindProductUseCase
        = FindProductUseCase.create(productRepository)
}
