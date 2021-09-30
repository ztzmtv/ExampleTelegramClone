package com.azmetov.telegram.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azmetov.telegram.databinding.ActivityMainBinding

private lateinit var mBinding: ActivityMainBinding


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}