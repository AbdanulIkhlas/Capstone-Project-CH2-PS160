package com.faiz.patanistaticui.ui.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiz.patanistaticui.data.Repository
import com.faiz.patanistaticui.data.response.Buyer
import com.faiz.patanistaticui.data.response.BuyerByIdResponse
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.data.response.ProductsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel (
    private val repository: Repository
) : ViewModel() {
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
}