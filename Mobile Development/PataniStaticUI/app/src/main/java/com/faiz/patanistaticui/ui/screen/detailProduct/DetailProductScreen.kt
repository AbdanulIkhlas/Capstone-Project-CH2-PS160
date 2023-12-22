package com.faiz.patanistaticui.ui.screen.product

import ViewModelFactory
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.faiz.patanistaticui.R
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.data.UserPref
import com.faiz.patanistaticui.data.response.NewOrder
import com.faiz.patanistaticui.isValidEmail
import com.faiz.patanistaticui.model.dummyShop
import com.faiz.patanistaticui.parseJsonToModel
import com.faiz.patanistaticui.readJsonFromAssets
import com.faiz.patanistaticui.ui.components.CategoryProductItem
import com.faiz.patanistaticui.ui.components.ProductCounter
import com.faiz.patanistaticui.ui.components.TokoItem
import com.faiz.patanistaticui.ui.screen.detailProduct.DetailProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProductScreen(
    navController: NavHostController,
    detailProductViewModel: DetailProductViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    productId: String = "",
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier
) {
    // get buyerid pref
    val pref = UserPref(context)
    val buyerId = pref.getUserId.collectAsState(initial = 0)

    // add order response
    val addOrderResponse = detailProductViewModel.addOrder.collectAsState(NewOrder())

    // get product
    detailProductViewModel.getProducts()
    val products = detailProductViewModel.products.collectAsState()

    // get product by id
    detailProductViewModel.getProductById(productId)
    val productById = detailProductViewModel.product.collectAsState()

    // get sellers
    detailProductViewModel.getSellers()
    val sellers = detailProductViewModel.sellers.collectAsState()

    // get sellers by id
    if (productById.value.id != null) {
        detailProductViewModel.getSellerById(productById.value.seller!!.id!!)
    }
    val seller = detailProductViewModel.seller.collectAsState()

    // get buyers
    detailProductViewModel.getBuyers()
    val buyers = detailProductViewModel.buyer.collectAsState()

    // post to get ML
//    detailProductViewModel.getPredict("2023-12-21", "2023-12-27", "buncis")
//    val prediction = detailProductViewModel.prediction.collectAsState()

    // ML json model
    val jsonString = readJsonFromAssets(context, "model.json")
    val predictionList = parseJsonToModel(jsonString)
    var pred by rememberSaveable {
        mutableStateOf(0)
    }

    var total_pred by rememberSaveable {
        mutableStateOf(0)
    }

    var inputPrice by rememberSaveable {
        mutableStateOf("")
    }

    var qtyCounter by rememberSaveable {
        mutableStateOf(0)
    }

    if (productById.value.id != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Detail Product")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = productById.value.image,
                    contentDescription = "image description",
                    placeholder = painterResource(id = R.drawable.logo),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(360.dp)
                        .height(240.dp)
                )
                Column (
                    modifier = Modifier.padding(16.dp)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = productById.value.productName.toString(),
                            fontSize = 25.sp,
                            color = Color.Black,
                            fontWeight = FontWeight(700),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
//                    Icon(
//                        imageVector = Icons.Default.FavoriteBorder,
//                        contentDescription = "Favorite",
//                        tint = Color.Red,
//                        modifier = Modifier.size(25.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Icon(
//                        imageVector = Icons.Default.Share,
//                        contentDescription = "Share",
//                        tint = Color.Gray,
//                        modifier = Modifier.size(25.dp)
//                    )
                    }
                    Text(
                        text = "Rp ${productById.value.price}/kg",
//                        text = "Rp 35000/kg",
                        fontSize = 20.sp,
                        color = Color.Black,
                        overflow = TextOverflow.Clip,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )

                    predictionList.map {
                        if (it.namaProduk.uppercase() == productById.value.productName!!.uppercase()) {
                            pred = it.prediksiHarga
                        }
                    }

                    Text(
//                        text = " Prediksi harga = Rp ${prediction.value.jsonMember20231221T000000000Z}/kg",
                        text = " Rekomendasi harga = Rp ${pred}/kg",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        overflow = TextOverflow.Clip,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color(0xFF2CC16B))
                            .padding(4.dp)
                    ) {
                        Text(
                            text = productById.value.category!!.categoryName.toString(),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight(700)
                            ),
                            fontSize = 15.sp,
                            color = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Column(modifier = Modifier.padding(4.dp)) {
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
                            text = productById.value.description!!,
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    TokoItem(
                        toko = seller.value,
                        modifier = Modifier
//                        .clickable {
//                            navController.navigate("detailShop")
//                        }
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
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

                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    OutlinedTextField(
                        value = inputPrice,
                        onValueChange = { inputPrice = it },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 16.sp
                        ),
                       colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF2EA071),
                            unfocusedBorderColor = Color(0xFF2EA071)
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(2f)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    ProductCounter(
                        value = qtyCounter,
                        onProductIncreased = { qtyCounter++ },
                        onProductDecreased = {
                                             if(qtyCounter > 0) {
                                                 qtyCounter--
                                             }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                if (qtyCounter > 0) {
                    total_pred = qtyCounter * pred
                    Text(
                        text = "Rekomendasi harga = Rp ${ total_pred }",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(10.dp))

                var total_price by rememberSaveable {
                    mutableStateOf(0)
                }
                Button(
                    onClick = {
                        total_price = qtyCounter * inputPrice.toInt()
//                        Toast.makeText(context, total_price.toString(), Toast.LENGTH_SHORT).show()

                        if (qtyCounter > 0) {
                            detailProductViewModel.addOrder(
                                status = "Offer",
                                qty = qtyCounter,
                                total_price = total_price.toString(),
                                sellerId = seller.value.id,
                                buyerId = buyerId.value,
                                productId = productId.toInt()
                            )

                            if (addOrderResponse.value.id != null) {
                                Toast.makeText(context, "Add Offer Success", Toast.LENGTH_SHORT).show()
                            }

                            navController.navigate("orderList") {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Ajukan",
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 1200)
fun DetailShopScreenPreview() {
    DetailProductScreen(navController = rememberNavController())
}