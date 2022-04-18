package com.example.user.adpter.`in`

import com.example.user.application.port.`in`.normal.CreateNormalUserCommand
import com.example.user.application.port.`in`.normal.CreateNormalUserUseCase
import com.example.user.application.port.`in`.normal.FindNormalUserQuery
import com.example.user.application.port.`in`.normal.FindNormalUserUseCase
import com.example.user.domain.model.NormalUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class NormalUserController(
    private val createNormalUserUseCase: CreateNormalUserUseCase,
    private val findNormalUserUseCase: FindNormalUserUseCase
) {

    @PostMapping(path = ["/user"])
    fun create(@Valid @RequestBody req: CreateNormalUserCommand): CreateNormalUserResponse {
        return CreateNormalUserResponse(createNormalUserUseCase.createNormalUser(req).id)
    }

    @GetMapping(path = ["/user"])
    fun find(@Valid @RequestBody req: FindNormalUserQuery): FindNormalUserResponse {
        return FindNormalUserResponse(findNormalUserUseCase.findNormalUsers(req))
    }
}

data class CreateNormalUserResponse(val id: UUID)

data class FindNormalUserResponse(val normalUsers: List<NormalUser>)
