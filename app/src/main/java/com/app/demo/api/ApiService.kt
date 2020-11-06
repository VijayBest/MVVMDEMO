package com.app.demo.api


import com.app.demo.model.CommonArrayObject
import com.app.demo.model.CommonObject
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


/**
 * Created by skycap.
 */
interface ApiService {
    @POST("login")
    @FormUrlEncoded
    suspend fun login(@FieldMap map: HashMap<String,String?>):Response<CommonObject<*>>

    @GET("dashboard")
    suspend fun getDashBoard():Response<CommonArrayObject<*>>

}



