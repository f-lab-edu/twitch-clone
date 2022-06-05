package com.example.user.adpter.`in`.streamer

import com.example.user.adpter.`in`.streamer.response.CreateStreamerUserResponse
import com.example.user.application.port.`in`.streamer.CreateStreamerUserCommand
import com.example.user.application.port.`in`.streamer.CreateStreamerUserUseCase
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class CreateStreamerUserController(
    private val createStreamerUserUseCase: CreateStreamerUserUseCase,
    private val transactionTemplate: TransactionTemplate
) {

    @PostMapping(path = ["/api/streamer"])
    fun create(@Valid @RequestBody req: CreateStreamerUserCommand): CreateStreamerUserResponse {
        val id = transactionTemplate.execute {
            createStreamerUserUseCase.createStreamerUser(req).id
        } as UUID
        return CreateStreamerUserResponse(id)
    }
}
