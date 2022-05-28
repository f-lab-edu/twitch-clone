package com.example.user.adpter.`in`

import com.example.user.application.port.`in`.normal.FindNormalUserQuery
import com.example.user.application.port.`in`.normal.FindNormalUserUseCase
import com.example.user.domain.model.NormalUser
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Transactional(readOnly = true)
@RestController
class NormalUserController(
    private val findNormalUserUseCase: FindNormalUserUseCase
) {

    @GetMapping(path = ["/user"])
    fun find(@Valid @RequestBody req: FindNormalUserQuery): FindNormalUserResponse {
        return FindNormalUserResponse(findNormalUserUseCase.findNormalUsers(req))
    }
}

data class FindNormalUserResponse(val normalUsers: List<NormalUser>)
