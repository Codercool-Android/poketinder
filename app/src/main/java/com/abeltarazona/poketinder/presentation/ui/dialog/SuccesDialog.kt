package com.abeltarazona.poketinder.presentation.ui.dialog

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import com.abeltarazona.poketinder.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by AbelTarazona on 17/08/2021
 */
class SuccesDialog(val title: String, val subtitle: String) : BaseDialog() {

    override val layoutId: Int
        get() = R.layout.dialog_success

    override fun initiDialog(view: View, dialog: AlertDialog) {

        view.findViewById<TextView>(R.id.tvTitle).text = title
        view.findViewById<TextView>(R.id.tvSubTitle).text = subtitle

        view.findViewById<FloatingActionButton>(R.id.btnClose).setOnClickListener {
            dialog.dismiss()
        }
    }

}