package com.faiz.patanistaticui.ui.screen.home

import ViewModelFactory
import android.content.Context
import android.text.style.AlignmentSpan
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.faiz.patanistaticui.R
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.data.UserPref
import com.faiz.patanistaticui.data.response.BuyersItem
import com.faiz.patanistaticui.model.Advertisement
import com.faiz.patanistaticui.model.dummyAdvertisement
import com.faiz.patanistaticui.model.dummyCategory
import com.faiz.patanistaticui.model.dummyProduct
import com.faiz.patanistaticui.model.dummyShop
import com.faiz.patanistaticui.ui.components.AdvertiseItem
import com.faiz.patanistaticui.ui.components.BottomBar
import com.faiz.patanistaticui.ui.components.CategoryItem
import com.faiz.patanistaticui.ui.components.CategoryProductItem
import com.faiz.patanistaticui.ui.components.SearchBar
import com.faiz.patanistaticui.ui.components.ShopItem
import com.faiz.patanistaticui.ui.screen.login.LoginViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val pref = UserPref(context)

    // get product
    homeViewModel.getProducts()
    val products = homeViewModel.products.collectAsState()

    // get sellers
    homeViewModel.getSellers()
    val sellers = homeViewModel.seller.collectAsState()

    // get buyers
    homeViewModel.getBuyers()
    val buyers = homeViewModel.buyer.collectAsState()

    // get buyer
    val buyerEmail = pref.getUserEmail.collectAsState("")
    var buyer = BuyersItem()
    buyers.value.map {
        when (it.email) {
            buyerEmail.value.toString() -> {
                buyer = it

                GlobalScope.launch {
                    pref.saveToken(true, buyer.email.toString(), buyer.id!!.toInt())
                }
            }

            else -> {}
        }
    }

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column (
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        "Hey ${buyer.name} \uD83D\uDC4B",
                        style = TextStyle(
                            fontWeight = FontWeight(700),
                            fontSize = 20.sp,
                            color = Color.Black
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                    )

                    Text(
                        "Let's buy vegies!",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier
                    )
                }
                
                Spacer(modifier = Modifier.width(10.dp))

                AsyncImage(
                    model = buyer.image,
                    contentDescription = stringResource(R.string.profile_picture),
                    error = painterResource(id = R.drawable.logo),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.logo),
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            BorderStroke(1.dp, Color.Black),
                            CircleShape
                        )
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate("profileScreen") {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

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

            Text(
                text = "Top Categories",
                style = TextStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(dummyCategory) { category ->
                    var productCount = 0
                    products.value.map {
                        if (it.category!!.categoryName == category.name) {
                            productCount++
                        }
                    }

                    CategoryItem(
                        category,
                        productCount,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("detailCategory/${category.name}")
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Top Patani",
                style = TextStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(count = sellers.value.size) {
                    ShopItem(
                        shop = sellers.value[it],
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Top Products",
                style = TextStyle(
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
            ) {
                items(count = products.value.size) {
                    CategoryProductItem(
                        product = products.value[it],
                        modifier = Modifier.clickable {
                            navController.navigate("detailProduct/${products.value[it].id}")
                        }
                    )
                }
            }


            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 720)
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}