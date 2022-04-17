package com.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping(path = ["/test/sample"])
    fun sample(@RequestParam message: String, @RequestParam number: Int): SampleResponse {
        return SampleResponse("im sample: $message, $number")
    }

    @GetMapping(path = ["/test/error"])
    fun error(@RequestParam message: String): Boolean {
        throw RuntimeException("error!!!")
    }
}

data class SampleResponse(val string: String)
