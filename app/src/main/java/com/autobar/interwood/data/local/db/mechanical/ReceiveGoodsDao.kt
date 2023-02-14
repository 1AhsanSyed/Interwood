package com.autobar.interwood.data.local.db.mechanical

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.autobar.interwood.data.models.receiveGoods.Data
import com.autobar.interwood.data.models.receiveGoods.ReceivedGoods
import com.ingenious.powergenerations.data.models.exhaustTemp.ExhaustTemp

@Dao
interface ReceiveGoodsDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeJobData(list: List<Data> )
}