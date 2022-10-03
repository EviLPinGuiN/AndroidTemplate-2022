package com.itis.template

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class VerifyEmailDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_home, null, false)
        return AlertDialog.Builder(requireContext())
            .setTitle("Title")
            .setView(view)
            .setMessage("misato")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    companion object {

        fun newInstance(fragmentManager: FragmentManager) {
            return VerifyEmailDialog().show(fragmentManager, "Tag")
        }
    }
}