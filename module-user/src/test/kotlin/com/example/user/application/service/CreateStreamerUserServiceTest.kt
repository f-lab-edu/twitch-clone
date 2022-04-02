package com.example.user.application.service

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.user.application.port.`in`.CreateStreamerUserCommand
import com.example.user.domain.model.StreamerUserStatus
import com.example.user.util.MockStreamerUserRepository
import com.example.user.util.randomUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[스트리머] 생성")
internal class CreateStreamerUserServiceTest {

    private lateinit var mockStreamerUserRepository: MockStreamerUserRepository
    private lateinit var createStreamerUserService: CreateStreamerUserService

    @BeforeEach
    fun beforeEach() {
        mockStreamerUserRepository = MockStreamerUserRepository()
        createStreamerUserService = CreateStreamerUserService(mockStreamerUserRepository)
    }

    @Test
    @DisplayName("유저 정보와 스트리머 닉네임으로 스트리머 회원을 생성 합니다")
    fun `create streamer user by user info and streamer nick name`() {
        // given
        val user = randomUser()
        val createStreamerCommand = CreateStreamerUserCommand(user, "테스트 스트리머")

        // when
        val createStreamer = createStreamerUserService.createStreamerUser(createStreamerCommand)

        // then
        val findStreamer = mockStreamerUserRepository.findById(createStreamer.id)

        assertAll(
            { assertThat(createStreamer).isEqualTo(findStreamer) },
            { assertThat(findStreamer.id).isEqualTo(user.id) },
            { assertThat(findStreamer.status).isEqualTo(StreamerUserStatus.PENDING) },
        )
    }

    @Test
    @DisplayName("이미 존재하는 스트리머 생성 시 CustomException이 발생합니다")
    fun `create streamer user by exists streamer info`() {
        // given
        val user = randomUser()
        val streamerCommand = CreateStreamerUserCommand(user, "테스트 스트리머")
        createStreamerUserService.createStreamerUser(streamerCommand)
        val existsStreamerCommand = CreateStreamerUserCommand(user, "존재하는 스트리머")

        // when
        val exception = Assertions.assertThrows(CustomException::class.java) {
            createStreamerUserService.createStreamerUser(existsStreamerCommand)
        }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.EXISTS_ENTITY)
    }
}
