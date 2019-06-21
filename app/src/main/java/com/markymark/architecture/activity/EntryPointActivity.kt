package com.markymark.architecture.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.markymark.architecture.R
import com.markymark.architecture.extensions.startActivity
import com.markymark.architecture.mvc.login.view.LoginActivityMvc
import com.markymark.architecture.mvi.login.view.LoginActivityMvi
import com.markymark.architecture.mvp.login.view.LoginActivityMvp
import com.markymark.architecture.mvvm.login.view.LoginActivityMvvm

class EntryPointActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_point_activity)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.mvvmButton)
    fun onClickMvvmLogin() {
        startActivity<LoginActivityMvvm>()
    }

    @OnClick(R.id.mviButton)
    fun onClickMviLogin() {
        startActivity<LoginActivityMvi>()
    }

    @OnClick(R.id.mvcButton)
    fun onClickMvcLogin() {
        startActivity<LoginActivityMvc>()
    }

    @OnClick(R.id.mvpButton)
    fun onClickMvpLogin() {
        startActivity<LoginActivityMvp>()
    }
}
