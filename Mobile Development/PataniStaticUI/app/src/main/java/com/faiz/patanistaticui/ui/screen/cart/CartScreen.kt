package com.faiz.patanistaticui.ui.screen.cart

import ViewModelFactory
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.data.UserPref
import com.faiz.patanistaticui.model.dummyProduct
import com.faiz.patanistaticui.ui.components.BottomBar
import com.faiz.patanistaticui.ui.components.CategoryProductItem
import com.faiz.patanistaticui.ui.components.SearchBar
import com.faiz.patanistaticui.ui.components.SellingProductItem
import com.faiz.patanistaticui.ui.screen.category.CategoryViewModel
import com.faiz.patanistaticui.ui.screen.orderlist.TabView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier,
) {
    val pref = UserPref(context)
    var total_price by rememberSaveable { mutableStateOf(0) }

    // get buyer
    val buyerId = pref.getUserId.collectAsState(0)
    cartViewModel.getBuyerById(buyerId.value)
    val buyer = cartViewModel.buyer.collectAsState()

    // get orders
    cartViewModel.getOrders()
    val orders = cartViewModel.order.collectAsState()

    // get products
    cartViewModel.getProducts()
    val products = cartViewModel.products.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Keranjang Belanja")
                },
//                navigationIcon = {
//                    IconButton(onClick = {  }) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
    ) {innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
//            Box (
//                modifier = Modifier.padding(horizontal = 20.dp)
//            ) {
//                SearchBar(
//                    query = "",
//                    onQueryChange = {},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                )
//            }

            Spacer(modifier = Modifier.height(10.dp))

//            LazyRow(
//                horizontalArrangement = Arrangement.spacedBy(20.dp),
//                contentPadding = PaddingValues(horizontal = 20.dp),
//            ) {
//                items(count = orders.value.size) {
//                    CategoryProductItem(
//                        product = products.value[it],
//                    )
//                }
//            }
//

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier = Modifier
                    .weight(4f)
            ) {
                items(count = orders.value.size) {orderIndex ->
                    if (orders.value[orderIndex].status.toString() == "Cart") {
                        if (orders.value[orderIndex].buyerId == buyerId.value) {
                            products.value.map {product ->
                                if (product.id == orders.value[orderIndex].id){
                                    total_price = orders.value[orderIndex].totalPrice!!
                                    SellingProductItem(
                                        product = product,
                                        qty = orders.value[orderIndex].qty!!,
                                        total_price = orders.value[orderIndex].totalPrice.toString(),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            if (total_price == 0) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Item Kosong",
                        color = Color.Gray,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            Box (
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        "Total : " + "Rp $total_price",
                        color = Color.Black
                    )
                    Button(
                        onClick = {
//                            cartViewModel.updateOrder()

                                  navController.navigate("orderList") {
                                      popUpTo(navController.graph.id) {
                                          inclusive = true
                                      }
                                  }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2EA071)
                        ),
                    ) {
                        Text(
                            "Checkout",
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun CartScreenPreview() {
    CartScreen(navController = rememberNavController())
}