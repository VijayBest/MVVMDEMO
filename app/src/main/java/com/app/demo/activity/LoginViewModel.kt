package com.app.demo.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.demo.di.SealedClass
import com.app.demo.repo.AuthRepo
import com.app.demo.utils.FieldValidators
import com.app.demo.utils.ResultWrapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import skycap.android.core.livedata.SingleEventLiveData
import java.util.*
import kotlin.collections.HashMap

class LoginViewModel(val authRepo: AuthRepo) :ViewModel(){

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    var singleEventUiState = SingleEventLiveData<SealedClass>()

    val passwordError: LiveData<String> = Transformations.switchMap(password, FieldValidators::setPassError)
    val emailError: LiveData<String> = Transformations.switchMap(email, FieldValidators::setEmailError)


    fun loginOnClick(){

        if (isLoginData()) {
            val map = HashMap<String, String?>().apply {
                put("email", email.value)
                put("password", password.value)
            }
            singleEventUiState.postValue(SealedClass.HasData(""))
            /*  GlobalScope.launch {
            when(val response=authRepo.login(map)){
                is ResultWrapper.Success -> {
                    singleEventUiState.postValue(SealedClass.DismissLoading)
                    val data=response.value?:return@launch
                    if(data.status==200)
                    {

                    }
                    else {
                        singleEventUiState.postValue(SealedClass.ErrorClass(data?.message))
                    }
                }
                is ResultWrapper.GenericError -> {
                    singleEventUiState.postValue(SealedClass.DismissLoading)
                    singleEventUiState.postValue(SealedClass.ErrorClass(response.error?.error_des?:""))

                }
                ResultWrapper.SocketTimeOutError -> {

                    //Show Error Message
                }
                ResultWrapper.NetworkError -> {

                    // Show error Message
                }

            }


        }*/
        }
        else{

        }
    }

    private fun isLoginData(): Boolean {
        return emailError.value == null && passwordError.value == null && email.value?.isNotEmpty()==true
                && password.value?.isNotEmpty()==true
    }


}