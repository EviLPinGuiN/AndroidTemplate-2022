package com.itis.template

import android.app.AlertDialog
import androidx.fragment.app.Fragment

typealias Click = () -> Unit
typealias ClickOne = (String) -> Unit

fun Fragment.showDialog(
    title: String = "",
    message: String = "",
    positiveText: String = "",
    positiveAction: Click? = null,
    negativeAction: Click? = null,
    neutralAction: ClickOne? = null,
) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveText) { dialog, _ ->
            positiveAction?.invoke()
            dialog.dismiss()
        }
        .setNegativeButton("CANCEL") { dialog, _ ->
            negativeAction?.invoke()
            dialog.dismiss()
        }
        .setNeutralButton("rerrorro") { dialog, _ ->
            neutralAction?.invoke("")
            dialog.dismiss()
        }
        .show()
}