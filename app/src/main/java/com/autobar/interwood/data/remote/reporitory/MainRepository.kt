package com.ingenious.powergenerations.data.remote.reporitory

import com.google.gson.JsonObject
import com.ingenious.powergenerations.data.local.db.AppDao
import com.ingenious.powergenerations.data.remote.ApiService
import retrofit2.http.Body
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {

    suspend fun getProducts() = apiService.getProducts()
    suspend fun userLogin(@Body param : JsonObject) = apiService.userLogin(param)


}