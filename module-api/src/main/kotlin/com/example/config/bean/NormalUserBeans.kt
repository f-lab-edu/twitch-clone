package com.example.config.bean

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
internal class NormalUserBeans {

    @Bean
    fun createNormalUserUseCase(): CreateNormalUserUseCase = CreateNormalUserUseCase.create(NormalUserRepositoryImpl())

    @Bean
    fun findNormalUserUseCase(): FindNormalUserUseCase = FindNormalUserUseCase.create(NormalUserRepositoryImpl())

    @Bean
    fun initNormalUserPasswordUseCase(): InitNormalUserPasswordUseCase = InitNormalUserPasswordUseCase.create(NormalUserRepositoryImpl())

    @Bean
    fun suspendNormalUserUseCase(): SuspendNormalUserUseCase = SuspendNormalUserUseCase.create(NormalUserRepositoryImpl())

    @Bean
    fun updateNormalUserPasswordUseCase(): UpdateNormalUserPasswordUseCase = UpdateNormalUserPasswordUseCase.create(NormalUserRepositoryImpl())

    @Bean
    fun updateNormalUserUseCase(): UpdateNormalUserUseCase = UpdateNormalUserUseCase.create(NormalUserRepositoryImpl())
}
