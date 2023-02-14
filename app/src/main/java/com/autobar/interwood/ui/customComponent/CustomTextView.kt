package com.autobar.interwood.ui.customComponent

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun HeadingTextView(
    modifier: Modifier? = Modifier,
    text: String,
    color: Int,
    font: Int,
    style: TextStyle? = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily(Font(font)),
        color = colorResource(id = color),
        textAlign = TextAlign.Center,
        lineHeight = 24.sp,
    )

) {

    Text(
        modifier = modifier!!,
        text = text,
        style = style!!,

        )


}

@Composable
fun ItemTextView(
    modifier: Modifier? = Modifier,
    text: String,
    color: Int,
    font: Int,
    style: TextStyle? = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily(Font(font)),
        color = colorResource(id = color),
        textAlign = TextAlign.Center,
        lineHeight = 24.sp,
    )

) {

    Text(
        modifier = modifier!!,
        text = text,
        style = style!!,

        )


}


@Composable
fun customEditText(
    modifier: Modifier? = Modifier,
    text: String,
    color: Int,
    font: Int,
    style: TextStyle? = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily(Font(font)),
        color = colorResource(id = color),
        textAlign = TextAlign.Center,
        lineHeight = 24.sp,
    )

) {


}





