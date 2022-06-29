package com.example.product.adpter.out

import com.example.product.domain.model.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import javax.persistence.LockModeType

internal interface ProductDao : JpaRepository<ProductEntity, Int> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    fun findByProductNo(productNo: Int): ProductEntity?
}
