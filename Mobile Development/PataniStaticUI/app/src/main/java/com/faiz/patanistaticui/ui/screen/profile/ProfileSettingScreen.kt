package com.faiz.patanistaticui.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.faiz.patanistaticui.ui.components.AuthButton
import com.faiz.patanistaticui.ui.components.AuthTextField
import com.faiz.patanistaticui.ui.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingScreen(
    navController: NavHostController
) {
    var name by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                "Update Profile",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color(0xFF2EA071),
                    fontWeight = FontWeight(700)
                )
            )

//            AuthTextField(hint = "Name", value = name)
//
//            AuthTextField(hint = "Email", value = email)
//
//            AuthTextField(hint = "Address", value = address)
//
//            AuthTextField(hint = "Phone", value = phone)

            Spacer(modifier = Modifier.height(24.dp))

            AuthButton(
                text = "Update  ",
                onClick = { }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 720)
fun ProfileSettingScreenPreview() {
    ProfileSettingScreen(navController = rememberNavController())
}