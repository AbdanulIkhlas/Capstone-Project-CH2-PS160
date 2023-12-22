package com.faiz.patanistaticui.ui.screen.detailCategory

import ViewModelFactory
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.model.dummyCategory
import com.faiz.patanistaticui.model.dummyProduct
import com.faiz.patanistaticui.ui.components.CategoryItem
import com.faiz.patanistaticui.ui.components.CategoryProductItem
import com.faiz.patanistaticui.ui.components.LargeCategoryItem
import com.faiz.patanistaticui.ui.components.LargeCategoryProductItem
import com.faiz.patanistaticui.ui.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    categoryName: String,
    detailCategoryViewModel: DetailCategoryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    detailCategoryViewModel.getProducts()
    val products = detailCategoryViewModel.products.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
//                    Box (
//                        modifier = Modifier.height(50.dp)
//                    ) {
//                        SearchBar(
//                            query = "",
//                            onQueryChange = {},
//                            modifier = Modifier
//                                .fillMaxWidth()
//                        )
//                    }
                        Text(text = categoryName, color = Color.Black)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
//                actions = {
//                    IconButton(onClick = {  }) {
//                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Action 2")
//                    }
//                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Other Categories",
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

            var counter by rememberSaveable {
                mutableStateOf(0)
            }
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(count = products.value.size) {
                    var productCategory = products.value[it].category!!.categoryName

                    if (productCategory == categoryName) {
                        counter++

                        LargeCategoryProductItem(
                            product = products.value[it],
                            modifier = Modifier.clickable {
                                navController.navigate("detailProduct/${products.value[it].id}")
                            }
                        )
                    }
                }
            }

            if (counter == 0) {
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

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 720)
fun DetailCategoryScreenPreview() {
    DetailCategoryScreen(navController = rememberNavController(), categoryName = "Sayur-Sayuran")
}