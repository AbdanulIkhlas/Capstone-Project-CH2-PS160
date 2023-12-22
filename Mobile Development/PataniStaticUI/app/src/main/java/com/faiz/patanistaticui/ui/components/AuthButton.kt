package com.faiz.patanistaticui.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faiz.patanistaticui.R

@Composable
fun AuthButton(
    text: String,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2EA071)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(800),
                color = Color.White
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AuthButtonPreview(){
    AuthButton(
        text = ""
    )
}