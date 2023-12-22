package com.faiz.patanistaticui.ui.screen.category

import ViewModelFactory
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.model.Product
import com.faiz.patanistaticui.model.dummyAdvertisement
import com.faiz.patanistaticui.model.dummyCategory
import com.faiz.patanistaticui.ui.components.AdvertiseItem
import com.faiz.patanistaticui.ui.components.BottomBar
import com.faiz.patanistaticui.ui.components.LargeCategoryItem
import com.faiz.patanistaticui.ui.components.SearchBar
import com.faiz.patanistaticui.ui.theme.themeApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    categoryViewModel: CategoryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    categoryViewModel.getProducts()
    val products = categoryViewModel.products.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Category")
                },
//                navigationIcon = {
//                    IconButton(onClick = {  }) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Action 1")
                    }
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Action 2")
                    }
                },
                colors = topAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
    ) { innerPadding ->
        Column(
            modifier.padding(innerPadding)
        ) {
//            Spacer(modifier = Modifier.height(10.dp))
//
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
                text = "Pilihan Category",
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

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier
            ) {
                items(dummyCategory) { category ->
                    var productCount = 0
                    products.value.map {
                        if (it.category!!.categoryName!!.uppercase() == category.name.uppercase()) {
                            productCount++
                        }
                    }

                    LargeCategoryItem(
                        category,
                        productCount,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("detailCategory/${category.name}")
                            }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
@Preview()
fun CategoryScreenPreview() {
    CategoryScreen(
        Modifier.background(MaterialTheme.colorScheme.background),
        rememberNavController()
    )
}

