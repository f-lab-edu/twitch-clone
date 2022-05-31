package com.example.user.adpter.`in`.streamer

import com.example.user.application.port.`in`.streamer.UpdateStreamerUserSubscriptionCostUseCase
import com.example.user.application.port.`in`.streamer.UpdateStreamerUserUseCase
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@Transactional
@RestController
class UpdateStreamerUserController(
    private val updateStreamerUserSubscriptionCostUseCase: UpdateStreamerUserSubscriptionCostUseCase,
    private val updateStreamerUserUseCase: UpdateStreamerUserUseCase,
) {

    @PatchMapping(path = ["/api/streamer/{id}"])
    fun update(@PathVariable id: UUID, @Valid @RequestBody req: UpdateCommand) {
        req.subscriptionCost?.let {
            updateStreamerUserSubscriptionCostUseCase.updateSubscriptionCost(id, it)
        }

        req.nickname?.let {
            updateStreamerUserUseCase.updateStreamerNickname(id, it)
        }
    }
}

data class UpdateCommand(val subscriptionCost: Int?, val nickname: String?)
