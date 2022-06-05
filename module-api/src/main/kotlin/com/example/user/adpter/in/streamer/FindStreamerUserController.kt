package com.example.user.adpter.`in`.streamer

import com.example.user.adpter.`in`.streamer.request.FindStreamerUserRequest
import com.example.user.adpter.`in`.streamer.response.FindStreamerUserResponse
import com.example.user.application.port.`in`.streamer.FindPendingStreamerUserUseCase
import com.example.user.application.port.`in`.streamer.FindStreamerUserUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class FindStreamerUserController(
    private val findStreamerUserUseCase: FindStreamerUserUseCase,
    private val findPendingStreamerUserUseCase: FindPendingStreamerUserUseCase
) {

    @GetMapping(path = ["/api/streamer"])
    fun find(@Valid @RequestBody req: FindStreamerUserRequest): FindStreamerUserResponse {
        // 스트리머 이름으로 조회
        req.streamerNickname?.let {
            val findStreamerUserQuery = com.example.user.application.port.`in`.streamer.FindStreamerUserQuery(req.streamerNickname)
            return FindStreamerUserResponse(findStreamerUserUseCase.findStreamers(findStreamerUserQuery))
        }

        // 전체 조회
        return FindStreamerUserResponse(findPendingStreamerUserUseCase.findPendingStreamers())
    }
}
