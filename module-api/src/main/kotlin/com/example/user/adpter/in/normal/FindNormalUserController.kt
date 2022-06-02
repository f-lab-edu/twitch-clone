package com.example.user.adpter.`in`.normal

import com.example.user.adpter.`in`.normal.response.FindNormalUserResponse
import com.example.user.application.port.`in`.normal.FindNormalUserQuery
import com.example.user.application.port.`in`.normal.FindNormalUserUseCase
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Transactional(readOnly = true)
@RestController
class FindNormalUserController(
    private val findNormalUserUseCase: FindNormalUserUseCase
) {

    @GetMapping(path = ["/api/user"])
    fun find(@Valid @RequestBody req: FindNormalUserQuery): FindNormalUserResponse {
        return FindNormalUserResponse(findNormalUserUseCase.findNormalUsers(req))
    }
}
