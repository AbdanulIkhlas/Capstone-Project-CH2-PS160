package com.faiz.patanistaticui.data

import android.util.Log
import com.faiz.patanistaticui.data.model.DataLogin
import com.faiz.patanistaticui.data.model.DataOrder
import com.faiz.patanistaticui.data.model.DataPrediction
import com.faiz.patanistaticui.data.model.DataSignin
import com.faiz.patanistaticui.data.response.AddOrderResponse
import com.faiz.patanistaticui.data.response.BuyerByIdResponse
import com.faiz.patanistaticui.data.response.BuyersResponse
import com.faiz.patanistaticui.data.response.LoginResponse
import com.faiz.patanistaticui.data.response.OrdersResponse
import com.faiz.patanistaticui.data.response.PredictResponse
import com.faiz.patanistaticui.data.response.ProductByIdResponse
import com.faiz.patanistaticui.data.response.ProductsResponse
import com.faiz.patanistaticui.data.response.RegisterBuyerResponse
import com.faiz.patanistaticui.data.response.SellerByIdResponse
import com.faiz.patanistaticui.data.response.UpdateOrderResponse
import com.faiz.patanistaticui.data.response.UserSellerResponse
import com.faiz.patanistaticui.data.retrofit.ApiService
import retrofit2.Call
import retrofit2.HttpException
import java.io.File

class Repository private constructor(
    private val apiService: ApiService
) {
    suspend fun registerBuyer(
        email: String,
        password: String,
        name: String,
        alamat: String,
        koordinat: String,
        no_hp: String,
        image: File? = null
    ) : RegisterBuyerResponse {
        val dataSignin = DataSignin(email, password, name, alamat, koordinat, no_hp, image)

        return apiService.registerUser(dataSignin)

    }

    fun loginUser(email: String, password: String) : Call<LoginResponse> {
        val dataLogin = DataLogin(email, password)

        return apiService.loginUser(dataLogin)
    }

    fun getProducts() : Call<ProductsResponse> {
        val response = apiService.getProducts()
        return response
    }

    fun getProductById(productId: String) : Call<ProductByIdResponse> {
        val response = apiService.getProductById(productId)
        return response
    }

    fun getBuyers() : Call<BuyersResponse> {
        val response = apiService.getBuyers()
        return response
    }

    fun getSellers() : Call<UserSellerResponse> {
        val response = apiService.getSellers()
        return response
    }

    fun getBuyerById(buyerId: Int) : Call<BuyerByIdResponse> {
        val response = apiService.getBuyerById(buyerId)
        return response
    }

    fun getSellerById(sellerId: Int) : Call<SellerByIdResponse> {
        val response = apiService.getSellerById(sellerId)
        return response
    }

    fun getOrders() : Call<OrdersResponse> {
        val response = apiService.getOrders()
        return response
    }

    fun updateOrder(orderId: Int, status: String) : Call<UpdateOrderResponse> {
        val dataOrder = DataOrder(status = status)
        val response = apiService.updateOrder(orderId, dataOrder)
        return response
    }

    fun postPredict(startDate: String, endDate: String, komoditi: String): Call<PredictResponse> {
        val dataPrediction = DataPrediction(startDate, endDate, komoditi)
        val response = apiService.postPredict(dataPrediction)
        return response
    }

    fun addOrder(
        status: String,
        qty: Int? = null,
        total_price: String? = null,
        sellerId: Int? = null,
        buyerId: Int? = null,
        productId: Int? = null
    ) : Call<AddOrderResponse> {
        val dataOrder = DataOrder(
            status,
            qty,
            total_price,
            sellerId,
            buyerId,
            productId
        )
        val response = apiService.addOrder(dataOrder)
        return response
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            apiService: ApiService
        ): Repository =
            instance ?: synchronized(this) {
                Repository(apiService).apply {
                    instance = this
                }
            }
    }
}