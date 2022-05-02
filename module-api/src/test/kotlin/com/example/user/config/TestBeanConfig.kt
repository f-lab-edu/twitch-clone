package com.example.user.config

import com.example.user.adpter.out.NormalUserDao
import com.example.user.adpter.out.NormalUserRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestBeanConfig {

    @Autowired
    internal lateinit var normalUserDao : NormalUserDao

    @Bean
    internal fun normalUserRepositoryImpl() : NormalUserRepositoryImpl {
        return NormalUserRepositoryImpl(normalUserDao)
    }
}
