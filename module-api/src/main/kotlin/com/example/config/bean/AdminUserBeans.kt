package com.example.config.bean

import com.example.user.adpter.out.AdminUserRepositoryImpl
import com.example.user.application.port.`in`.admin.CreateAdminUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class AdminUserBeans {

    @Bean
    fun createAdminUserUseCase(): CreateAdminUserUseCase = CreateAdminUserUseCase.create(AdminUserRepositoryImpl())
}
