package com.example.product.application.service

import com.example.exception.CustomException
import com.example.product.domain.model.MockProduct
import com.example.product.domain.model.Product
import com.example.product.util.MockProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class FindProductServiceTest {
    private lateinit var mockProductRepository: MockProductRepository
    private lateinit var findProductService: FindProductService
    private lateinit var product: Product
    private val productNo = 123456

    @BeforeEach
    fun setup() {
        mockProductRepository = MockProductRepository()
        findProductService = FindProductService(mockProductRepository)
        product = MockProduct(
            productNo = productNo,
            productName = "PRODUCT",
            price = 15000,
            stock = 10,
            streamerUserId = UUID.randomUUID()
        )
    }

    @Test
    @DisplayName("상품 번호를 통하여 상품을 가져온다.")
    fun `find product by product no`() {
        // given
        mockProductRepository.save(product)

        // when
        val findProduct = mockProductRepository.findByProductNo(productNo)

        // then
        assertThat(findProduct.productNo).isEqualTo(productNo)
    }

    @Test
    @DisplayName("상품 번호를 통하여 상품을 가져온다 - 존재하지 않을 경우 Exception을 발생시킨다.")
    fun `find product by product no if not exists throw exception`() {
        // given
        mockProductRepository.save(product)

        // when
        val exception = assertThrows<CustomException> {
            mockProductRepository.findByProductNo(111111)
        }

        // then
        assertThat(exception.message).isEqualTo("존재하지 않는 상품")
    }

}