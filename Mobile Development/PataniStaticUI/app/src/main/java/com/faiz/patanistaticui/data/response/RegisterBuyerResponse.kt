package com.faiz.patanistaticui.data.response

import com.google.gson.annotations.SerializedName

data class RegisterBuyerResponse(

    @field:SerializedName("buyerUser")
    val buyerUser: BuyerUser? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)