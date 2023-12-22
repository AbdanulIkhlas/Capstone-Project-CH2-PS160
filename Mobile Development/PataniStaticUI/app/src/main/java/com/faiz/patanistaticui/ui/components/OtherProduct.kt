package com.faiz.patanistaticui.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
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
import com.faiz.patanistaticui.R

@Composable
fun OtherProduct(
    image : Int,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "sayur item",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Text(
            text = title,
            maxLines = 1,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(6.dp),
        )

        Row (
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(
                text = "Rp 20.000",
                fontSize = 15.sp,
                color = Color.Gray,
                overflow = TextOverflow.Clip,
                maxLines = 1,
                modifier = Modifier
                    .padding(6.dp)
                    .wrapContentSize()
            )
            //modifier = Modifier.padding(8.dp)
            Text(
                text = "115 terjual",
                fontSize = 12.sp,
                color = Color.Green,
                overflow = TextOverflow.Clip,
                maxLines = 1,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OtherProductPreview(){
    OtherProduct(image = R.drawable.sayur, title = "Kacang Polong" )
}