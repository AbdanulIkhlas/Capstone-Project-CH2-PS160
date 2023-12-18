package com.faiz.patanimobile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faiz.patanimobile.R
import com.faiz.patanimobile.ui.theme.PataniMobileTheme

@Composable
fun ItemToko(name: String, modifier: Modifier) {
    var isExpand = remember { mutableStateOf(false) }

    Row (
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.toko),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column (modifier = Modifier.weight(1f)) {
            Text(
                text = "Toko Pak Budi",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "star",
                    tint = Color.Yellow,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "4.0 Reviews",
                    fontSize = 12.sp,
                    color = Color.Black,
                    overflow = TextOverflow.Clip,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Text(
            text = "Cek Toko",
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal
        )
        IconButton(onClick = { isExpand.value = !isExpand.value }) {
            Icon(
                imageVector = if (isExpand.value) Icons.Filled.ExpandLess else Icons.Outlined.ExpandMore,
                contentDescription = if (isExpand.value) "Show more" else "Show more"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemTokoPreview () {
    PataniMobileTheme {
        ItemToko(name = "Toko Pak Budi", modifier = Modifier)
    }
}