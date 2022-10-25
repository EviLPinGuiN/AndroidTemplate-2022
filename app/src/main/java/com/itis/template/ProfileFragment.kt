package com.itis.template

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.itis.template.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

//        A->B->C->D->B

        binding?.tvHello?.setOnClickListener {
            bundleOf()

//            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
//            VerifyEmailDialog().show(childFragmentManager, "TAG")
//            TestBottomSheet().show(childFragmentManager, "TAG")
//            parentFragmentManager
//                .beginTransaction()
//                .setCustomAnimations(
//                    android.R.anim.fade_in,
//                    android.R.anim.fade_out,
//                    android.R.anim.fade_in,
//                    android.R.anim.fade_out,
//                )
//                .replace(R.id.host_fragment, HomeFragment.newInstance("MEssage"))
//                .addToBackStack("B")
//                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}