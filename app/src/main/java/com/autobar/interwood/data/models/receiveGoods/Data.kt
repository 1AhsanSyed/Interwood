package com.autobar.interwood.data.models.receiveGoods

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receiveGoods")
data class Data(
    val compCode: String,
    val compQty: Int,
    val customer: String,
    val docEntryDate: String,
    val docEntryNo: String,
    val fgItemCode: String,
    val fgItemName: String,
    val isReceived: Boolean,
    val jobNo: String,
    val jobQty: String,
    val packNo: Int,
    val packingCode: Int,
    val packingDetailCode: Int,
    val qAchecked: Boolean,

    @PrimaryKey(autoGenerate = false)
    val row_num: Int,

    val sItemCode: String,
    val sItemName: String,
    val salesOrder: String,
    val transDate: String,
    val type: String
)