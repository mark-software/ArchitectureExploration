package com.markymark.architecture.mvi.login.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import butterknife.ButterKnife
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.view.RxView
import com.markymark.architecture.mvi.home.view.HomeActivityMvi
import com.markymark.architecture.mvi.login.model.Credentials
import com.markymark.architecture.mvi.login.presenter.LoginPresenterMvi
import com.markymark.architecture.mvi.login.state.ILoginViewState
import com.markymark.architecture.R
import com.markymark.architecture.extensions.hide
import com.markymark.architecture.extensions.show
import com.markymark.architecture.extensions.showShortToast
import com.markymark.architecture.extensions.startActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivityMvi : MviActivity<ILoginView, LoginPresenterMvi>(), ILoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
    }

    override fun loginIntent(): Observable<Credentials> {
        return RxView.clicks(loginButton)
            .map {
                Credentials(emailTv.text.toString(), passwordTv.text.toString())
            }
    }

    /**
     * Remember that each time the view is attached the most recent state is sent again.
     */
    override fun render(state: ILoginViewState) {
        when (state) {
            is ILoginViewState.NotStarted -> progressBar.hide()

            is ILoginViewState.Loading -> progressBar.show()

            is ILoginViewState.Success -> {
                progressBar.hide()
                startActivity<HomeActivityMvi>()
            }

            is ILoginViewState.Error -> {
                showShortToast(state.error?.message.orEmpty())
            }
        }
    }

    override fun createPresenter(): LoginPresenterMvi {
        return LoginPresenterMvi()
    }
}
