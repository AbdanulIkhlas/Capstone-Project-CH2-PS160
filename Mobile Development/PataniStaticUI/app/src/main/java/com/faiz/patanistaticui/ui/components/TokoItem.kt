package com.faiz.patanistaticui.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import coil.compose.AsyncImage
import com.faiz.patanistaticui.R
import com.faiz.patanistaticui.data.response.Seller
import com.faiz.patanistaticui.model.Shop
import com.faiz.patanistaticui.model.dummyShop

@Composable
fun TokoItem(
    modifier: Modifier = Modifier,
    toko: Seller
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

//        Image(
//            painter = painterResource(id = toko.image),
//            contentDescription = null,
//            modifier = Modifier
//                .size(80.dp)
//                .clip(RoundedCornerShape(10.dp))
//        )

        AsyncImage(
            model = toko.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.logo),
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Toko : ${ toko.name.toString() }",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

//            Spacer(modifier = Modifier.height(6.dp))

//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Star,
//                    contentDescription = "Star",
//                    tint = Color.Yellow,
//                    modifier = Modifier.size(16.dp)
//                )
//
//                Text(
//                    text = "4.5",
//                    fontSize = 12.sp,
//                    color = Color.Gray
//                )
//            }

        }

//        Spacer(modifier = Modifier.width(20.dp))
//
//        Text(
//            text = "Cek Toko",
//            fontSize = 12.sp,
//            color = Color.Black
//        )
//
//        Icon(
//            imageVector = Icons.Default.KeyboardArrowRight,
//            contentDescription = "Lihat Detail",
//            tint = Color.DarkGray,
//            modifier = Modifier.size(20.dp)
//        )
    }
}

@Composable
@Preview(showBackground = true)
fun tokoItemPreview() {
//    TokoItem(toko = dummyShop[1])
}
