package com.faiz.patanistaticui.ui.screen.signin

import ViewModelFactory
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.R
import com.faiz.patanistaticui.ui.components.AuthButton
import com.faiz.patanistaticui.ui.components.AuthTextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.viewmodel.compose.viewModel
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.data.UserPref
import com.faiz.patanistaticui.isValidEmail
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun SigninScreen(
    navController: NavHostController,
    signinViewModel: SigninViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var alamat by rememberSaveable { mutableStateOf("") }
    var koordinat by rememberSaveable { mutableStateOf("1234567") }
    var no_hp by rememberSaveable { mutableStateOf("") }

//    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent(),
//        onResult = {
//            selectedImageUri = it
//        }
//    )

    Surface(
//        color = Color(0xFFC0FBBB)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.logo_image),
                modifier = Modifier
                    .size(160.dp)
            )
            Text(
                "Sign In",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color(0xFF2EA071),
                    fontWeight = FontWeight(700)
                ),
                modifier = Modifier.offset(y = (-20.dp))
            )

            AuthTextField(
                hint = "Name",
                value = name,
                onValueChange = { name = it },
            )

            AuthTextField(
                hint = "Email Address",
                value = email,
                onValueChange = { email = it }
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

            AuthTextField(
                hint = "Address",
                value = alamat,
                onValueChange = { alamat = it }
            )

            AuthTextField(
                hint = "Phone",
                value = no_hp,
                onValueChange = { no_hp = it }
            )

//            Spacer(modifier = Modifier.height(20.dp))
//
//            Button(
//                onClick = { launcher.launch("image/*") },
//                content = { Text("Select Image") }
//            )

            Spacer(modifier = Modifier.height(20.dp))

            AuthButton(
                text = stringResource(R.string.sign_in),
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        if (isValidEmail(email)) {
                            GlobalScope.launch {
                                signinViewModel.registerBuyer(
                                    email,
                                    password,
                                    name,
                                    alamat,
                                    koordinat,
                                    no_hp
                                )
                            }

                            Toast.makeText(context, "Signin Berhasil", Toast.LENGTH_SHORT).show()

                            navController.navigate("login") {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
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
                    "Already have an account?",
                    color = Color.Black,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    "Login",
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {
                        navController.navigate("login") {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 640)
fun SigninScreenPreview() {
    SigninScreen(
        rememberNavController()
    )
}