package com.app.demo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.app.demo.R
import com.app.demo.activity.list.ListActivity
import com.app.demo.databinding.ActivityLoginBinding
import com.app.demo.di.SealedClass
import com.enact.activity.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

import org.koin.core.parameter.parametersOf
import skycap.android.core.livedata.observeNonNull

class LoginActivity : BaseActivity() {
    lateinit var binding: ActivityLoginBinding
    val viewModel: LoginViewModel by  viewModel()
   //val viewModel:LoginViewModel by viewModel { parametersOf(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel=viewModel
        binding.apply {
            lifecycleOwner= this@LoginActivity
            executePendingBindings()
        }
        initView()
    }

    private fun initView() {

        observeLiveData()
    }

    /*observeLiveData=>Observe All observer here
    * */
    private fun observeLiveData() {
        observeNonNull(viewModel.singleEventUiState) {
            when (it) {
                is SealedClass.Loading -> {
                    showProgressDialog()
                }
                is SealedClass.DismissLoading -> {
                    hideProgressDialog()
                }

                is SealedClass.HasData<*> -> {
                    hideProgressDialog()
                    startSpecificActivity(ListActivity::class.java)
                }

                is SealedClass.ErrorClass -> {
                    hideProgressDialog()
                    showAlertDialog(this,it.errorMessage)
                   // Showing Error
                }
            }
        }
    }
}