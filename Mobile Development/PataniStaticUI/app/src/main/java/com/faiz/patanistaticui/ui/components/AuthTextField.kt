package com.faiz.patanistaticui.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faiz.patanistaticui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    hint: String,
    value: String,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(!isPassword) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                hint,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF2EA071)
                ),
            )
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible)
                    painterResource(R.drawable.ic_eye)
                else painterResource(R.drawable.ic_eye_red)

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(painter = image, description)
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.Transparent,
            focusedBorderColor = Color(0xFF2EA071),
            unfocusedBorderColor = Color(0xFF2EA071)
        )
    )
}

@Composable
@Preview(showBackground = true)
fun AuthTextFieldPreview() {
    AuthTextField(
        hint = "",
        value = "",
        onValueChange = {}
    )
}