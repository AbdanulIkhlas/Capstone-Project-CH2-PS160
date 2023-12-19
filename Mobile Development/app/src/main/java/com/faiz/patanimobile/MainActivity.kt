package com.faiz.patanimobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.faiz.patanimobile.model.Category
import com.faiz.patanimobile.model.dummyCategory
import com.faiz.patanimobile.screen.cart.CartScreen
import com.faiz.patanimobile.screen.detail.DetailProductScreen
import com.faiz.patanimobile.screen.detail.DetailTokoScreen
import com.faiz.patanimobile.ui.theme.PataniMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PataniMobileTheme {
                DetailProductScreen(category = dummyCategory[1], baseHarga = 7500, count = 1,)
            }
        }
    }
}