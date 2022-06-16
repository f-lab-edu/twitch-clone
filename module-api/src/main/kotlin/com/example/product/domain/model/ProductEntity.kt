package com.example.product.domain.model

import com.example.user.domain.model.StreamerUserEntity
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "product")
internal class ProductEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "INT(11)")
    override val productNo: Int = 0,
    @Column
    override val productName: String = "",
    @Column
    override val price: Int = 0,
    @Column
    override var stock: Int = 0,

    override val streamerUserId: UUID = UUID.randomUUID(),

    ) : Product {
    companion object {
        fun from(product: Product): ProductEntity = with(product) {
            ProductEntity(
                productNo = productNo,
                productName = productName,
                price = price,
                stock = stock,
                streamerUserId = streamerUserId
            )
        }
    }
}
