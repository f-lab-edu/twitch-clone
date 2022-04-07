package com.example.user.application.service

import com.example.user.application.port.out.SearchStreamerQuery
import com.example.user.domain.model.StreamerUserStatus
import com.example.user.util.MockStreamerUserRepository
import com.example.user.util.randomStreamUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[스트리머 유저] 조회")
class FindStreamerUserServiceTest {

    private lateinit var findStreamerService: FindStreamerUserService
    private lateinit var mockStreamerUserRepository: MockStreamerUserRepository

    @BeforeEach
    fun beforeEach() {
        mockStreamerUserRepository = MockStreamerUserRepository()
        findStreamerService = FindStreamerUserService(mockStreamerUserRepository)
    }

    @Test
    @DisplayName("스트리머 유저 요청 승인을 위하여 스트리머 유저 등록을 신청한 리스트를 가져온다.")
    fun `find pending streamer user list`() {
        // given
        saveStreamer()

        // when
        val pendingStreamerUsers = findStreamerService.findPendingStreamers()

        // then
        assertAll(
            { assertThat(pendingStreamerUsers).isNotEmpty },
            { assertThat(pendingStreamerUsers).allMatch { it.streamerStatus == StreamerUserStatus.PENDING} }
        )
    }

    @Test
    @DisplayName("스트리머 유저 요청이 존재하지 않을 경우 빈 리스트를 반환한다.")
    fun `find pending streamer user list if not exists return empty list`() {
        // when
        val pendingStreamerUsers = findStreamerService.findPendingStreamers()

        // then
        assertThat(pendingStreamerUsers).isEmpty()
    }

    @Test
    @DisplayName("스트리머 유저 닉네임을 통하여 해당 스트리머 유저 리스트를 가져온다.")
    fun `find streamer user by streamer nick name`() {
        // given
        saveRegisteredStreamer()
        val streamerQuery = SearchStreamerQuery("streamer")

        // when
        val pendingStreamerUsers = findStreamerService.findStreamers(streamerQuery)

        // then
        assertAll(
            { assertThat(pendingStreamerUsers).isNotEmpty },
            { assertThat(pendingStreamerUsers).allMatch { it.streamerStatus == StreamerUserStatus.REGISTERED} }
        )
    }

    private fun saveStreamer() {
        mockStreamerUserRepository.save(randomStreamUser())
    }

    private fun saveRegisteredStreamer(streamerNickname : String = "streamer") {
        val streamerUser = randomStreamUser(streamerNickname = streamerNickname)
        streamerUser.register()
        mockStreamerUserRepository.save(streamerUser)
    }
}
