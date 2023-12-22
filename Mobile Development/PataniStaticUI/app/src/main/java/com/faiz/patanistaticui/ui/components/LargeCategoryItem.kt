package com.faiz.patanistaticui.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.model.Category
import com.faiz.patanistaticui.model.dummyCategory

@Composable
fun LargeCategoryItem(
    category: Category,
    productCount: Int,
    modifier: Modifier = Modifier,
    onCLick: () -> Unit = {}
) {
    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(category.icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column (
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = category.name,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight(700),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )

                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Masuk ke kategori",
                        tint = Color.Black
                    )
                }

                Text(
                    text = productCount.toString() + " Products",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LargeCategoryItemPreview(){
    LargeCategoryItem(dummyCategory[1], 10)
}