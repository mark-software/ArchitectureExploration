package com.markymark.architecture.mvp.home.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.markymark.architecture.R
import com.markymark.architecture.extensions.showShortToast
import com.markymark.architecture.mvp.home.presenter.HomePresenter
import com.markymark.architecture.adapter.ColorAdapter
import com.markymark.architecture.mvvm.home.model.ColorPresentationModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivityMvp : MvpActivity<IHomeView, HomePresenter>(), IHomeView {

    private val colorAdapter = ColorAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupViews()
        getPresenter().updateColorData()
    }

    private fun setupViews() {
        colorRv.adapter = colorAdapter
        colorRv.layoutManager = LinearLayoutManager(applicationContext)
        colorRv.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayout.VERTICAL))
    }

    override fun setAdapterItems(items: List<ColorPresentationModel>) {
        colorAdapter.items = items.toMutableList()
    }

    override fun showToast(text: String) {
        showShortToast(text)
    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }
}
