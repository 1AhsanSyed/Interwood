package com.ingenious.powergenerations.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormate {

    companion object{

        fun getDateWithTimeSecond():String{
            val date = System.currentTimeMillis()
//            val dateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault())
            val dateFormat = SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.getDefault())
            val dateStr: String = dateFormat.format(date)

            return dateStr
        }


        fun getDateWithTimeMinutes():String{
            val date = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.getDefault())
            val dateStr: String = dateFormat.format(date)

            return dateStr
        }


        fun getDateWithTimeHours():String{
            val date = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("EEE, d MMM yyyy HH", Locale.getDefault())
            val dateStr: String = dateFormat.format(date)



            return dateStr
        }
    }

}