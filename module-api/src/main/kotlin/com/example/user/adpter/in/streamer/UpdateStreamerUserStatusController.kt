package com.example.user.adpter.`in`.streamer

import com.example.user.adpter.`in`.streamer.request.UpdateStreamerUserStatusCommand
import com.example.user.application.port.`in`.streamer.SuspendStreamerUserUseCase
import com.example.user.domain.model.StreamerUserStatus
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class UpdateStreamerUserStatusController(
//    private val approveStreamerUserUseCase: ApproveStreamerUserUseCase,
//    private val rejectStreamerUserUseCase: RejectStreamerUserUseCase,
    private val suspendStreamerUserUseCase: SuspendStreamerUserUseCase,
    private val transactionTemplate: TransactionTemplate
) {

    @PatchMapping(path = ["/api/streamer/{id}/status"])
    fun updateStatus(@PathVariable id: UUID, @Valid @RequestBody req: UpdateStreamerUserStatusCommand) {
        transactionTemplate.execute {
            when (req.status) {
//            StreamerUserStatus.REGISTERED -> approveStreamerUserUseCase.approveStreamerUser()
//            StreamerUserStatus.REJECTED -> rejectStreamerUserUseCase.rejectStreamerUser()
                StreamerUserStatus.SUSPENDED -> suspendStreamerUserUseCase.suspendStreamer(id)
                else -> {}
            }
        }
    }
}
