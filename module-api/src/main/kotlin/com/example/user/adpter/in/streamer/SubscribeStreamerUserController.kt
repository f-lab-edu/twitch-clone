package com.example.user.adpter.`in`.streamer

import com.example.user.adpter.`in`.streamer.request.SubscribeRequest
import com.example.user.application.port.`in`.streamer.SubscribeStreamerUserUseCase
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class SubscribeStreamerUserController(
    private val subscribeStreamerUserUseCase: SubscribeStreamerUserUseCase,
    private val transactionTemplate: TransactionTemplate
) {

    @PatchMapping(path = ["/api/streamer/{id}"])
    fun subscribe(@PathVariable id: UUID, @Valid @RequestBody req: SubscribeRequest) {
        transactionTemplate.execute {
            subscribeStreamerUserUseCase.subscribe(req.normarUserId, id)
        }
    }
}
