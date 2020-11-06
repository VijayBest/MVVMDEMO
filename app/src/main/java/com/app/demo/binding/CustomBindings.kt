package com.app.demo.binding
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter

import androidx.lifecycle.MutableLiveData
import com.enact.binding.setVisibleOrGone


@BindingAdapter("error")
fun EditText.setError(errorMessage: String?) {
    if (errorMessage != "") {
        error = errorMessage
    } else {
        error = null
    }
}


@BindingAdapter("visibility")
fun View.setVisibility(value: MutableLiveData<Boolean>) {
    setVisibleOrGone(value.value)
}




