package com.example.user.adpter.`in`.admin

import com.example.user.adpter.`in`.admin.response.CreateAdminUserResponse
import com.example.user.application.port.`in`.admin.CreateAdminUserCommand
import com.example.user.application.port.`in`.admin.CreateAdminUserUseCase
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class CreateAdminUserController(
    private val createAdminUserUseCase: CreateAdminUserUseCase,
    private val transactionTemplate: TransactionTemplate
) {
    @PostMapping(path = ["/api/admin"])
    fun create(@Valid @RequestBody req: CreateAdminUserCommand): CreateAdminUserResponse {
        val id = transactionTemplate.execute {
            createAdminUserUseCase.createAdminUser(req).id
        } as UUID
        return CreateAdminUserResponse(id)
    }
}
