package com.ingenious.powergenerations.constants

import androidx.annotation.StringDef


object AppConstants {

    @StringDef(ApiConfiguration.BASE_URL)
    annotation class ApiConfiguration {
        companion object {
            //            const val BASE_URL = "http://110.39.7.210:704/api/"
//            const val BASE_URL = "http://192.168.6.11:901/api/"
//            const val BASE_URL = "http://192.168.100.18:8082/api/"
//            const val BASE_URL = "http://escloud.dyndns.info:709/api/"
//            const val BASE_URL = "http://192.168.2.100:922/api/"
//            const val BASE_URL = "http://192.168.2.100:922/api/"
//            const val BASE_URL = "http://192.168.6.11:905/api/"
            const val BASE_URL = "http://192.168.100.58:708/api/"
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