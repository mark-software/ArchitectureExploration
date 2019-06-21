package com.markymark.architecture.mvvm.home.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.markymark.architecture.R
import com.markymark.architecture.extensions.showShortToast
import com.markymark.architecture.adapter.ColorAdapter
import com.markymark.architecture.mvvm.home.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivityMvvm : AppCompatActivity() {

    private var homeViewModel: HomeViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        setupViews()
        homeViewModel?.getColorList()
    }

    private fun setupViews() {
        val colorAdapter = ColorAdapter()
        colorRv.adapter = colorAdapter
        colorRv.layoutManager = LinearLayoutManager(applicationContext)
        colorRv.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayout.VERTICAL))

        homeViewModel?.observeColorList()?.observe(this, Observer {
            colorAdapter.items = it.toMutableList()
        })

        homeViewModel?.observeMessages()?.observe(this, Observer {
            showShortToast(it)
        })
    }
}
