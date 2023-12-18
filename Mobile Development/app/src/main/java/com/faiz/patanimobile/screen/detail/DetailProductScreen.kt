package com.faiz.patanimobile.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faiz.patanimobile.R
import com.faiz.patanimobile.component.ItemToko
import com.faiz.patanimobile.component.ProductCount
import com.faiz.patanimobile.component.ProductItem
import com.faiz.patanimobile.model.Category
import com.faiz.patanimobile.model.dummyCategory
import com.faiz.patanimobile.model.dummyMenu
import com.faiz.patanimobile.ui.theme.PataniMobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProductScreen(
    category: Category,
    baseHarga: Int,
    count: Int,
    modifier: Modifier = Modifier
) {
    var totalHarga by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "DetailScreen")
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sayur),
                contentDescription = "Kacang-kacangan",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Kacang-kacangan",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Rp 10.000",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.Red)
                            .padding(4.dp)
                    ) {
                        Text(
                            text = category.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight(700)
                            ),
                            fontSize = 8.sp,
                            color = Color.White,
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    Modifier.padding(start = 20.dp, end = 10.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "share",
                    Modifier.padding(start = 10.dp, end = 10.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier =  Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = "Deskripsi",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Color.Black,
                )
                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 10.sp,
                    color = Color.Black,
                )
            }
            Box(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                ItemToko(name = "Toko pak Burhan", modifier = Modifier.fillMaxSize())
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Product Lainnya",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(6.dp)
                )
                LazyRow(
                    modifier = modifier,
                    contentPadding = PaddingValues(start = 6.dp, end = 6.dp)
                ) {
                    items(dummyMenu)  { menu ->
                        ProductItem(menu = menu, modifier = Modifier.padding(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Surface (
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            Text(text = "Rp 5.000", color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        ProductCount(
                            1,
                            orderCount,
                            onProductIncreased = { orderCount++ },
                            onProductDecreased = { if (orderCount > 0) orderCount-- },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 16.dp)
                        )
                        totalHarga = baseHarga * orderCount
                    }
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            Text(text = "Tawaran", color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    PataniMobileTheme {
        DetailProductScreen(category = dummyCategory[1], baseHarga = 7500, count = 1,)
    }
}


