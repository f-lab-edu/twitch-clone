package com.example.order.adapter.`in`

import com.example.order.application.service.OrderService
import com.example.order.domain.model.Order
import com.example.product.application.port.out.ProductRepository
import com.example.product.domain.model.Product
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class OrderServiceImpl(
    private val productRepository: ProductRepository
) : OrderService {

    @Transactional
    override fun productOrder(purchaseProductsMap: Map<Product, Int>): Order {
        minusProductStock(purchaseProductsMap)
        return Order(purchaseProductsMap)
    }

    private fun minusProductStock(purchaseProductsMap: Map<Product, Int>) {
        for (productEntry in purchaseProductsMap) {
            val findProduct = productRepository.findByProductNo(productEntry.key.productNo)
            findProduct.minusStock(productEntry.value)
        }
    }
}