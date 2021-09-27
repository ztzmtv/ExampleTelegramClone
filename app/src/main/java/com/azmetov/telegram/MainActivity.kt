package com.azmetov.telegram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.azmetov.telegram.databinding.ActivityMainBinding
import com.azmetov.telegram.ui.objects.AppDrawer
import com.azmetov.telegram.ui.fragments.ChatsFragment


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
    }

    private fun initFunc() {
        setSupportActionBar(mToolBar)
        mAppDrawer.create()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.dataContainer, ChatsFragment())
            .commit()

    }


}