package com.daniyal.basicappimpl.ui.home

import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.daniyal.basicappimpl.R
import com.daniyal.basicappimpl.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        glide.load(R.mipmap.ic_launcher).into(image)

    }
}