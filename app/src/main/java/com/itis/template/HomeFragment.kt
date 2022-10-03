package com.itis.template

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARG_NAME)?.also {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        view.findViewById<TextView>(R.id.tv_hello).setOnClickListener {
            showDialog2()
        }
    }

    private fun showDialog2() {
        showDialog(
            title = "New title 2",
            positiveAction = {
            },
        )

        return
       showDialog(
           title = "New title",
           positiveAction = {
               parentFragmentManager.beginTransaction()
                   .replace(R.id.container, ProfileFragment())
                   .commit()
           },
           negativeAction = {},
           neutralAction = {}
       )
    }

    companion object {

        private const val ARG_NAME = "name_arg"

        fun newInstance(name: String) = HomeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_NAME, name)
            }
        }

        fun newInstance2(name: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            bundle.putString(ARG_NAME, name)
            fragment.arguments = bundle
            return fragment
        }
    }
}