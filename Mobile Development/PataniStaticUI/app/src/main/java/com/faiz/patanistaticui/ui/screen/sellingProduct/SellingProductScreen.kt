package com.faiz.patanistaticui.ui.screen.sellingProduct

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.model.dummyCategory
import com.faiz.patanistaticui.model.dummyProduct
import com.faiz.patanistaticui.ui.components.LargeCategoryItem
import com.faiz.patanistaticui.ui.components.SearchBar
import com.faiz.patanistaticui.ui.components.SellingProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellingProductScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "List Jualan Produk")
                },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                contentColor = Color.White,
                shape = CircleShape,
                containerColor = Color(0xFF2EA071)
            ){
                Icon(Icons.Default.Add,"")
            }
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            Box (
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                SearchBar(
                    query = "",
                    onQueryChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                items(dummyProduct) { product ->
//                    SellingProductItem(product = product, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 720)
fun SellingProductScreenPreview() {
    SellingProductScreen(navController = rememberNavController())
}