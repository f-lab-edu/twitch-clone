package com.example.product.adapter.out.repository

import com.example.exception.CustomException
import com.example.exception.ErrorCode
import com.example.product.adpter.out.ProductRepositoryImpl
import com.example.product.config.TestBeanConfig
import com.example.product.domain.model.Product
import com.example.product.domain.model.ProductEntity
import com.example.user.domain.model.StreamerUser
import com.example.user.util.TestUserGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import


@DataJpaTest
@Import(value = [TestBeanConfig::class])
class ProductRepositoryImplTest {

    @Autowired
    private lateinit var productRepositoryImpl: ProductRepositoryImpl

    private lateinit var streamerUser: StreamerUser
    private lateinit var product: Product

    @BeforeEach
    fun setup() {
        streamerUser = TestUserGenerator.streamUser()
        product = ProductEntity(123456, "Product", 10000, 100, streamerUser.id)
    }

    @DisplayName("상품 정보를 저장한다.")
    @Test
    fun `save product`() {
        // when
        val productEntity = productRepositoryImpl.save(product)

        // then
        assertThat(productEntity.productNo).isEqualTo(product.productNo)
    }

    @DisplayName("상품 번호를 통하여 검색한다.")
    @Test
    fun `select product`() {
        // given
        productRepositoryImpl.save(product)

        // when
        val findProduct = productRepositoryImpl.findByProductNo(product.productNo)

        // then
        assertThat(findProduct.productNo).isEqualTo(product.productNo)
    }

    @DisplayName("상품을 존재하지 않는 상품 번호를 통하여 검색 하면 Custom Exception을 발생 시킨다.")
    @Test
    fun `select product if not exists throw exception`() {
        // when
        val exception = assertThrows<CustomException> {
            productRepositoryImpl.findByProductNo(0)
        }

        // then
        assertThat(exception.errorCode).isEqualTo(ErrorCode.PRODUCT_NOT_FOUND)
    }
}
