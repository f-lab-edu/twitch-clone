package com.example.product.domain.model

import java.util.*

class MockProduct(
    override val productNo: Int,
    override val productName: String,
    override val price: Int,
    override var stock: Int,
    override val streamerUserId: UUID
) : Product