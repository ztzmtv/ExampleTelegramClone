package com.azmetov.telegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.azmetov.telegram.MainActivity
import com.azmetov.telegram.R
import com.azmetov.telegram.activities.RegisterActivity
import com.azmetov.telegram.databinding.FragmentEnterCodeBinding
import com.azmetov.telegram.utilites.AUTH
import com.azmetov.telegram.utilites.AppTextWatcher
import com.azmetov.telegram.utilites.replaceActivity
import com.azmetov.telegram.utilites.showToast
import com.google.firebase.auth.PhoneAuthProvider


class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {
    private var _binding: FragmentEnterCodeBinding? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        // добавляет слушателя и описывает действие лямбды
        binding.registerInputCode.addTextChangedListener(AppTextWatcher {
            val string = binding.registerInputCode.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() {
        val code = binding.registerInputCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else showToast(task.exception?.message.toString())
        }
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