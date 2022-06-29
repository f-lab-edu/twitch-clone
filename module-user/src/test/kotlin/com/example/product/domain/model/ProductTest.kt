package com.example.product.domain.model

import com.example.exception.CustomException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

@DisplayName("[상품]")
class ProductTest {
    private lateinit var product: Product

    @BeforeEach
    fun setup() {
        product = MockProduct(123456, "상품", 15000, 20, UUID.randomUUID())
    }

    @Test
    @DisplayName("상품의 재고를 차감한다.")
    fun `minus product stock`() {
        // when
        product.minusStock(10)

        // then
        assertThat(product.stock).isEqualTo(10)
    }

    @Test
    @DisplayName("상품의 재고가 부족하면 CustomException을 발생 시킨다.")
    fun `minus product stock if not enough throw CustomException`() {
        // when
        val exception = assertThrows<CustomException> {
            product.minusStock(100)
        }

        // then
        assertThat(exception.message).isEqualTo("상품의 재고 부족")
    }
}