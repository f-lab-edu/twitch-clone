package com.example.user.application.service

import com.example.user.application.port.`in`.InitNormalUserPasswordUseCase
import com.example.user.application.port.`in`.UpdateNormalUserPasswordUseCase
import com.example.user.application.port.`in`.UpdateUserPasswordCommand
import com.example.user.application.port.out.NormalUserRepository
import com.example.user.domain.model.NormalUser
import java.util.*

internal class UpdateNormalUserPasswordService(private val normalUserRepository: NormalUserRepository) : UpdateNormalUserPasswordUseCase,
    InitNormalUserPasswordUseCase {

    // 추후 설정 파일이나 외부 주입으로 변경
    private val initPassword = "test1234"

    override fun updateNormalUserPassword(updateUserPasswordCommand: UpdateUserPasswordCommand): NormalUser {
        return with(updateUserPasswordCommand) {
            val normalUser = normalUserRepository.findById(id)
            normalUser.updatePassword(password)
            normalUserRepository.save(normalUser)
        }
    }

    override fun initNormalUserPassword(id: UUID) {
        val user = normalUserRepository.findById(id)
        user.updatePassword(initPassword)
        normalUserRepository.save(user)
    }
}
