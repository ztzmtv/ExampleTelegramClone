package com.azmetov.telegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.azmetov.telegram.R
import com.azmetov.telegram.databinding.FragmentEnterCodeBinding
import com.azmetov.telegram.utilites.AppTextWatcher
import com.azmetov.telegram.utilites.showToast


class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {
    private var _binding: FragmentEnterCodeBinding? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        // добавляет слушателя и описывает действие лямбды
        binding.registerInputCode.addTextChangedListener(AppTextWatcher {
            val string = binding.registerInputCode.text.toString()
            if (string.length == 6) {
                verifyCode()
            }
        })
    }

    private fun verifyCode() {
        showToast("Ok")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}