package com.faiz.patanimobile.screen.cart

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faiz.patanimobile.component.AddImageProduct
import com.faiz.patanimobile.model.dummyProduct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreenSeller(
    modifier: Modifier = Modifier
) {

    var description by remember { mutableStateOf(TextFieldValue("Deskripsi")) }
    //var content by remember { mutableStateOf(TextFieldValue("Isi Deskripsi")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tambah Product",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
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

        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ){
            Text(
                text = "Nama Produk",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Masukkan Nama Produk") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))

            LazyRow(
                modifier = modifier,
                contentPadding = PaddingValues(start = 6.dp, end = 6.dp)
            ) {
                items(dummyProduct) { product ->
                    AddImageProduct(product = product, modifier = Modifier.padding(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(6.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Deskripsi",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                OutlinedTextField(
                    value = "Indonesia adalah negara otoriter yang harus di selesaiakn oleh Presiden ",
                    onValueChange = {},
                    label = { Text("Masukkan Deskripsi Produk") },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Kategori",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight(700),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF2EA071))
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Sayur-sayuran",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight(700)
                                ),
                                fontSize = 8.sp,
                                color = Color.White,
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF2EA071))
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Kacang-kacangan",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight(700)
                                ),
                                fontSize = 8.sp,
                                color = Color.White,
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.ArrowDropDownCircle,
                            contentDescription = "Drop down fillter"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Jenis Produk",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight(700),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Gray)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Masukkan Jumlah Produk",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight(900)
                                ),
                                fontSize = 8.sp,
                                color = Color.White,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Gray)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Kacang-kacangan",
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
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Minimal Pembelian",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight(700),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Gray)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Masukkan Jumlah Produk",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight(900)
                                ),
                                fontSize = 8.sp,
                                color = Color.White,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Gray)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Kacang-kacangan",
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
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Ketahanan Produk",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight(700),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Gray)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Masukkan Jumlah Produk",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight(900)
                                ),
                                fontSize = 8.sp,
                                color = Color.White,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Gray)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "Kacang-kacangan",
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
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {  },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2EA071)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "Tambahkan Produk",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(800),
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CartScreenSellerPreview(){
    CartScreenSeller()
}