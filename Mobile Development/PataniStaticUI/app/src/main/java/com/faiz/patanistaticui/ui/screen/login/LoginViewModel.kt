package com.faiz.patanistaticui.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiz.patanistaticui.data.Repository
import com.faiz.patanistaticui.data.response.BuyerUser
import com.faiz.patanistaticui.data.response.LoginResponse
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.data.response.ProductsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel (
    private val repository: Repository
) : ViewModel() {
//    suspend fun loginUser(email: String, password: String) = repository.loginUser(email, password)

    private  val _loginReponse: MutableStateFlow<String> = MutableStateFlow("")
    val loginReponse : StateFlow<String> = _loginReponse

    fun loginUser(email: String, password: String){
        viewModelScope.launch {
            val call: Call<LoginResponse> = repository.loginUser(email, password)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData: String? = response.body()?.status.toString()
                        if(responseData != null){
                            _loginReponse.value = responseData
                        }
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _loginReponse.value = "Failed, Check your creds"
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }
}