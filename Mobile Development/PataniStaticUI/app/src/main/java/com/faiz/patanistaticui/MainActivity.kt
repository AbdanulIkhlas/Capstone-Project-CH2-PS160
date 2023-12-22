package com.faiz.patanistaticui

import ViewModelFactory
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.data.UserPref
import com.faiz.patanistaticui.ui.screen.cart.CartScreen
import com.faiz.patanistaticui.ui.screen.category.CategoryScreen
import com.faiz.patanistaticui.ui.screen.detailCategory.DetailCategoryScreen
import com.faiz.patanistaticui.ui.screen.detailShop.DetailShopScreen
import com.faiz.patanistaticui.ui.screen.home.HomeScreen
import com.faiz.patanistaticui.ui.screen.login.LoginScreen
import com.faiz.patanistaticui.ui.screen.orderlist.OrderListScreen
import com.faiz.patanistaticui.ui.screen.product.DetailProductScreen
import com.faiz.patanistaticui.ui.screen.product.DetailShopScreenPreview
import com.faiz.patanistaticui.ui.screen.profile.PasswordSettingScreen
import com.faiz.patanistaticui.ui.screen.profile.ProfileScreen
import com.faiz.patanistaticui.ui.screen.profile.ProfileSettingScreen
import com.faiz.patanistaticui.ui.screen.profile.TransactionHistoryScreen
import com.faiz.patanistaticui.ui.screen.sellingProduct.SellingProductScreen
import com.faiz.patanistaticui.ui.screen.signin.SigninScreen
import com.faiz.patanistaticui.ui.screen.signin.SigninViewModel
import com.faiz.patanistaticui.ui.theme.PataniStaticUITheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PataniStaticUITheme {
                // A surface container using the 'background' color from the theme
                NavigationView()
            }
        }
    }
}

@Composable
fun NavigationView(
    context: Context = LocalContext.current
) {
    val navController: NavHostController = rememberNavController()
    val pref = UserPref(context)
    val isLogin = pref.getUserIsLogin.collectAsState(initial = false)
    Log.i("userlogin", isLogin.toString())
    val startDestination = if (isLogin.value) "home" else "welcome"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("welcome") {
            Welcome(navController = navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("signin") {
            SigninScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("category") {
            CategoryScreen(navController = navController)
        }
        composable("detailCategory/{categoryName}") {backStackEntry ->
            DetailCategoryScreen(
                navController = navController,
                categoryName = backStackEntry.arguments?.getString("categoryName")!!
            )
        }
        composable("sellingProduct") {
            SellingProductScreen(navController = navController)
        }
        composable("profileScreen") {
            ProfileScreen(navController = navController)
        }
        composable("passwordSetting") {
            PasswordSettingScreen(navController = navController)
        }
        composable("profileSetting") {
            ProfileSettingScreen(navController = navController)
        }
        composable("transactionHistory") {
            TransactionHistoryScreen(navController = navController)
        }
        composable("orderList") {
            OrderListScreen(navController = navController)
        }
        composable("cart") {
            CartScreen(navController = navController)
        }
        composable("detailProduct/{productId}") {backStackEntry ->
            DetailProductScreen(
                navController = navController,
                productId = backStackEntry.arguments?.getString("productId")!!
            )
        }
        composable("detailShop") {
            DetailShopScreen(navController = navController)
        }
    }
}