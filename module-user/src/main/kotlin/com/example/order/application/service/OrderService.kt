package com.example.order.application.service

import com.example.order.domain.model.Order
import com.example.product.domain.model.Product


interface OrderService {
    fun productOrder(purchaseProductsMap: Map<Product, Int>): Order
}