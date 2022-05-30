package com.example.user.adpter.`in`.admin

import com.example.user.application.port.`in`.admin.CreateAdminUserCommand
import com.example.user.application.port.`in`.admin.CreateAdminUserUseCase
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@Transactional
@RestController
class CreateAdminUserController(
    private val createAdminUserUseCase: CreateAdminUserUseCase,
) {

    @PostMapping(path = ["/api/admin"])
    fun create(@Valid @RequestBody req: CreateAdminUserCommand): CreateAdminUserResponse {
        return CreateAdminUserResponse(createAdminUserUseCase.createAdminUser(req).id)
    }
}

data class CreateAdminUserResponse(val id: UUID)
