package com.example.config.bean

import com.example.user.adpter.out.NormalUserDao
import com.example.user.adpter.out.NormalUserDynamicDao
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
    fun createNormalUserUseCase(
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao
    ): CreateNormalUserUseCase =
        CreateNormalUserUseCase.create(NormalUserRepositoryImpl(normalUserDao, normalUserDynamicDao))

    @Bean
    fun findNormalUserUseCase(
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao
    ): FindNormalUserUseCase =
        FindNormalUserUseCase.create(NormalUserRepositoryImpl(normalUserDao, normalUserDynamicDao))

    @Bean
    fun initNormalUserPasswordUseCase(
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao
    ): InitNormalUserPasswordUseCase =
        InitNormalUserPasswordUseCase.create(NormalUserRepositoryImpl(normalUserDao, normalUserDynamicDao))

    @Bean
    fun suspendNormalUserUseCase(
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao
    ): SuspendNormalUserUseCase =
        SuspendNormalUserUseCase.create(NormalUserRepositoryImpl(normalUserDao, normalUserDynamicDao))

    @Bean
    fun updateNormalUserPasswordUseCase(
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao
    ): UpdateNormalUserPasswordUseCase =
        UpdateNormalUserPasswordUseCase.create(NormalUserRepositoryImpl(normalUserDao, normalUserDynamicDao))

    @Bean
    fun updateNormalUserUseCase(
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao
    ): UpdateNormalUserUseCase =
        UpdateNormalUserUseCase.create(NormalUserRepositoryImpl(normalUserDao, normalUserDynamicDao))
}
