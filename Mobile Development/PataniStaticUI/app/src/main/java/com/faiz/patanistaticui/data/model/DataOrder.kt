package com.faiz.patanistaticui.data.model

data class DataOrder (
    var status: String,
    var qty: Int? = null,
    var total_price: String? = null,
    var sellerId: Int? = null,
    var buyerId: Int? = null,
    var productId: Int? = null
)