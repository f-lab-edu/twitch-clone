package com.example.product.domain.model

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import java.util.*

interface Product {
    val productNo : Int
    val productName : String
    val price : Int
    var stock: Int
    val streamerUserId: UUID

    fun minusStock(stock: Int) {
        validationStock(stock)
        this.stock = this.stock - stock
    }

    private fun validationStock(stock: Int) {
        if (this.stock < stock) {
            throw CustomException(ErrorCode.PRODUCT_NOT_ENOUGH)
        }
    }
}