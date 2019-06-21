package com.markymark.architecture.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import butterknife.ButterKnife
import com.markymark.architecture.R
import com.markymark.architecture.contract.AdapterItem
import com.markymark.architecture.mvvm.home.model.ColorPresentationModel
import kotlinx.android.synthetic.main.color_row_view.view.*

//List row element used by all architectures
class ColorRowView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : FrameLayout(context, attrs, defStyle), AdapterItem<ColorPresentationModel> {


    init {
        inflate(context, R.layout.color_row_view, this)
        ButterKnife.bind(this)

        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun setData(viewModel: ColorPresentationModel) {
        name.text = viewModel.name
        parentContainer.setBackgroundColor(Color.parseColor(viewModel.color))
    }

    override fun reset() {

    }
}
