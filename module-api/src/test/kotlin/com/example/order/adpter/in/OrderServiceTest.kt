package com.example.order.adpter.`in`

import com.example.exception.CustomException
import com.example.order.application.service.OrderService
import com.example.order.config.TestBeanConfig
import com.example.product.application.port.out.ProductRepository
import com.example.product.domain.model.Product
import com.example.product.domain.model.ProductEntity
import com.example.user.domain.model.StreamerUser
import com.example.user.util.TestUserGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@DataJpaTest
@Import(value = [TestBeanConfig::class])
internal class OrderServiceTest {

    @Autowired
    @Qualifier("testProductRepository")
    private lateinit var productRepository: ProductRepository

    @Autowired
    @Qualifier("testOrderService")
    private lateinit var orderService: OrderService

    private lateinit var streamerUser: StreamerUser
    private lateinit var product: Product
    private var productNo: Int = 0

    private val orderProducts: MutableMap<Product, Int> = mutableMapOf()
    private val overStockedOrderProducts: MutableMap<Product, Int> = mutableMapOf()
    private val multiThreadTestOrderProducts: MutableMap<Product, Int> = mutableMapOf()

    @BeforeEach
    fun setup() {
        streamerUser = TestUserGenerator.streamUser()
        product = ProductEntity(123456, "Product", 10000, 100, streamerUser.id)
        productRepository.save(product);
        productNo = product.productNo;

        orderProducts[product] = 100
        overStockedOrderProducts[product] = 101
        multiThreadTestOrderProducts[product] = 2
    }

    @DisplayName("상품정보와 구매할 갯수를 통하여 주문을 생성 한다 - 주문량이 재고보다 적으면 정상 주문 진행")
    @Test
    fun `create order by products and product amount`() {
        // when
        val order = orderService.productOrder(orderProducts)

        // then
        val changeProduct = productRepository.findByProductNo(productNo)

        assertAll(
            { assertThat(order.productsPrice).isEqualTo(1000000) },
            { assertThat(order.deliveryPrice).isEqualTo(0) },
            { assertThat(order.totalPrice()).isEqualTo(1000000) },
            { assertThat(changeProduct.stock).isEqualTo(0) },
        )
    }

    @DisplayName("상품정보와 구매할 갯수를 통하여 주문을 생성 한다 - 주문량이 재고보다 많으면 CustomException 발생")
    @Test
    fun `create order by products and if stock less than order quantity throw CustomException`() {
        // when
        val exception = assertThrows<CustomException> {
            orderService.productOrder(overStockedOrderProducts)
        }

        // then
        assertThat(exception.message).isEqualTo("상품의 재고 부족")
    }

    @DisplayName("상품정보와 구매할 갯수를 통하여 주문을 생성 한다 - 주문량이 재고보다 많으면 CustomException 발생(멀티 스레드)")
    @Test
    fun `create order by products and if stock less than order quantity throw CustomException(Multi Thread)`() {
        // given
//        orderService.productOrder(multiThreadTestOrderProducts)
        val threadCount = 51
        val executorService: ExecutorService = Executors.newFixedThreadPool(threadCount)
        val run = Runnable {
            for (i in 0..threadCount) {
                orderService.productOrder(multiThreadTestOrderProducts)
                Thread.sleep(100)
            }
        }

        // when
        val exception = assertThrows<ExecutionException> {
            executorService.submit(run).get()
        }

        // then
        assertThat(exception.message).contains("상품의 재고 부족")
    }
}
