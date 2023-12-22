package com.faiz.patanistaticui.model

import androidx.annotation.DrawableRes
import com.faiz.patanistaticui.R

data class Product (
    var name: String,
    @DrawableRes val image: Int
)

val dummyProduct = listOf(
    "Produk 1" to R.drawable.kafka,
    "Produk 2" to R.drawable.kafka,
    "Produk 3" to R.drawable.kafka,
    "Produk 4" to R.drawable.kafka,
    "Produk 5" to R.drawable.kafka,
    "Produk 6" to R.drawable.kafka,
).map { Product(it.first, it.second) }