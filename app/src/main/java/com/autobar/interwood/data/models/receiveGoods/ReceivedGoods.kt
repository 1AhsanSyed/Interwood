package com.autobar.interwood.data.models.receiveGoods

data class ReceivedGoods(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)