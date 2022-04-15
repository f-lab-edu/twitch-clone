package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ModuleApiApplication

fun main(args: Array<String>) {
    @Suppress("SpreadOperator")
    runApplication<ModuleApiApplication>(*args)
}
