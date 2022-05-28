package com.example.user.adpter.`in`

import com.example.user.application.port.`in`.normal.CreateNormalUserCommand
import com.example.user.application.port.`in`.normal.CreateNormalUserUseCase
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@Transactional
@RestController
class CreateNormalUserController(
    private val createNormalUserUseCase: CreateNormalUserUseCase,
) {

    @PostMapping(path = ["/user"])
    fun create(@Valid @RequestBody req: CreateNormalUserCommand): CreateNormalUserResponse {
        return CreateNormalUserResponse(createNormalUserUseCase.createNormalUser(req).id)
    }
}

data class CreateNormalUserResponse(val id: UUID)
