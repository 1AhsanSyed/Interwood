package com.autobar.interwood.ui
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val QuickSand = FontFamily(
    Font(com.autobar.interwood.R.font.poppins_light, weight = FontWeight.W300),
    Font(com.autobar.interwood.R.font.poppins_medium, weight = FontWeight.W300),
    Font(com.autobar.interwood.R.font.poppins_regular, weight = FontWeight.W400),
    Font(com.autobar.interwood.R.font.poppins_semibold, weight = FontWeight.W600),

)

val InterwoodQuickFont = Typography(
    h1 = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W600,
        fontSize = 25.sp,
    )
)