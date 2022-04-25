package com.example.config.bean

import com.example.user.adpter.out.NormalUserRepositoryImpl
import com.example.user.adpter.out.PaymentPortImpl
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
    fun approveStreamerUserUseCase(): ApproveStreamerUserUseCase = ApproveStreamerUserUseCase.create(StreamerUserRepositoryImpl())

    @Bean
    fun createStreamerUserUseCase(): CreateStreamerUserUseCase = CreateStreamerUserUseCase.create(StreamerUserRepositoryImpl())

    @Bean
    fun findPendingStreamerUserUseCase(): FindPendingStreamerUserUseCase = FindPendingStreamerUserUseCase.create(StreamerUserRepositoryImpl())

    @Bean
    fun findStreamerUserUseCase(): FindStreamerUserUseCase = FindStreamerUserUseCase.create(StreamerUserRepositoryImpl())

    @Bean
    fun rejectStreamerUserUseCase(): RejectStreamerUserUseCase = RejectStreamerUserUseCase.create(StreamerUserRepositoryImpl())

    @Bean
    fun subscribeStreamerUserUseCase(): SubscribeStreamerUserUseCase =
        SubscribeStreamerUserUseCase.create(NormalUserRepositoryImpl(), StreamerUserRepositoryImpl(), PaymentPortImpl())

    @Bean
    fun suspendStreamerUserUseCase(): SuspendStreamerUserUseCase = SuspendStreamerUserUseCase.create(StreamerUserRepositoryImpl())

    @Bean
    fun updateStreamerUserSubscriptionCostUseCase(): UpdateStreamerUserSubscriptionCostUseCase =
        UpdateStreamerUserSubscriptionCostUseCase.create(StreamerUserRepositoryImpl())

    @Bean
    fun updateStreamerUserUseCase(): UpdateStreamerUserUseCase = UpdateStreamerUserUseCase.create(StreamerUserRepositoryImpl())
}
