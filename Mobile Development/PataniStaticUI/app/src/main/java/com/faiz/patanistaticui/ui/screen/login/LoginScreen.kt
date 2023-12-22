package com.faiz.patanistaticui.ui.screen.login

import ViewModelFactory
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.R
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.data.UserPref
import com.faiz.patanistaticui.isValidEmail
import com.faiz.patanistaticui.ui.components.AuthButton
import com.faiz.patanistaticui.ui.components.AuthTextField
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val pref = UserPref(context)

    val loginResponse = loginViewModel.loginReponse.collectAsState()

    Surface(
//        color = Color(0xFFC0FBBB)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.logo_image),
                modifier = Modifier
                    .padding(top = 54.dp)
                    .size(160.dp)
            )
            Text(
                "Log In",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color(0xFF2EA071),
                    fontWeight = FontWeight(700)
                ),
                modifier = Modifier.offset(y = (-20.dp))
            )

            AuthTextField(
                hint = "Email Address",
                value = email,
                onValueChange = { email = it },
            )

            if (email.isNotEmpty()) {
                if (!isValidEmail(email)) {
                    Text(
                        text = "Email is not valid",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                }
            }

            AuthTextField(
                hint = "Password",
                value = password,
                onValueChange = { password = it },
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            AuthButton(
                text = stringResource(R.string.log_in),
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        if (isValidEmail(email)) {
                            loginViewModel.loginUser(email, password)

                            GlobalScope.launch {
                                pref.saveToken(isLogin = true, userEmail = email, 0)
                            }

                            if (loginResponse.value.isNotEmpty()) {
                                if (loginResponse.value == "success"){
                                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()

                                    navController.navigate("home") {
                                        popUpTo(navController.graph.id) {
                                            inclusive = true
                                        }
                                    }
                                }
                                else {
                                    Toast.makeText(context, loginResponse.value, Toast.LENGTH_SHORT).show()
                                }
                            }

                        } else {
                            Toast.makeText(context, "Insufficient Creds", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Insufficient Creds", Toast.LENGTH_SHORT).show()
                    }
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row {
                Text(
                    "Don't have an account?",
                    color = Color.Black,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    "Signin",
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {
                        navController.navigate("signin") {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 640)
fun LoginScreenPreview() {
    LoginScreen(
        rememberNavController()
    )
}