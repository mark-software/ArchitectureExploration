package com.markymark.architecture.mvp.login.view

import android.os.Bundle
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.markymark.architecture.R
import com.markymark.architecture.extensions.*
import com.markymark.architecture.mvp.home.view.HomeActivityMvp
import com.markymark.architecture.mvp.login.presenter.LoginPresenterMvp
import kotlinx.android.synthetic.main.activity_login.*

//Passive view variation of MVP
class LoginActivityMvp : MvpActivity<ILoginView, LoginPresenterMvp>(), ILoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.loginButton)
    fun onClickLogin() {
        getPresenter().login(emailTv.text.toString(), passwordTv.text.toString())
    }

    override fun showToast(text: String) {
        showShortToast(text)
    }

    override fun showToast(intRes: Int) {
        showShortToast(intRes)
    }

    override fun launchHomeScreenActivity() {
        startActivity<HomeActivityMvp>()
    }

    override fun hideKeyboard() {
        loginButton.hideKeyboard()
    }

    override fun showProgress(show: Boolean) {
        if(show) {
            progressBar.show()
        }
        else {
            progressBar.hide()
        }
    }

    override fun createPresenter(): LoginPresenterMvp {
        return LoginPresenterMvp()
    }
}
