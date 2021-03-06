package com.example.user.config

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
    internal lateinit var normalUserDao : NormalUserDao

    @Autowired
    internal lateinit var streamerUserDao: StreamerUserDao

    @Autowired
    internal lateinit var adminUserDao: AdminUserDao

    @Bean
    internal fun normalUserRepositoryImpl() : NormalUserRepositoryImpl {
        return NormalUserRepositoryImpl(normalUserDao, NormalUserDynamicDao(entityManager))
    }

    @Bean
    internal fun streamerUserRepositoryImpl() : StreamerUserRepositoryImpl {
        return StreamerUserRepositoryImpl(streamerUserDao, StreamerUserDynamicDao(entityManager),
            normalUserDao, normalUserRepositoryImpl())
    }

    @Bean
    internal fun adminUserRepositoryImpl() : AdminUserRepositoryImpl {
        return AdminUserRepositoryImpl(adminUserDao, normalUserRepositoryImpl())
    }
}
