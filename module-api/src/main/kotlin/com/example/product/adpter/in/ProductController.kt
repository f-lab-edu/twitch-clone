package com.example.product.adpter.`in`

import com.example.product.adpter.`in`.response.ProductResponse
import com.example.product.application.port.`in`.FindProductUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val findProductUseCase: FindProductUseCase) {

    @GetMapping(path = ["/api/product/{productNo}"])
    fun find(@PathVariable productNo : Int): ProductResponse {
        return ProductResponse(findProductUseCase.findProduct(productNo).productNo)
    }
}
