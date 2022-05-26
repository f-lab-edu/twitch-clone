package com.example.config.bean

import com.example.user.adpter.out.NormalUserDao
import com.example.user.adpter.out.NormalUserDynamicDao
import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.adpter.out.PaymentPortImpl
import com.example.user.adpter.out.StreamerUserDao
import com.example.user.adpter.out.StreamerUserDynamicDao
import com.example.user.adpter.out.StreamerUserRepositoryImpl
import com.example.user.application.port.`in`.streamer.ApproveStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.CreateStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.FindPendingStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.FindStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.RejectStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.SubscribeStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.SuspendStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.UpdateStreamerUserSubscriptionCostUseCase
import com.example.user.application.port.`in`.streamer.UpdateStreamerUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class StreamerUserBeans {

    @Bean
    fun approveStreamerUserUseCase(
        streamerUserDao: StreamerUserDao,
        streamerUserDynamicDao: StreamerUserDynamicDao,
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): ApproveStreamerUserUseCase =
        ApproveStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun createStreamerUserUseCase(
        streamerUserDao: StreamerUserDao,
        streamerUserDynamicDao: StreamerUserDynamicDao,
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): CreateStreamerUserUseCase =
        CreateStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun findPendingStreamerUserUseCase(
        streamerUserDao: StreamerUserDao,
        streamerUserDynamicDao: StreamerUserDynamicDao,
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): FindPendingStreamerUserUseCase =
        FindPendingStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun findStreamerUserUseCase(
        streamerUserDao: StreamerUserDao,
        streamerUserDynamicDao: StreamerUserDynamicDao,
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): FindStreamerUserUseCase =
        FindStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun rejectStreamerUserUseCase(
        streamerUserDao: StreamerUserDao,
        streamerUserDynamicDao: StreamerUserDynamicDao,
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): RejectStreamerUserUseCase =
        RejectStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun subscribeStreamerUserUseCase(
        streamerUserDao: StreamerUserDao,
        streamerUserDynamicDao: StreamerUserDynamicDao,
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): SubscribeStreamerUserUseCase =
        SubscribeStreamerUserUseCase.create(
            NormalUserRepositoryImpl(normalUserDao, normalUserDynamicDao),
            StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl),
            PaymentPortImpl()
        )

    @Bean
    fun suspendStreamerUserUseCase(
        streamerUserDao: StreamerUserDao,
        streamerUserDynamicDao: StreamerUserDynamicDao,
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): SuspendStreamerUserUseCase =
        SuspendStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun updateStreamerUserSubscriptionCostUseCase(
        streamerUserDao: StreamerUserDao,
        streamerUserDynamicDao: StreamerUserDynamicDao,
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): UpdateStreamerUserSubscriptionCostUseCase =
        UpdateStreamerUserSubscriptionCostUseCase.create(
            StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl)
        )

    @Bean
    fun updateStreamerUserUseCase(
        streamerUserDao: StreamerUserDao,
        streamerUserDynamicDao: StreamerUserDynamicDao,
        normalUserDao: NormalUserDao,
        normalUserDynamicDao: NormalUserDynamicDao,
        normalUserRepositoryImpl: NormalUserRepositoryImpl
    ): UpdateStreamerUserUseCase =
        UpdateStreamerUserUseCase.create(
            StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl)
        )
}
