package com.faiz.patanimobile.model

import androidx.annotation.DrawableRes
import com.faiz.patanimobile.R

data class Product (
    var name: String,
    @DrawableRes val image: Int
)

val dummyProduct = listOf(
    "Produk 1" to R.drawable.sayur,
    "Produk 2" to R.drawable.sayur,
    "Produk 3" to R.drawable.sayur,
    "Produk 4" to R.drawable.sayur,
    "Produk 5" to R.drawable.sayur,
    "Produk 6" to R.drawable.sayur,
).map { Product(it.first, it.second) }