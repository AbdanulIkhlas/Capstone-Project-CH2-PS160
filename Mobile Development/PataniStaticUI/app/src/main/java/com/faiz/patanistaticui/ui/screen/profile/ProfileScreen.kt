package com.faiz.patanistaticui.ui.screen.profile

import ViewModelFactory
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.faiz.patanistaticui.R
import com.faiz.patanistaticui.data.Injection
import com.faiz.patanistaticui.data.UserPref
import com.faiz.patanistaticui.ui.components.BottomBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    context: Context = LocalContext.current,
    profileViewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    modifier: Modifier = Modifier,
) {
    val pref = UserPref(context)
    val buyerId = pref.getUserId.collectAsState(initial = 0)
    profileViewModel.getBuyerById(buyerId.value)
    val buyer = profileViewModel.buyer.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            GlobalScope.launch {
                                val pref = UserPref(context)
                                pref.logout()
                            }
                        },
                        modifier = Modifier
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Log Out",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
        bottomBar = {
            BottomBar(navController)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding((innerPadding))
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
//                    .background(Color(0xFF2EA071))
                    .background(Color.Gray)
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(40.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        AsyncImage(
                            model = buyer.value.image,
                            contentDescription = stringResource(R.string.profile_picture),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.logo),
                            error = painterResource(id = R.drawable.logo),
                            modifier = Modifier
                                .size(60.dp)
                                .border(
                                    BorderStroke(1.dp, Color.Black),
                                    CircleShape
                                )
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Column(
                            modifier = Modifier.weight(3f)
                        ) {
                            Text(
                                text = buyer.value.name.toString(),
                                color = Color.White,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.width(160.dp)
                            )

//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                modifier = Modifier
//                                    .clickable { navController.navigate("profileSetting") }
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Edit,
//                                    contentDescription = "Edit Profile",
//                                    tint = Color.White,
//                                    modifier = Modifier.size(12.dp)
//                                )
//
//                                Spacer(modifier = Modifier.width(4.dp))
//
//                                Text(
//                                    text = "Edit Profile",
//                                    color = Color.White,
//                                    overflow = TextOverflow.Ellipsis,
//                                    fontSize = 12.sp,
//                                    maxLines = 1,
//                                    modifier = Modifier.width(100.dp)
//                                )
//                            }

                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }

//                    Spacer(modifier = Modifier.height(40.dp))

//                    Row (
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 40.dp)
//                            .clickable {
//                                navController.navigate("profileSetting")
//                            }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.AccountCircle,
//                            contentDescription = "Edit Profile",
//                            tint = Color.White,
//                            modifier = Modifier.size(16.dp)
//                        )
//
//                        Spacer(modifier = Modifier.width(16.dp))
//
//                        Text(
//                            text = "Account",
//                            color = Color.White,
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//
//                        Spacer(modifier = Modifier.weight(1f))
//
//                        Icon(
//                            imageVector = Icons.Default.KeyboardArrowRight,
//                            contentDescription = "Edit Profile",
//                            tint = Color.White,
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(40.dp))
//
//                    Row (
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 40.dp)
//                            .clickable { navController.navigate("passwordSetting") }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Lock,
//                            contentDescription = "Edit Profile",
//                            tint = Color.White,
//                            modifier = Modifier.size(16.dp)
//                        )
//
//                        Spacer(modifier = Modifier.width(16.dp))
//
//                        Text(
//                            text = "Password",
//                            color = Color.White,
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//
//                        Spacer(modifier = Modifier.weight(1f))
//
//                        Icon(
//                            imageVector = Icons.Default.KeyboardArrowRight,
//                            contentDescription = "Edit Profile",
//                            tint = Color.White,
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp)
                            .clickable {
                                navController.navigate("orderList") {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                }
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Edit Profile",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Transaction History",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Edit Profile",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }

//                    Spacer(modifier = Modifier.height(40.dp))
//
//                    Row (
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 40.dp)
//                            .clickable { }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Settings,
//                            contentDescription = "Edit Profile",
//                            tint = Color.White,
//                            modifier = Modifier.size(16.dp)
//                        )
//
//                        Spacer(modifier = Modifier.width(16.dp))
//
//                        Text(
//                            text = "Theme",
//                            color = Color.White,
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//
//                        Spacer(modifier = Modifier.weight(1f))
//
//                        Icon(
//                            imageVector = Icons.Default.KeyboardArrowRight,
//                            contentDescription = "Edit Profile",
//                            tint = Color.White,
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }

                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 360, heightDp = 720)
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}
