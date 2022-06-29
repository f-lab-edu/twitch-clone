package com.example.order.domain.model

import com.example.product.domain.model.MockProduct
import com.example.product.domain.model.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

internal class OrderTest {

    private val deliveryFreeOrderProducts : MutableMap<Product, Int> = mutableMapOf()
    private val deliveryChargeOrderProducts : MutableMap<Product, Int> = mutableMapOf()
    private val streamerUserId = UUID.randomUUID()

    @BeforeEach
    fun setup() {
        val product1 = MockProduct(1, "상품1", 1000, 100, streamerUserId)
        val product2 = MockProduct(2, "상품2", 10000, 10, streamerUserId)

        deliveryFreeOrderProducts[product1] = 10
        deliveryFreeOrderProducts[product2] = 10
        deliveryChargeOrderProducts[product1] = 49
    }

    @Test
    @DisplayName("주문의 상품 금액을 계산한다.")
    fun `calculate products price`() {
        // given
        val order = Order(deliveryFreeOrderProducts)

        // when
        val productsPrice = order.productsPrice

        // then
        assertThat(productsPrice).isEqualTo(110000)
    }

    @Test
    @DisplayName("주문의 배송 금액을 계산한다. - 상품 금액이 5만원 이상일 경우 배송비는 0원 이어야 한다.")
    fun `calculate delivery price if products price more than 50000`() {
        // given
        val order = Order(deliveryFreeOrderProducts)

        // when
        val deliveryPrice = order.deliveryPrice

        // then
        assertThat(deliveryPrice).isEqualTo(0)
    }

    @Test
    @DisplayName("주문의 배송 금액을 계산한다. - 상품 금액이 5만원 미만일 경우 배송비가 부과된다.")
    fun `calculate delivery price if products price less than 50000`() {
        // given
        val order = Order(deliveryChargeOrderProducts)

        // when
        val deliveryPrice = order.deliveryPrice

        // then
        assertThat(deliveryPrice).isEqualTo(2500)
    }

    @Test
    @DisplayName("주문의 총 결제 금액을 계산한다. - 상품 금액이 5만원 이상일 경우 상품 금액과 총 결제 금액이 동일하다.")
    fun `calculate total price if products price more than 50000`() {
        // given
        val order = Order(deliveryFreeOrderProducts)

        // when
        val totalPrice = order.totalPrice()

        // then
        assertThat(totalPrice).isEqualTo(110000)
    }

    @Test
    @DisplayName("주문의 총 결제 금액을 계산한다. - 상품 금액이 5만원 미만 경우 배송비가 총 결제 금액에 포함되어야 한다.")
    fun `calculate total price if products price less than 50000`() {
        // given
        val order = Order(deliveryChargeOrderProducts)

        // when
        val totalPrice = order.totalPrice()

        // then
        assertThat(totalPrice).isEqualTo(51500)
    }
}