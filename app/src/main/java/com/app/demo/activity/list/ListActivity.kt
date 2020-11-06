package com.app.demo.activity.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.app.demo.R
import com.app.demo.activity.LoginViewModel
import com.app.demo.adapter.DemoAdapter
import com.app.demo.adapter.ItemClick
import com.app.demo.databinding.ActivityListBinding
import com.app.demo.databinding.ActivityLoginBinding
import com.app.demo.di.SealedClass
import com.enact.activity.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import skycap.android.core.livedata.observeNonNull

class ListActivity : BaseActivity() {
    lateinit var binding: ActivityListBinding
    val viewModel: ListViewModel by  viewModel()
    lateinit var demoAdapter: DemoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        binding.apply {
            lifecycleOwner= this@ListActivity
            executePendingBindings()
        }
        initView()
    }

    private fun initView() {
        observeLiveData()
        setAdapter()
    }

    private fun setAdapter() {
        demoAdapter= DemoAdapter(object :ItemClick{
            override fun onItemClick(item: String) {
                showToast(item)
            }
        })
        binding.listView.adapter= demoAdapter

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
                    val arrayList = it.content as ArrayList<String>
                    demoAdapter.submitList(arrayList)
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