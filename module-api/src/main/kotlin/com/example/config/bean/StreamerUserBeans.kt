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
internal class StreamerUserBeans(
    private val streamerUserDao: StreamerUserDao,
    private val streamerUserDynamicDao: StreamerUserDynamicDao,
    private val normalUserDao: NormalUserDao,
    private val normalUserDynamicDao: NormalUserDynamicDao,
    private val normalUserRepositoryImpl: NormalUserRepositoryImpl,
) {

    @Bean
    fun approveStreamerUserUseCase(): ApproveStreamerUserUseCase =
        ApproveStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun createStreamerUserUseCase(): CreateStreamerUserUseCase =
        CreateStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun findPendingStreamerUserUseCase(): FindPendingStreamerUserUseCase =
        FindPendingStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun findStreamerUserUseCase(): FindStreamerUserUseCase =
        FindStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun rejectStreamerUserUseCase(): RejectStreamerUserUseCase =
        RejectStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun subscribeStreamerUserUseCase(): SubscribeStreamerUserUseCase =
        SubscribeStreamerUserUseCase.create(
            NormalUserRepositoryImpl(normalUserDao, normalUserDynamicDao),
            StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl),
            PaymentPortImpl()
        )

    @Bean
    fun suspendStreamerUserUseCase(): SuspendStreamerUserUseCase =
        SuspendStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))

    @Bean
    fun updateStreamerUserSubscriptionCostUseCase(): UpdateStreamerUserSubscriptionCostUseCase =
        UpdateStreamerUserSubscriptionCostUseCase.create(
            StreamerUserRepositoryImpl(
                streamerUserDao,
                streamerUserDynamicDao,
                normalUserDao,
                normalUserRepositoryImpl
            )
        )

    @Bean
    fun updateStreamerUserUseCase(): UpdateStreamerUserUseCase =
        UpdateStreamerUserUseCase.create(StreamerUserRepositoryImpl(streamerUserDao, streamerUserDynamicDao, normalUserDao, normalUserRepositoryImpl))
}
