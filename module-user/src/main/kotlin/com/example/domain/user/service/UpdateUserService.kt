package com.example.domain.user.service

import com.example.domain.user.entity.User
import com.example.domain.user.repository.UserRepository
import com.example.exception.CustomException
import com.example.exception.ErrorCode
import java.util.*

class UpdateUserService(private val userRepository: UserRepository) {

    /**
     * - 현재 변경 가능한 필드는 nickName만 입니다.
     * - 항상 새로운 객체를 만듭니다.
     */
    fun updateUser(id: UUID, nickName: String?): User {
        return userRepository.findById(id)?.let {
            userRepository.save(
                User(
                    id = it.id, email = it.email, password = it.password,
                    nickName = nickName ?: it.nickName
                )
            )
        }
            ?: throw CustomException(ErrorCode.ENTITY_NOT_FOUND)
    }

    fun updateUserPassword(id: UUID, password: String): User {
        return userRepository.findById(id)?.let {
            userRepository.save(
                User(
                    id = it.id, email = it.email,
                    password = password,
                    nickName = it.nickName
                )
            )
        }
            ?: throw CustomException(ErrorCode.ENTITY_NOT_FOUND)
    }
}
