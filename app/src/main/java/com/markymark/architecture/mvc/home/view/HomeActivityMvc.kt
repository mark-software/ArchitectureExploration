package com.markymark.architecture.mvc.home.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.markymark.architecture.R
import com.markymark.architecture.mvc.base.BaseMvcActivityView
import com.markymark.architecture.mvc.base.BaseMvcView
import com.markymark.architecture.mvc.home.controller.HomeController
import com.markymark.architecture.adapter.ColorAdapter
import com.markymark.architecture.mapper.HomeColorMapper
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivityMvc : BaseMvcActivityView<BaseMvcView, HomeController>() {

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

    override fun modelUpdated() {
        //key point is that the view utilizes direct access to the model data
        val mappedData = HomeColorMapper().mapModel(controller?.modelResponse?.data)
        colorAdapter.items = mappedData.orEmpty().toMutableList()
    }

    override fun createController(): HomeController {
        return HomeController()
    }
}
