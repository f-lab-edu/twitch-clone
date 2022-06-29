package com.example.product.config

import com.example.product.adpter.out.ProductDao
import com.example.product.adpter.out.ProductRepositoryImpl
import com.example.user.adpter.out.AdminUserDao
import com.example.user.adpter.out.AdminUserRepositoryImpl
import com.example.user.adpter.out.NormalUserDao
import com.example.user.adpter.out.NormalUserDynamicDao
import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.adpter.out.StreamerUserDao
import com.example.user.adpter.out.StreamerUserDynamicDao
import com.example.user.adpter.out.StreamerUserRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import javax.persistence.EntityManager

@TestConfiguration
class TestBeanConfig {

    @Autowired
    internal lateinit var entityManager: EntityManager

    @Autowired
    internal lateinit var productDao: ProductDao

    @Bean
    internal fun productRepositoryImpl() : ProductRepositoryImpl {
        return ProductRepositoryImpl(productDao)
    }
}
