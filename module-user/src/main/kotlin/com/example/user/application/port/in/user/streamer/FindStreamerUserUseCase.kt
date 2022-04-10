package com.example.user.application.port.`in`.user.streamer

import com.example.user.application.port.out.SearchStreamerQuery
import com.example.user.domain.model.StreamerUser

interface FindStreamerUserUseCase {
    fun findStreamers(searchStreamerQuery: SearchStreamerQuery): List<StreamerUser>
}
