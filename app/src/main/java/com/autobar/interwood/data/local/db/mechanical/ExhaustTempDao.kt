package com.ingenious.powergenerations.data.local.db.mechanical

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ingenious.powergenerations.data.models.exhaustTemp.ExhaustTemp

@Dao
interface ExhaustTempDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeHourlyExhaust(list: List<ExhaustTemp> )

}