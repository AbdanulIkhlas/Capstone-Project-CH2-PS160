package com.faiz.patanistaticui.ui.screen.detailProduct

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faiz.patanistaticui.data.Repository
import com.faiz.patanistaticui.data.response.AddOrderResponse
import com.faiz.patanistaticui.data.response.BuyersItem
import com.faiz.patanistaticui.data.response.BuyersResponse
import com.faiz.patanistaticui.data.response.NewOrder
import com.faiz.patanistaticui.data.response.PredResult
import com.faiz.patanistaticui.data.response.PredictResponse
import com.faiz.patanistaticui.data.response.Product
import com.faiz.patanistaticui.data.response.ProductByIdResponse
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.data.response.ProductsResponse
import com.faiz.patanistaticui.data.response.Seller
import com.faiz.patanistaticui.data.response.SellerByIdResponse
import com.faiz.patanistaticui.data.response.SellersItem
import com.faiz.patanistaticui.data.response.UserSellerResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProductViewModel (
    private val repository: Repository
) : ViewModel() {
    private  val _prediction: MutableStateFlow<PredResult> = MutableStateFlow(PredResult())
    val prediction : StateFlow<PredResult> = _prediction

    fun getPredict(startDate: String, endDate: String, komoditi: String){
        viewModelScope.launch {
            val call: Call<PredictResponse> = repository.postPredict(startDate, endDate, komoditi)

            call.enqueue(object : Callback<PredictResponse> {
                override fun onResponse(
                    call: Call<PredictResponse>,
                    response: Response<PredictResponse>
                ) {

                    if(response.isSuccessful){
                        val responseData: PredResult? = response.body()?.predResult
                        Log.i("responpredict", responseData.toString())
                        if(responseData != null){
                            _prediction.value = responseData

                        }
                    }
                }
                override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }

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

    private  val _product: MutableStateFlow<Product> = MutableStateFlow(Product())
    val product : StateFlow<Product> = _product

    fun getProductById(productId: String){
        viewModelScope.launch {
            val call: Call<ProductByIdResponse> = repository.getProductById(productId)

            call.enqueue(object : Callback<ProductByIdResponse> {
                override fun onResponse(
                    call: Call<ProductByIdResponse>,
                    response: Response<ProductByIdResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData: Product? = response.body()?.product
                        if(responseData != null){
                            _product.value = responseData

                        }
                    }
                }
                override fun onFailure(call: Call<ProductByIdResponse>, t: Throwable) {
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }

    private  val _addOrder: MutableStateFlow<NewOrder> = MutableStateFlow(NewOrder())
    val addOrder : StateFlow<NewOrder> = _addOrder

    fun addOrder(
        status: String,
        qty: Int? = null,
        total_price: String? = null,
        sellerId: Int? = null,
        buyerId: Int? = null,
        productId: Int? = null
    ){
        viewModelScope.launch {
            val call: Call<AddOrderResponse> = repository.addOrder(
                status, qty, total_price, sellerId, buyerId, productId
            )

            call.enqueue(object : Callback<AddOrderResponse> {
                override fun onResponse(
                    call: Call<AddOrderResponse>,
                    response: Response<AddOrderResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData: NewOrder? = response.body()?.newOrder
                        if(responseData != null){
                            _addOrder.value = responseData
                        }
                    }
                }
                override fun onFailure(call: Call<AddOrderResponse>, t: Throwable) {
                    Log.d("Failed Retrieve", "Network Error")
                }
            })
        }
    }

    private  val _seller: MutableStateFlow<Seller> = MutableStateFlow(Seller())
    val seller : StateFlow<Seller> = _seller

    fun getSellerById(sellerId: Int){
        viewModelScope.launch {
            val call: Call<SellerByIdResponse> = repository.getSellerById(sellerId)

            call.enqueue(object : Callback<SellerByIdResponse> {
                override fun onResponse(
                    call: Call<SellerByIdResponse>,
                    response: Response<SellerByIdResponse>
                ) {
                    if(response.isSuccessful){
                        val responseData: Seller? = response.body()?.seller
                        if(responseData != null){
                            _seller.value = responseData

                        }
                    }
                }
                override fun onFailure(call: Call<SellerByIdResponse>, t: Throwable) {
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

    private  val _sellers: MutableStateFlow<List<SellersItem>> = MutableStateFlow(listOf())
    val sellers : StateFlow<List<SellersItem>> = _sellers

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
                            _sellers.value = responseData
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