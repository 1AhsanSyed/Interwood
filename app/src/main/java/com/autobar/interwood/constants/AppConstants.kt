package com.ingenious.powergenerations.constants

import androidx.annotation.StringDef


object AppConstants {

    @StringDef(ApiConfiguration.BASE_URL)
    annotation class ApiConfiguration {
        companion object {
//            const val BASE_URL = "http://escloud.dyndns.info:706/"
//
            const val BASE_URL = "http://192.168.100.45:708/"
        }
    }

    @StringDef(DbConfiguration.DB_NAME)
    annotation class DbConfiguration {
        companion object {
            const val DB_NAME = "BaseProject"
        }
    }


    @StringDef(DataStore.DATA_STORE_NAME, DataStore.LOCALIZATION_KEY_NAME, DataStore.USER_NAME_KEY,DataStore.CONTINUE_KEY, DataStore.DATA_STORE_NAME)
    annotation class DataStore {
        companion object {
            const val DATA_STORE_NAME = "BaseProject"
            const val LOCALIZATION_KEY_NAME = "lang"
            const val USER_NAME_KEY = "user_name_key"
            const val CONTINUE_KEY = "continue_key"
        }
    }

}