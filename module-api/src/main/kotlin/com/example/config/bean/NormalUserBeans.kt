package com.example.config.bean

import com.example.user.adpter.out.NormalUserDao
import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.application.port.`in`.normal.CreateNormalUserUseCase
import com.example.user.application.port.`in`.normal.FindNormalUserUseCase
import com.example.user.application.port.`in`.normal.InitNormalUserPasswordUseCase
import com.example.user.application.port.`in`.normal.SuspendNormalUserUseCase
import com.example.user.application.port.`in`.normal.UpdateNormalUserPasswordUseCase
import com.example.user.application.port.`in`.normal.UpdateNormalUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class NormalUserBeans(private val normalUserDao: NormalUserDao) {

    @Bean
    fun createNormalUserUseCase(): CreateNormalUserUseCase = CreateNormalUserUseCase.create(NormalUserRepositoryImpl(normalUserDao))

    @Bean
    fun findNormalUserUseCase(): FindNormalUserUseCase = FindNormalUserUseCase.create(NormalUserRepositoryImpl(normalUserDao))

    @Bean
    fun initNormalUserPasswordUseCase(): InitNormalUserPasswordUseCase = InitNormalUserPasswordUseCase.create(NormalUserRepositoryImpl(normalUserDao))

    @Bean
    fun suspendNormalUserUseCase(): SuspendNormalUserUseCase = SuspendNormalUserUseCase.create(NormalUserRepositoryImpl(normalUserDao))

    @Bean
    fun updateNormalUserPasswordUseCase(): UpdateNormalUserPasswordUseCase = UpdateNormalUserPasswordUseCase.create(NormalUserRepositoryImpl(normalUserDao))

    @Bean
    fun updateNormalUserUseCase(): UpdateNormalUserUseCase = UpdateNormalUserUseCase.create(NormalUserRepositoryImpl(normalUserDao))
}
