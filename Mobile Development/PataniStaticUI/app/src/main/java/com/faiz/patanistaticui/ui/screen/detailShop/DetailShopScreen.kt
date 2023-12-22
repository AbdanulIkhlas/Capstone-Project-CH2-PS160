package com.faiz.patanistaticui.ui.screen.detailShop

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.model.dummyCategory
import com.faiz.patanistaticui.model.dummyProduct
import com.faiz.patanistaticui.model.dummyShop
import com.faiz.patanistaticui.ui.components.CategoryItem
import com.faiz.patanistaticui.ui.components.SearchBar
import com.faiz.patanistaticui.ui.components.SellingProductItem
import com.faiz.patanistaticui.ui.components.TokoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailShopScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detail Toko")
                },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Box (
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
//                TokoItem(toko = dummyShop[1])
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Deskripsi",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(700),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = "Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna.Ultrices mauris Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula consectetur, ultrices mauris Lorem",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF424040)
                    )
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier = Modifier
                    .weight(4f)
                    .padding(bottom = 10.dp)
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
fun DetailShopScreenPreview() {
    DetailShopScreen(navController = rememberNavController())
}