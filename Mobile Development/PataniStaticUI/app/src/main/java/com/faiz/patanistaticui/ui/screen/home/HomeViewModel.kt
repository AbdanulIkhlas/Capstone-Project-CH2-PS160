package com.faiz.patanistaticui.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiz.patanistaticui.data.Repository
import com.faiz.patanistaticui.data.response.Buyer
import com.faiz.patanistaticui.data.response.BuyerByIdResponse
import com.faiz.patanistaticui.data.response.BuyersItem
import com.faiz.patanistaticui.data.response.BuyersResponse
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.data.response.ProductsResponse
import com.faiz.patanistaticui.data.response.SellersItem
import com.faiz.patanistaticui.data.response.UserSellerResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel (
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

    private  val _buyer: MutableStateFlow<List<BuyersItem>> = MutableStateFlow(listOf())
    val buyer : StateFlow<List<BuyersItem>> = _buyer

    fun getBuyers(){
        viewModelScope.launch {
            val call: Call<BuyersResponse> = repository.getBuyers()

            call.enqueue(object : Callback<BuyersResponse> {
                override fun onResponse(
                    call: Call<BuyersResponse>,
                    response: Response<BuyersResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData: List<BuyersItem>? = response.body()?.buyers
                        if(responseData != null){
                            _buyer.value = responseData
                        }
                    }
                }
                override fun onFailure(call: Call<BuyersResponse>, t: Throwable) {
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }

    private  val _seller: MutableStateFlow<List<SellersItem>> = MutableStateFlow(listOf())
    val seller : StateFlow<List<SellersItem>> = _seller

    fun getSellers(){
        viewModelScope.launch {
            val call: Call<UserSellerResponse> = repository.getSellers()

            call.enqueue(object : Callback<UserSellerResponse> {
                override fun onResponse(
                    call: Call<UserSellerResponse>,
                    response: Response<UserSellerResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData: List<SellersItem>? = response.body()?.sellers
                        if(responseData != null){
                            _seller.value = responseData
                        }
                    }
                }
                override fun onFailure(call: Call<UserSellerResponse>, t: Throwable) {
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }
}