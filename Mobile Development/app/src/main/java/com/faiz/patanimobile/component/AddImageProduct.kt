package com.faiz.patanimobile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faiz.patanimobile.model.Product
import com.faiz.patanimobile.model.dummyProduct

@Composable
fun AddImageProduct(
    product: Product,
    modifier: Modifier = Modifier,
    onCLick: () -> Unit = {}
) {
    Column(
        modifier = modifier.clickable { onCLick },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(product.image),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
    }
}

@Composable
@Preview(showBackground = true) 
fun AddProductPreview() {
    AddImageProduct(dummyProduct[3])
}