package com.faiz.patanistaticui.data.response

import com.google.gson.annotations.SerializedName

data class ProductByIdResponse(

	@field:SerializedName("product")
	val product: Product? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Product(

	@field:SerializedName("seller")
	val seller: Seller? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("sellerId")
	val sellerId: Int? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("product_durability")
	val productDurability: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("categoryId")
	val categoryId: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

