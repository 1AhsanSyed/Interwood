package com.autobar.interwood.data.DummyData

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class PackingList(
    val sno : Int,
    val item : String,
    val packet : Int,
    var isSelected : MutableState<Boolean> = mutableStateOf(false)
)
