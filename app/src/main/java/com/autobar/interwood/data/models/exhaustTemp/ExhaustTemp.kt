package com.ingenious.powergenerations.data.models.exhaustTemp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exhaustTemp")
class ExhaustTemp(

    @PrimaryKey(autoGenerate = true)
    var exhaustTempId: Int,

    var masterId : String,
    var categoryName : String,
    var engineName : String,
    var unit : String,
    var load : String,
    var side : String,
    var hour : String,
    var date : String,
    var status : String,
    var userId : String,
    var shift : String,
    var shiftTiming : String,
    var plant : String,
    var currentDateTime : String,
    var isUpdatedOnServer : Int
)