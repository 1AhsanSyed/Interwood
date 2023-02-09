package com.ingenious.powergenerations.data.remote


import com.autobar.interwood.data.models.login.UserLogin
import com.autobar.interwood.data.models.packingList.PackingList
import com.autobar.interwood.data.models.packingList.UpdateQC
import com.autobar.interwood.data.models.packingList.UpdateQCResult
import com.google.gson.JsonObject
import com.ingenious.powergenerations.constants.NetworkCallPoints
import com.ingenious.powergenerations.data.models.ProductModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(NetworkCallPoints.API_GET_ALL_PRODUCTS)
    suspend fun getProducts(): Response<List<ProductModel>>

    @POST(NetworkCallPoints.USER_LOGIN)
    suspend fun userLogin(@Body userLogin : JsonObject) : Response<UserLogin>


    @POST(NetworkCallPoints.Packing_Request)
    suspend fun getPackingDetails(@Body packingDetails : JsonObject) : Response<PackingList>

    @POST(NetworkCallPoints.Update_QC_List)
    suspend fun updateQCList(@Body packingDetails : UpdateQC) : Response<UpdateQCResult>




}