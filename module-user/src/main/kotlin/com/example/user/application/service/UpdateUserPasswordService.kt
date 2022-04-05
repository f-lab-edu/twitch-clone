package com.example.user.application.service

import com.example.user.application.port.`in`.UpdateUserPasswordCommand
import com.example.user.application.port.`in`.UpdateUserPasswordUseCase
import com.example.user.application.port.out.UserRepository
import com.example.user.domain.model.NormalUser
import java.util.*

internal class UpdateUserPasswordService(private val userRepository: UserRepository) : UpdateUserPasswordUseCase {

    // 추후 설정 파일이나 외부 주입으로 변경
    private val initPassword = "test1234"

    override fun updateUserPassword(updateUserPasswordCommand: UpdateUserPasswordCommand): NormalUser {
        return with(updateUserPasswordCommand) {
            userRepository.findById(id).let {
                userRepository.save(
                    NormalUser(
                        id = it.id, email = it.email,
                        password = password,
                        nickname = it.nickname
                    )
                )
            }
        }
    }

    override fun initUserPassword(id: UUID) {
        val user = userRepository.findById(id)
        userRepository.save(
            NormalUser(
                id = user.id, email = user.email,
                password = initPassword,
                nickname = user.nickname
            )
        )
    }
}
