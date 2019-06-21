package com.markymark.architecture.mvi.home.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.markymark.architecture.mvi.home.presenter.HomePresenterMvi
import com.markymark.architecture.mvi.home.state.IHomeViewState
import com.markymark.architecture.adapter.ColorAdapter
import com.markymark.architecture.R
import com.markymark.architecture.extensions.hide
import com.markymark.architecture.extensions.show
import com.markymark.architecture.extensions.showShortToast
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*

class HomeActivityMvi : MviActivity<IHomeView, HomePresenterMvi>(), IHomeView {

    private val colorAdapter = ColorAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupViews()
    }

    private fun setupViews() {
        colorRv.adapter = colorAdapter
        colorRv.layoutManager = LinearLayoutManager(applicationContext)
        colorRv.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayout.VERTICAL))
    }

    override fun screenLoadedIntent(): Observable<Boolean> {
        return Observable.just(true)
    }

    override fun render(state: IHomeViewState) {
        when (state) {

            is IHomeViewState.Loading -> progressBar.show()

            is IHomeViewState.Success -> {
                progressBar.hide()
                colorAdapter.items = state.items.orEmpty().toMutableList()
            }

            is IHomeViewState.Error -> {
                progressBar.hide()
                showShortToast(state.error?.message.orEmpty())
            }
        }
    }

    override fun createPresenter(): HomePresenterMvi {
        return HomePresenterMvi()
    }
}

