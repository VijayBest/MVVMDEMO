package com.app.demo.activity.list

import androidx.lifecycle.ViewModel
import com.app.demo.di.SealedClass
import com.app.demo.repo.AuthRepo
import com.app.demo.utils.ResultWrapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import skycap.android.core.livedata.SingleEventLiveData

class ListViewModel (val authRepo: AuthRepo):ViewModel(){

    var singleEventUiState = SingleEventLiveData<SealedClass>()
    lateinit var listItemArrayList: ArrayList<String>

    init {

        listItemArrayList= ArrayList()
        listItemArrayList.add("ABC")
        listItemArrayList.add("DEF")
        listItemArrayList.add("GHI")
        getListIng()
    }


    fun getListIng(){

        singleEventUiState.postValue(SealedClass.HasData(listItemArrayList))
        /* singleEventUiState.postValue(SealedClass.Loading)
         GlobalScope.launch {
             when(val response=authRepo.getList()){
                 is ResultWrapper.Success -> {
                   singleEventUiState.postValue(SealedClass.DismissLoading)
                   singleEventUiState.postValue(SealedClass.HasData(listItemArrayList))
                 }
                 is ResultWrapper.GenericError -> {
                     singleEventUiState.postValue(SealedClass.DismissLoading)

                 }
                 ResultWrapper.SocketTimeOutError -> {
                     singleEventUiState.postValue(SealedClass.DismissLoading)

                 }

                 ResultWrapper.NetworkError -> {
                     singleEventUiState.postValue(SealedClass.DismissLoading)

                 }

             }
         }*/
    }



}