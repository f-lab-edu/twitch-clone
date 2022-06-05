package com.example.user.adpter.`in`.normal

import com.example.user.adpter.`in`.normal.response.CreateNormalUserResponse
import com.example.user.application.port.`in`.normal.CreateNormalUserCommand
import com.example.user.application.port.`in`.normal.CreateNormalUserUseCase
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class CreateNormalUserController(
    private val createNormalUserUseCase: CreateNormalUserUseCase,
    private val transactionTemplate: TransactionTemplate
) {

    @PostMapping(path = ["/api/user"])
    fun create(@Valid @RequestBody req: CreateNormalUserCommand): CreateNormalUserResponse {
        val id = transactionTemplate.execute {
            createNormalUserUseCase.createNormalUser(req).id
        } as UUID
        return CreateNormalUserResponse(id)
    }
}
