package com.example.user.config

import com.example.user.adpter.out.NormalUserDao
import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.adpter.out.StreamerUserDao
import com.example.user.adpter.out.StreamerUserRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestBeanConfig {

    @Autowired
    internal lateinit var normalUserDao : NormalUserDao

    @Autowired
    internal lateinit var streamerUserDao: StreamerUserDao

    @Bean
    internal fun normalUserRepositoryImpl() : NormalUserRepositoryImpl {
        return NormalUserRepositoryImpl(normalUserDao)
    }

    @Bean
    internal fun streamerUserRepositoryImpl() : StreamerUserRepositoryImpl {
        return StreamerUserRepositoryImpl(streamerUserDao, normalUserDao, normalUserRepositoryImpl())
    }
}
