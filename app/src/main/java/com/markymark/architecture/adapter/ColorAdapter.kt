package com.markymark.architecture.adapter

import android.view.ViewGroup
import com.markymark.architecture.mvvm.home.model.ColorPresentationModel
import com.markymark.architecture.view.ColorRowView
import com.markymark.architecture.view.ViewHolder

/**
 * This adapter is shared by multiple classes
 */
class ColorAdapter: BaseRecyclerAdapter<ColorPresentationModel, ColorRowView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ColorRowView(parent.context))
    }
}