package com.example.user.adpter.`in`.streamer

import com.example.user.application.port.`in`.streamer.SubscribeStreamerUserUseCase
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@Transactional
@RestController
class SubscribeStreamerUserController(
    private val subscribeStreamerUserUseCase: SubscribeStreamerUserUseCase
) {

    @PatchMapping(path = ["/api/streamer/{id}"])
    fun subscribe(@PathVariable id: UUID, @Valid @RequestBody req: SubscribeCommand) {
        subscribeStreamerUserUseCase.subscribe(req.normarUserId, id)
    }
}

data class SubscribeCommand(val normarUserId: UUID)
