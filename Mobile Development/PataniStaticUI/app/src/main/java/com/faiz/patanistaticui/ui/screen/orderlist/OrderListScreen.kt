package com.faiz.patanistaticui.ui.screen.orderlist

import ViewModelFactory
import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.data.UserPref
import com.faiz.patanistaticui.ui.components.BottomBar
import com.faiz.patanistaticui.ui.screen.category.CategoryViewModel
import com.google.android.material.tabs.TabLayout.TabView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderListScreen(
    navController: NavHostController,
    orderListViewModel: OrderListViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier
) {
    val pref = UserPref(context)

    // get buyer
    val buyerId = pref.getUserId.collectAsState(0)
    orderListViewModel.getBuyerById(buyerId.value)
    val buyer = orderListViewModel.buyer.collectAsState()

    // get orders
    orderListViewModel.getOrders()
    val orders = orderListViewModel.order.collectAsState()

    // get products
    orderListViewModel.getProducts()
    val products = orderListViewModel.products.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pesanan")
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
                .fillMaxSize()
        ) {
            var tabIndex by remember { mutableStateOf(0) }
            val tabs = listOf("Penawaran", "Diproses", "Selesai")

            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = {
                        Text(
                            title,
                            style = TextStyle(
                                color = Color.Black,
                            )
                        ) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }

            when (tabIndex) {
                0 -> TabView(
                    type = "Offer",
                    buyerId = buyerId.value,
                    orders = orders,
                    products = products
                )
                1 -> TabView(
                    type = "Process",
                    buyerId = buyerId.value,
                    orders = orders,
                    products = products
                )
                2 -> TabView(
                    type = "Done",
                    buyerId = buyerId.value,
                    orders = orders,
                    products = products
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 720)
fun OrderListScreenPreview() {
    OrderListScreen(rememberNavController())
}