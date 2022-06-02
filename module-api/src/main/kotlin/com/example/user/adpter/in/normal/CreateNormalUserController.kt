package com.example.user.adpter.`in`.normal

import com.example.user.adpter.`in`.normal.response.CreateNormalUserResponse
import com.example.user.application.port.`in`.normal.CreateNormalUserCommand
import com.example.user.application.port.`in`.normal.CreateNormalUserUseCase
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Transactional
@RestController
class CreateNormalUserController(
    private val createNormalUserUseCase: CreateNormalUserUseCase,
) {

    @PostMapping(path = ["/api/user"])
    fun create(@Valid @RequestBody req: CreateNormalUserCommand): CreateNormalUserResponse {
        return CreateNormalUserResponse(createNormalUserUseCase.createNormalUser(req).id)
    }
}
