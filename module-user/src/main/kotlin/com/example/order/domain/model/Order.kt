package com.example.order.domain.model

import com.example.product.domain.model.Product

class Order(
    override val orderProduct : Map<Product, Int>,
) : CalculateOrder {
    val productsPrice: Int = calculateProductsPrice()
    val deliveryPrice: Int = calculateDeliveryPrice()

    override val deliveryFreeProductPrice: Int
        get() = 50000
    override val defaultDeliveryPrice: Int
        get() = 2500
}