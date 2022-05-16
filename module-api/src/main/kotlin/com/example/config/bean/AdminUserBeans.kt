package com.example.config.bean

import com.example.user.adpter.out.AdminUserDao
import com.example.user.adpter.out.AdminUserRepositoryImpl
import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.application.port.`in`.admin.CreateAdminUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class AdminUserBeans(
    private val adminUserDao: AdminUserDao,
    private val normalUserRepositoryImpl: NormalUserRepositoryImpl,
) {

    @Bean
    fun createAdminUserUseCase(): CreateAdminUserUseCase = CreateAdminUserUseCase.create(AdminUserRepositoryImpl(adminUserDao, normalUserRepositoryImpl))
}
