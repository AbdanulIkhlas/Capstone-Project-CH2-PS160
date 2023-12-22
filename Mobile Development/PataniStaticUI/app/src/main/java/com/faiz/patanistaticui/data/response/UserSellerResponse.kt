package com.faiz.patanistaticui.data.response

import com.google.gson.annotations.SerializedName

data class UserSellerResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("sellers")
	val sellers: List<SellersItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class SellersItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("kordinat")
	val kordinat: String? = null,

	@field:SerializedName("no_hp")
	val noHp: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("no_rekening")
	val noRekening: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
