package com.faiz.patanistaticui.data.response

import com.google.gson.annotations.SerializedName

data class AddOrderResponse(

	@field:SerializedName("newOrder")
	val newOrder: NewOrder? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class NewOrder(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("sellerId")
	val sellerId: Int? = null,

	@field:SerializedName("total_price")
	val totalPrice: String? = null,

	@field:SerializedName("productId")
	val productId: Int? = null,

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("buyerId")
	val buyerId: Int? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
