package com.azmetov.telegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azmetov.telegram.R
import com.azmetov.telegram.databinding.FragmentEnterPhoneNumberBinding
import com.azmetov.telegram.utilites.replaceFragment
import com.azmetov.telegram.utilites.showToast

class EnterPhoneNumberFragment : BaseFragment(R.layout.fragment_enter_phone_number) {
    private var _binding: FragmentEnterPhoneNumberBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterPhoneNumberBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onStart() {
        super.onStart()
        binding.registerBtnNext.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.registerInputPhoneNumber.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            replaceFragment(EnterCodeFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}