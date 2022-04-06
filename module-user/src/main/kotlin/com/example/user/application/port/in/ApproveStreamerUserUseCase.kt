package com.example.user.application.port.`in`

import com.example.user.domain.model.StreamerUser
import java.util.*

interface ApproveStreamerUserUseCase {

    fun approveStreamerUser(streamerUsers: List<StreamerUser>)

}
