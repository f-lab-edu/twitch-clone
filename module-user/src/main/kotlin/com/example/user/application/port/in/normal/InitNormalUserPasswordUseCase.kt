package com.example.user.application.port.`in`.normal

import com.example.user.application.port.out.NormalUserRepository
import com.example.user.application.service.normal.UpdateNormalUserPasswordService
import java.util.*

interface InitNormalUserPasswordUseCase {

    fun initNormalUserPassword(id: UUID)

    companion object {
        fun create(normalUserRepository: NormalUserRepository): InitNormalUserPasswordUseCase = UpdateNormalUserPasswordService(normalUserRepository)
    }
}
