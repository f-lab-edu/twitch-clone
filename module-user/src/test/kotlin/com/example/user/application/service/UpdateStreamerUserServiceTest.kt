package com.example.user.application.service

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserStatus
import com.example.user.util.MockStreamerUserRepository
import com.example.user.util.randomStreamUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("[스트리머 유저] 수정")
class UpdateStreamerUserServiceTest {

    private lateinit var updateStreamerService: UpdateStreamerUserService
    private lateinit var mockStreamerUserRepository: MockStreamerUserRepository

    @BeforeEach
    fun beforeEach() {
        mockStreamerUserRepository = MockStreamerUserRepository()
        updateStreamerService = UpdateStreamerUserService(mockStreamerUserRepository)
    }

    @Test
    @DisplayName("스트리머 유저 요청 리스트가 존재할 경우 스트리머 유저 등록 요청을 승인한다.")
    fun `pending streamer user register`() {
        // given
        saveStreamerUser()
        val pendingStreamers = selectPendingStreamerUser()

        // when
        updateStreamerService.approveStreamerUser(pendingStreamers)

        // then
        val approvedUuid = mockStreamerUserRepository.findById(pendingStreamers[0].id)

        assertAll(
            { assertThat(approvedUuid).isNotNull },
            { assertThat(approvedUuid.streamerStatus).isEqualTo(StreamerUserStatus.REGISTERED) }
        )
    }

    @Test
    @DisplayName("스트리머 유저 요청 리스트가 존재하지만 결격 사유 존재 시 스트리머 유저 등록 요청을 거절한다.")
    fun `pending streamer user rejected`() {
        // given
        saveStreamerUser()
        val pendingStreamers = selectPendingStreamerUser()

        // when
        updateStreamerService.rejectStreamerUser(pendingStreamers)

        // then
        val approvedUuid = mockStreamerUserRepository.findById(pendingStreamers[0].id)

        assertAll(
            { assertThat(approvedUuid).isNotNull },
            { assertThat(approvedUuid.streamerStatus).isEqualTo(StreamerUserStatus.REJECTED) }
        )
    }

    @Test
    @DisplayName("스트리머 유저의 상태를 정지 상태로 변경한다.")
    fun `update streamer user status suspense`() {
        // given
        saveStreamerUser()
        val streamerUsers = selectPendingStreamerUser()
        updateStreamerService.approveStreamerUser(streamerUsers)
        val id = streamerUsers[0].id
        // when
        updateStreamerService.suspendStreamer(id)

        // then
        val findUser = mockStreamerUserRepository.findById(id)

        assertAll(
            { assertThat(findUser).isNotNull },
            { assertThat(findUser.streamerStatus).isEqualTo(StreamerUserStatus.SUSPENDED) },
        )
    }

    @Test
    @DisplayName("스트리머 유저의 닉네임을 변경한다.")
    fun `update streamer user nickname`() {
        // given
        val streamerUser = saveStreamerUser()
        val id = streamerUser.id
        val changeNickname = "change nickname"

        // when
        updateStreamerService.updateStreamerNickname(id, changeNickname)

        // then
        val findUser = mockStreamerUserRepository.findById(id)

        assertAll(
            { assertThat(findUser).isNotNull },
            { assertThat(findUser.streamerNickname).isEqualTo(changeNickname) },
        )
    }

    private fun saveStreamerUser() : StreamerUser{
        return mockStreamerUserRepository.save(randomStreamUser())
    }

    private fun selectPendingStreamerUser() : List<StreamerUser>{
        return mockStreamerUserRepository.findAllByStatus(StreamerUserStatus.PENDING)
    }
}
