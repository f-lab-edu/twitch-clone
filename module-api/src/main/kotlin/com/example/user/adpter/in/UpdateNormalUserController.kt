package com.example.user.adpter.`in`

import com.example.user.application.port.`in`.normal.InitNormalUserPasswordUseCase
import com.example.user.application.port.`in`.normal.SuspendNormalUserUseCase
import com.example.user.application.port.`in`.normal.UpdateNormalUserPasswordUseCase
import com.example.user.application.port.`in`.normal.UpdateNormalUserUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateNormalUserController(
    private val initNormalUserPasswordUseCase: InitNormalUserPasswordUseCase,
    private val suspendNormalUserUseCase: SuspendNormalUserUseCase,
    private val updateNormalUserPasswordUseCase: UpdateNormalUserPasswordUseCase,
    private val updateNormalUserUseCase: UpdateNormalUserUseCase
)
