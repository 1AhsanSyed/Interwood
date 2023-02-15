package com.autobar.interwood.data.local.db

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.autobar.interwood.data.models.receiveGoods.Data
import com.autobar.interwood.data.models.receiveGoods.ReceivedGoods
import com.ingenious.powergenerations.data.models.exhaustTemp.ExhaustTemp

@Dao
interface ReceiveGoodsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeJobData(list: List<Data>)

    @Query("Select count(*) from receiveGoods")
    fun isDataPresent(): Int

    @Query("select * from receiveGoods where qAchecked=1 And isReceived=1 And packNo=:packNo And fgItemCode = :fgCode And packingCode = :packingCode")
    fun checkIsScanned(packNo: Int, fgCode: String, packingCode: String): Boolean

    @Query("update receiveGoods set isReceived =1 where qAchecked=1 And isReceived=0 And packNo=:packNo And fgItemCode = :fgCode And packingCode = :packingCode")
    fun updateScannedItem(packNo: Int, fgCode: String, packingCode: String): Int
}