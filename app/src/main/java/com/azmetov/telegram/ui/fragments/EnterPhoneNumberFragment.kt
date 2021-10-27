package com.azmetov.telegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azmetov.telegram.MainActivity
import com.azmetov.telegram.R
import com.azmetov.telegram.activities.RegisterActivity
import com.azmetov.telegram.databinding.FragmentEnterPhoneNumberBinding
import com.azmetov.telegram.utilites.AUTH
import com.azmetov.telegram.utilites.replaceActivity
import com.azmetov.telegram.utilites.replaceFragment
import com.azmetov.telegram.utilites.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class EnterPhoneNumberFragment : BaseFragment(R.layout.fragment_enter_phone_number) {
    private var _binding: FragmentEnterPhoneNumberBinding? = null
    private val binding get() = _binding!!
    private lateinit var mPhoneNumber: String
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.registerBtnNext.setOnClickListener { sendCode() }
        AUTH = FirebaseAuth.getInstance()
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(task.exception?.message.toString())
                }
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber, id))
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

        }
    }

    private fun sendCode() {
        if (binding.registerInputPhoneNumber.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            authUser()
        }
    }

    private fun authUser() {
        mPhoneNumber = binding.registerInputPhoneNumber.text.toString()
        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions.newBuilder(AUTH)
                .setPhoneNumber(mPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity as RegisterActivity)
                .setCallbacks(mCallBack)
                .build()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}