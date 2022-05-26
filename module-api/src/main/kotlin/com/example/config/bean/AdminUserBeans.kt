package com.example.config.bean

import com.example.user.adpter.out.AdminUserDao
import com.example.user.adpter.out.AdminUserRepositoryImpl
import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.application.port.`in`.admin.CreateAdminUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class AdminUserBeans {

    @Bean
    fun createAdminUserUseCase(
        adminUserDao: AdminUserDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): CreateAdminUserUseCase = CreateAdminUserUseCase.create(AdminUserRepositoryImpl(adminUserDao, normalUserRepositoryImpl))
}
