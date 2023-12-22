package com.faiz.patanistaticui.ui.screen.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiz.patanistaticui.data.Repository
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.data.response.ProductsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel (
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
}