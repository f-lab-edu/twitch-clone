package com.example.user.adpter.`in`

import com.example.user.application.port.`in`.normal.CreateNormalUserCommand
import com.example.user.application.port.`in`.normal.CreateNormalUserUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class NormalUserUseCaseController(private val createNormalUserUseCase: CreateNormalUserUseCase) {

    @PostMapping(path = ["/user"])
    fun create(@Valid @RequestBody req: CreateNormalUserCommand): UUID {
        return createNormalUserUseCase.createNormalUser(req).id
    }
}
