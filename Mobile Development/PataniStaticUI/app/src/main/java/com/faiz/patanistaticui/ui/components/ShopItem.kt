package com.faiz.patanistaticui.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.faiz.patanistaticui.data.response.SellersItem
import com.faiz.patanistaticui.model.Shop
import com.faiz.patanistaticui.model.dummyShop
import com.faiz.patanistaticui.R

@Composable
fun ShopItem(
    shop: SellersItem,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.width(210.dp)
    ) {
        AsyncImage(
            model = shop.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.logo),
            error = painterResource(id = R.drawable.logo),
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
        )

//        Image(
//            painter = painterResource(R.drawable.sawah),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(140.dp)
//                .fillMaxWidth()
//                .clip(RoundedCornerShape(20.dp))
//        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = shop.name!!,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold),
            color = Color.Black
        )
    }
}


@Composable
@Preview(showBackground = true)
fun ShopItemPreview() {
    ShopItem(SellersItem())
}