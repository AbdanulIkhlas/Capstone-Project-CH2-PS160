package com.faiz.patanistaticui.ui.screen.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiz.patanistaticui.data.Repository
import com.faiz.patanistaticui.data.response.Buyer
import com.faiz.patanistaticui.data.response.BuyerByIdResponse
import com.faiz.patanistaticui.data.response.OrderItem
import com.faiz.patanistaticui.data.response.OrdersResponse
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.data.response.ProductsResponse
import com.faiz.patanistaticui.data.response.UpdateOrderResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel (
    private val repository: Repository
) : ViewModel() {
    private  val _products: MutableStateFlow<List<ProductsItem>> = MutableStateFlow(listOf())
    val products : StateFlow<List<ProductsItem>> = _products

    fun getProducts(){
        viewModelScope.launch {
            val call: Call<ProductsResponse> = repository.getProducts()

            call.enqueue(object : Callback<ProductsResponse> {
                override fun onResponse(
                    call: Call<ProductsResponse>,
                    response: Response<ProductsResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData: List<ProductsItem>? = response.body()?.products
                        if(responseData != null){
                            _products.value = responseData
                        }

                    }
                }
                override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }

    private val _buyer: MutableStateFlow<Buyer> = MutableStateFlow(Buyer())
    val buyer: StateFlow<Buyer> = _buyer

    fun getBuyerById(buyerId: Int) {
        viewModelScope.launch {
            val call: Call<BuyerByIdResponse> = repository.getBuyerById(buyerId)

            call.enqueue(object : Callback<BuyerByIdResponse> {
                override fun onResponse(
                    call: Call<BuyerByIdResponse>,
                    response: Response<BuyerByIdResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseData: Buyer? = response.body()?.buyer
                        if (responseData != null) {
                            _buyer.value = responseData
                        }
                    }
                }

                override fun onFailure(call: Call<BuyerByIdResponse>, t: Throwable) {
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }

    private  val _order: MutableStateFlow<List<OrderItem>> = MutableStateFlow(listOf())
    val order : StateFlow<List<OrderItem>> = _order

    fun getOrders(){
        viewModelScope.launch {
            val call: Call<OrdersResponse> = repository.getOrders()

            call.enqueue(object : Callback<OrdersResponse> {
                override fun onResponse(
                    call: Call<OrdersResponse>,
                    response: Response<OrdersResponse>
                ) {

                    if(response.isSuccessful){
                        val responseData: List<OrderItem>? = response.body()?.order
                        if(responseData != null){
                            _order.value = responseData
                        }

                    }
                }
                override fun onFailure(call: Call<OrdersResponse>, t: Throwable) {
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }

    private  val _updateMessage: MutableStateFlow<String> = MutableStateFlow("")
    val updateMessage : StateFlow<String> = _updateMessage

    fun updateOrder(orderId: Int, status: String){
        viewModelScope.launch {
            val call: Call<UpdateOrderResponse> = repository.updateOrder(orderId, status)

            call.enqueue(object : Callback<UpdateOrderResponse> {
                override fun onResponse(
                    call: Call<UpdateOrderResponse>,
                    response: Response<UpdateOrderResponse>
                ) {
                    if(response.isSuccessful){
                        _updateMessage.value = response.message()
                        Log.i("Success Update Order", response.message())
                    }
                }
                override fun onFailure(call: Call<UpdateOrderResponse>, t: Throwable) {
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }
}