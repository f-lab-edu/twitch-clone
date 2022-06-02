package com.example.user.adpter.`in`.streamer

import com.example.user.adpter.`in`.streamer.response.CreateStreamerUserResponse
import com.example.user.application.port.`in`.streamer.CreateStreamerUserCommand
import com.example.user.application.port.`in`.streamer.CreateStreamerUserUseCase
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Transactional
@RestController
class CreateStreamerUserController(
    private val createStreamerUserUseCase: CreateStreamerUserUseCase,
) {

    @PostMapping(path = ["/api/streamer"])
    fun create(@Valid @RequestBody req: CreateStreamerUserCommand): CreateStreamerUserResponse {
        return CreateStreamerUserResponse(createStreamerUserUseCase.createStreamerUser(req).id)
    }
}
