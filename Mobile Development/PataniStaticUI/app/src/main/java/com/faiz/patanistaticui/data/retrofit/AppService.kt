package com.faiz.patanistaticui.data.retrofit

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
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.data.response.ProductsResponse
import com.faiz.patanistaticui.data.response.RegisterBuyerResponse
import com.faiz.patanistaticui.data.response.SellerByIdResponse
import com.faiz.patanistaticui.data.response.UpdateOrderResponse
import com.faiz.patanistaticui.data.response.UpdatedOrder
import com.faiz.patanistaticui.data.response.UserSellerResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @POST("registerBuyer")
    suspend fun registerUser(
        @Body dataSignin: DataSignin
    ): RegisterBuyerResponse

    @POST("login")
    fun loginUser(
        @Body dataLogin: DataLogin
    ): Call<LoginResponse>

    @GET("products")
    fun getProducts(
    ): Call<ProductsResponse>

    @GET("product/{productId}")
    fun getProductById(
        @Path("productId") productId: String
    ): Call<ProductByIdResponse>

    @GET("buyers")
    fun getBuyers(
    ): Call<BuyersResponse>

    @GET("sellers")
    fun getSellers(
    ): Call<UserSellerResponse>

    @GET("buyer/{buyerId}")
    fun getBuyerById(
        @Path("buyerId") buyerId: Int
    ): Call<BuyerByIdResponse>

    @GET("seller/{sellerId}")
    fun getSellerById(
        @Path("sellerId") sellerId: Int
    ): Call<SellerByIdResponse>

    @POST("addOrder")
    fun addOrder(
        @Body dataOrder: DataOrder
    ): Call<AddOrderResponse>

    @GET("orders")
    fun getOrders(): Call<OrdersResponse>

    @PUT("update/order/{orderId}")
    fun updateOrder(
        @Path("orderId") orderId: Int,
        @Body dataOrder: DataOrder
    ): Call<UpdateOrderResponse>

    @POST("inputPrediction")
    fun postPredict(
        @Body dataPrediction: DataPrediction
    ): Call<PredictResponse>

//    @FormUrlEncoded
//    @POST("registerBuyer")
//    suspend fun registerUser(
//        @Field("email") email: String,
//        @Field("password") password: String,
//        @Field("nama") name: String,
//        @Field("alamat") alamat: String,
//        @Field("kordinat") koordinat: String,
//        @Field("no_hp") no_hp: String
//    ): RegisterBuyerResponse

//    @FormUrlEncoded
//    @POST("login")
//    suspend fun loginUser(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): LoginResponse
//
//    @GET("stories")
//    suspend fun getStories(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int = 1,
//        @Query("size") size: Int = 20
//    ): StoryResponse
//

//
//    @Multipart
//    @POST("stories")
//    suspend fun uploadImage(
//        @Header("Authorization") token: String,
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//        @Part("lat") lat: RequestBody? = null,
//        @Part("lon") lon: RequestBody? = null
//    ): FileUploadResponse
}