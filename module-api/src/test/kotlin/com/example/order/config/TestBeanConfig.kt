package com.example.order.config

import com.example.order.adapter.`in`.OrderServiceImpl
import com.example.product.adpter.out.ProductDao
import com.example.product.adpter.out.ProductRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestBeanConfig {
    @Autowired
    private lateinit var productDao: ProductDao

    @Bean
    @Qualifier("testProductRepository")
    internal fun productRepositoryImpl() : ProductRepositoryImpl {
        return ProductRepositoryImpl(productDao)
    }

    @Bean
    @Qualifier("testOrderService")
    internal fun orderServiceImpl() : OrderServiceImpl {
        return OrderServiceImpl(productRepositoryImpl())
    }

}