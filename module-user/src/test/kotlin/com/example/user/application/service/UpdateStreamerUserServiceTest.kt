package com.example.user.application.service

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import com.example.user.domain.model.User
import com.example.user.util.MockStreamerUserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[스트리머] 수정")
class UpdateStreamerUserServiceTest {

    private lateinit var updateStreamerService: UpdateStreamerUserService
    private lateinit var mockStreamerUserRepository: MockStreamerUserRepository

    @BeforeEach
    fun beforeEach() {
        mockStreamerUserRepository = MockStreamerUserRepository()
        updateStreamerService = UpdateStreamerUserService(mockStreamerUserRepository)
    }

    @Test
    @DisplayName("스트리머 요청 리스트가 존재할 경우 스트리머 요청을 승인한다.")
    fun `pending streamer user register`() {
        // given
        createPendingStreamer()
        val pendingStreamers = selectPendingStreamer()

        // when
        updateStreamerService.approveStreamerUser(pendingStreamers)

        // then
        val approvedUuid = mockStreamerUserRepository.findById(pendingStreamers[0].id())

        Assertions.assertAll(
            { assertThat(approvedUuid).isNotNull },
            { assertThat(approvedUuid.status).isEqualTo(StreamerUserStatus.REGISTERED) }
        )
    }

    @Test
    @DisplayName("스트리머 요청 리스트가 존재하지만 결격 사유 존재 시 스트리머 요청을 거절한다.")
    fun `pending streamer user rejected`() {
        // given
        createPendingStreamer()
        val pendingStreamers = selectPendingStreamer()

        // when
        updateStreamerService.rejectStreamerUser(pendingStreamers)

        // then
        val approvedUuid = mockStreamerUserRepository.findById(pendingStreamers[0].id())

        Assertions.assertAll(
            { assertThat(approvedUuid).isNotNull },
            { assertThat(approvedUuid.status).isEqualTo(StreamerUserStatus.REJECTED) }
        )
    }

    private fun createUser() = User(email = "test@Test.com", nickname = "test", password = "password01")

    private fun createStreamerUser(user: User) = StreamerUser(user = user, streamerNickname = "streamer")

    private fun createPendingStreamer() {
        val user = createUser()
        mockStreamerUserRepository.save(createStreamerUser(user))
    }

    private fun selectPendingStreamer() : List<StreamerUser>{
        return mockStreamerUserRepository.findAllByStatus(StreamerUserStatus.PENDING)
    }
}
