package com.faiz.patanistaticui.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.model.Advertisement
import com.faiz.patanistaticui.model.Product
import com.faiz.patanistaticui.model.dummyProduct
import com.faiz.patanistaticui.R

@Composable
fun LargeCategoryProductItem(
    product: ProductsItem,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(bottom = 20.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .height(160.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row (
            modifier =  Modifier.padding(6.dp)
        ) {
            AsyncImage(
                model = product.image,
                placeholder = painterResource(id = R.drawable.logo),
                error = painterResource(id = R.drawable.logo),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.productName.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold),
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = product.description.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(4.dp)
                ) {
                    Text(
                        text = product.category!!.categoryName.toString(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight(700)),
                        fontSize = 12.sp,
                        color = Color.White,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Rp " + product.price.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold),
                    color = Color(0xFFFFA500),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LargeCategoryProductItemPreview() {
    LargeCategoryProductItem(product = ProductsItem())
}

