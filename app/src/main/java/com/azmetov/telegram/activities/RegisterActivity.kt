package com.azmetov.telegram.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.azmetov.telegram.R
import com.azmetov.telegram.databinding.ActivityRegisterBinding
import com.azmetov.telegram.ui.fragments.EnterPhoneNumberFragment
import com.azmetov.telegram.utilites.replaceFragment

private lateinit var mBinding: ActivityRegisterBinding
private lateinit var mToolBar: Toolbar


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        mToolBar = mBinding.registerToolbar
        setSupportActionBar(mToolBar)
        title = getString(R.string.register_title_your_phone)

        replaceFragment(EnterPhoneNumberFragment(), false)
    }
}