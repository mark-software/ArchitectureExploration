package com.markymark.architecture.mvvm.login.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import com.markymark.architecture.R
import com.markymark.architecture.extensions.hideKeyboard
import com.markymark.architecture.extensions.showShortToast
import com.markymark.architecture.extensions.startActivity
import com.markymark.architecture.mvvm.home.view.HomeActivityMvvm
import com.markymark.architecture.mvvm.login.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivityMvvm : AppCompatActivity() {

    private var loginViewModel: LoginViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        setupView()
    }

    private fun setupView() {
        loginViewModel?.observeLoading()?.observe(this, Observer { loading ->
            progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        })

        loginViewModel?.observeLoginState()?.observe(this, Observer { state ->
            showShortToast(state.message)

            if (state.loginSuccess) {
                startActivity<HomeActivityMvvm>()
            }
        })

        loginViewModel?.observeMessages()?.observe(this, Observer {
            showShortToast(it)
        })
    }

    @OnClick(R.id.loginButton)
    fun onClickLogin() {
        emailTv.hideKeyboard()
        loginViewModel?.login(emailTv.text.toString(), passwordTv.text.toString())
    }
}
