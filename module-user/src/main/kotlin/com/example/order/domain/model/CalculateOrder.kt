package com.example.order.domain.model

import com.example.product.domain.model.Product

internal interface CalculateOrder {
    val orderProduct: Map<Product, Int>
    val deliveryFreeProductPrice: Int
    val defaultDeliveryPrice: Int

    fun calculateProductsPrice(): Int {
        var price = 0
        for (product in orderProduct) {
            price += product.key.price * product.value
        }
        return price
    }

    fun calculateDeliveryPrice(): Int {
        return if (calculateProductsPrice() >= deliveryFreeProductPrice) 0 else defaultDeliveryPrice
    }

    fun totalPrice(): Int {
        return calculateProductsPrice() + calculateDeliveryPrice()
    }
}