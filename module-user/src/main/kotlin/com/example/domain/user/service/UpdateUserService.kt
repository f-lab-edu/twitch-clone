package com.example.domain.user.service

import com.example.domain.user.entity.User
import com.example.domain.user.repository.UserRepository
import com.example.exception.CustomException
import com.example.exception.ErrorCode
import java.util.*

class UpdateUserService(private val userRepository: UserRepository) {

    /**
     * - 현재 변경 가능한 필드는 nickname만 입니다.
     * - 항상 새로운 객체를 만듭니다.
     */
    fun updateUser(id: UUID, nickname: String?): User {
        return userRepository.findById(id).let {
            userRepository.save(
                User(
                    id = it.id, email = it.email, password = it.password,
                    nickname = nickname ?: it.nickname
                )
            )
        }
    }

    fun updateUserPassword(id: UUID, password: String): User {
        return userRepository.findById(id).let {
            userRepository.save(
                User(
                    id = it.id, email = it.email,
                    password = password,
                    nickname = it.nickname
                )
            )
        }
    }
}
