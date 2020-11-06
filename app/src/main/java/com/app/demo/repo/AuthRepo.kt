package com.app.demo.repo
import com.app.demo.api.ApiService
import com.app.demo.model.CommonArrayObject
import com.app.demo.model.CommonObject
import com.app.demo.sharedpref.SharedPreferenceHelper
import com.app.demo.utils.ResultWrapper
import com.app.demo.utils.safeApiCall

import okhttp3.ResponseBody

/**
 * Created by skycap.
 */
class AuthRepo(private val apiService: ApiService
               ,private val sharedPreferenceHelper: SharedPreferenceHelper){


    suspend fun login(body:HashMap<String,String?>): ResultWrapper<CommonObject<*>?> {
        return when(val call = safeApiCall { apiService.login(body) }){
            is ResultWrapper.Success ->{
                ResultWrapper.Success(call.value)
            }
            is ResultWrapper.GenericError -> ResultWrapper.GenericError()
            ResultWrapper.SocketTimeOutError -> ResultWrapper.SocketTimeOutError
            ResultWrapper.NetworkError -> ResultWrapper.NetworkError
        }
    }

    suspend fun getList(): ResultWrapper<CommonArrayObject<*>?> {
        return when(val call = safeApiCall { apiService.getDashBoard() }){
            is ResultWrapper.Success ->{
                ResultWrapper.Success(call.value)
            }
            is ResultWrapper.GenericError -> ResultWrapper.GenericError()
            ResultWrapper.SocketTimeOutError -> ResultWrapper.SocketTimeOutError
            ResultWrapper.NetworkError -> ResultWrapper.NetworkError
        }
    }


}
