package com.azmetov.telegram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.azmetov.telegram.activities.RegisterActivity
import com.azmetov.telegram.databinding.ActivityMainBinding
import com.azmetov.telegram.ui.fragments.ChatsFragment
import com.azmetov.telegram.ui.objects.AppDrawer
import com.azmetov.telegram.utilites.AUTH
import com.azmetov.telegram.utilites.initFirebase
import com.azmetov.telegram.utilites.replaceActivity
import com.azmetov.telegram.utilites.replaceFragment


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolBar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields() {
        mToolBar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolBar)
        initFirebase()
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolBar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(), false)
        } else {
            replaceActivity(RegisterActivity())
        }
    }


}