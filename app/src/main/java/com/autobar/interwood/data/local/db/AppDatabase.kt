package com.ingenious.powergenerations.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ingenious.powergenerations.constants.AppConstants
import com.ingenious.powergenerations.data.models.exhaustTemp.ExhaustTemp


@Database(
    entities = [ExhaustTemp::class],
    version = 5,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {

        val mirgration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("Alter Table windTemp ADD COLUMN currentDateTime TEXT NOT NULL DEFAULT '2022/11/08'")
                /*database.execSQL("CREATE TABLE `BearingTemperature` (`id` INTEGER, `name` TEXT, " +
                        "PRIMARY KEY(`id`))")*/
            }
        }

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                AppConstants.DbConfiguration.DB_NAME
            )
                .fallbackToDestructiveMigration()
                .addMigrations(mirgration_1_2)
                .build()
    }

}