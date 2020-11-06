package com.enact.activity.base

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.demo.R


open class BaseActivity :AppCompatActivity() {
    lateinit var context: Context
     var dialog:Dialog?=null
    lateinit var builder:AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        supportActionBar?.hide()
        showSessionDialog()
    }





    fun showToast(message:String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
    private  fun showSessionDialog()
    {
//        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
//        builder.setTitle("Error")
//                .setMessage("sssssss")
//                .setCancelable(false)
//                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
//        val alert: android.app.AlertDialog = builder.create()
//        // Set to TYPE_SYSTEM_ALERT so that the Service can display it
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alert.getWindow()?.setType(WindowManager.LayoutParams.TYPE_TOAST);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            alert.getWindow()?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
//        }
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
//        {
//            alert.getWindow()?.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        }
//        // alert.getWindow()?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
//        alert.show()


    }
    fun showProgressDialog() {

        if (dialog?.isShowing?:false){
            dialog?.dismiss()
        }
        dialog = Dialog(context)
        val inflate = LayoutInflater.from(context).inflate(R.layout.custom_progress, null)
        dialog?.setContentView(inflate)
        dialog?.setCancelable(false)
        dialog?.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT))
        dialog?.show()
       // CallProgressWheel.showLoadingDialog(context)
    }



    fun hideProgressDialog() {
        try {
            if (dialog != null && dialog?.isShowing?:false) {
                dialog?.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        /*if (CallProgressWheel.isDialogShowing) {
            CallProgressWheel.dismissLoadingDialog()
        }*/
    }


    fun hideKeyboard(applicationContext: Context, mView: View?) {
        if (mView != null) {
            val imm =
                applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(mView.windowToken, 0)
        }
    }

    override fun onDestroy() {
        hideProgressDialog()
        super.onDestroy()
    }

    open fun startSpecificActivity(otherActivityClass: Class<*>?) {
        val intent = Intent(applicationContext, otherActivityClass)
        Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    fun showAlertDialog(context: Context, message: String, title:String?="Alert!") {
        val dialogBuilder = AlertDialog.Builder(context)

        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(title)
        // show alert dialog
        if (!(context as Activity).isFinishing()) {
            alert.show()
        }

    }


    }



