package com.faiz.patanistaticui.ui.screen.orderlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faiz.patanistaticui.data.response.OrderItem
import com.faiz.patanistaticui.data.response.ProductsItem
import com.faiz.patanistaticui.ui.components.SellingProductItem

@Composable
fun TabView(
    type: String,
    buyerId: Int,
    orders: State<List<OrderItem>>,
    products: State<List<ProductsItem>>,
) {
    var counter by rememberSaveable { mutableStateOf(0) }

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(horizontal = 10.dp),
            modifier = Modifier
        ) {
            items(count = orders.value.size) {orderIndex ->
                if (orders.value[orderIndex].status.toString() == type) {
                    if (orders.value[orderIndex].buyerId == buyerId) {
                        products.value.map {product ->
                            if (product.id == orders.value[orderIndex].productId){
                                counter++
                                SellingProductItem(
                                    product = product,
                                    qty = orders.value[orderIndex].qty!!,
                                    total_price = orders.value[orderIndex].totalPrice.toString(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        if (counter == 0) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Item Kosong",
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }

}


@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 720)
fun TabViewPreview() {
//    TabView("Offer")
}