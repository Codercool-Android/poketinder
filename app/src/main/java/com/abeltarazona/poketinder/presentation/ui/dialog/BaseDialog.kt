package com.abeltarazona.poketinder.presentation.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.view.Window

/**
 * Created by AbelTarazona on 17/08/2021
 */
abstract class BaseDialog {

    fun show(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        val view = activity.layoutInflater.inflate(layoutId, null)
        builder.setView(view)

        val dialog = builder.create()

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.decorView?.setBackgroundResource(android.R.color.transparent)

        initiDialog(view, dialog)
        dialog.show()
    }

    protected abstract val layoutId: Int

    protected abstract fun initiDialog(view: View, dialog: AlertDialog)

}