package com.markymark.architecture.mvc.login.view

import android.os.Bundle
import butterknife.ButterKnife
import butterknife.OnClick
import com.markymark.architecture.R
import com.markymark.architecture.extensions.hide
import com.markymark.architecture.extensions.show
import com.markymark.architecture.extensions.showShortToast
import com.markymark.architecture.extensions.startActivity
import com.markymark.architecture.mvc.base.BaseMvcActivityView
import com.markymark.architecture.mvc.base.BaseMvcView
import com.markymark.architecture.mvc.home.view.HomeActivityMvc
import com.markymark.architecture.mvc.login.controller.LoginController
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivityMvc : BaseMvcActivityView<BaseMvcView, LoginController>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
    }

    /**
     * Model has been updated so fetch the model data and use it to update the UI.
     */
    override fun modelUpdated() {
        progressBar.hide()

        val loginFailed = controller?.modelResponse?.token.isNullOrEmpty()

        if (loginFailed) {
            showShortToast(R.string.server_error)
        } else {
            startActivity<HomeActivityMvc>()
        }
    }

    @OnClick(R.id.loginButton)
    fun onClickLogin() {
        if(controller?.emailPasswordValid(emailTv.text.toString(), passwordTv.text.toString()) == false) {
            showShortToast(R.string.name_pw_empty)
            return
        }

        progressBar.show()
        controller?.login(emailTv.text.toString(), passwordTv.text.toString())
    }

    override fun createController(): LoginController {
        return LoginController()
    }
}
