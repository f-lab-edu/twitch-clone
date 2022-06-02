package com.example.user.adpter.`in`.normal

import com.example.user.adpter.`in`.normal.request.UpdateRequest
import com.example.user.application.port.`in`.normal.InitNormalUserPasswordUseCase
import com.example.user.application.port.`in`.normal.SuspendNormalUserUseCase
import com.example.user.application.port.`in`.normal.UpdateNormalUserPasswordUseCase
import com.example.user.application.port.`in`.normal.UpdateNormalUserUseCase
import com.example.user.application.port.`in`.normal.UpdateUserCommand
import com.example.user.application.port.`in`.normal.UpdateUserPasswordCommand
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@Transactional
@RestController
class UpdateNormalUserController(
    private val initNormalUserPasswordUseCase: InitNormalUserPasswordUseCase,
    private val suspendNormalUserUseCase: SuspendNormalUserUseCase,
    private val updateNormalUserPasswordUseCase: UpdateNormalUserPasswordUseCase,
    private val updateNormalUserUseCase: UpdateNormalUserUseCase
) {

    @PatchMapping(path = ["/api/user/{id}/paassword/init"])
    fun initPassword(@PathVariable id: UUID) {
        initNormalUserPasswordUseCase.initNormalUserPassword(id)
    }


    @PatchMapping(path = ["/api/user/{id}/suspend"])
    fun suspend(@PathVariable id: UUID) {
        suspendNormalUserUseCase.suspendNormalUser(id)
    }

    @PatchMapping(path = ["/api/user/{id}"])
    fun create(@PathVariable id: UUID, @Valid @RequestBody req: UpdateRequest) {
        req.password?.let {
            val command = UpdateUserPasswordCommand(id, password = it)
            updateNormalUserPasswordUseCase.updateNormalUserPassword(command)
        }

        req.nickname?.let {
            val command = UpdateUserCommand(id, nickname = it)
            updateNormalUserUseCase.updateNormalUser(command)
        }
    }
}
