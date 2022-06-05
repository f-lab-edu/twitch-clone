package com.example.user.adpter.`in`.streamer

import com.example.user.adpter.`in`.streamer.request.UpdateStreamerUserRequest
import com.example.user.application.port.`in`.streamer.UpdateStreamerUserSubscriptionCostUseCase
import com.example.user.application.port.`in`.streamer.UpdateStreamerUserUseCase
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class UpdateStreamerUserController(
    private val updateStreamerUserSubscriptionCostUseCase: UpdateStreamerUserSubscriptionCostUseCase,
    private val updateStreamerUserUseCase: UpdateStreamerUserUseCase,
    private val transactionTemplate: TransactionTemplate
) {

    @PatchMapping(path = ["/api/streamer/{id}"])
    fun update(@PathVariable id: UUID, @Valid @RequestBody req: UpdateStreamerUserRequest) {
        transactionTemplate.execute {
            req.subscriptionCost?.let {
                updateStreamerUserSubscriptionCostUseCase.updateSubscriptionCost(id, it)
            }

            req.nickname?.let {
                updateStreamerUserUseCase.updateStreamerNickname(id, it)
            }
        }
    }
}
