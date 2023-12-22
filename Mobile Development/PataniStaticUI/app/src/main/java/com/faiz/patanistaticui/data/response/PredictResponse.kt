package com.faiz.patanistaticui.data.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("result")
	val predResult: PredResult? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class PredResult(

	@field:SerializedName("2023-12-27T00:00:00.000Z")
	val jsonMember20231227T000000000Z: Any? = null,

	@field:SerializedName("2023-12-23T00:00:00.000Z")
	val jsonMember20231223T000000000Z: Any? = null,

	@field:SerializedName("2023-12-24T00:00:00.000Z")
	val jsonMember20231224T000000000Z: Any? = null,

	@field:SerializedName("2023-12-28T00:00:00.000Z")
	val jsonMember20231228T000000000Z: Any? = null,

	@field:SerializedName("2023-12-25T00:00:00.000Z")
	val jsonMember20231225T000000000Z: Any? = null,

	@field:SerializedName("2023-12-22T00:00:00.000Z")
	val jsonMember20231222T000000000Z: Any? = null,

	@field:SerializedName("2023-12-21T00:00:00.000Z")
	val jsonMember20231221T000000000Z: Any? = null,

	@field:SerializedName("2023-12-26T00:00:00.000Z")
	val jsonMember20231226T000000000Z: Any? = null
)
