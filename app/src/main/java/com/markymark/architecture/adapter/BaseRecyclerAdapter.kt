package com.markymark.architecture.adapter

import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.PublishRelay
import com.markymark.architecture.contract.AdapterItem
import com.markymark.architecture.view.ViewHolder
import io.reactivex.Observable

abstract class BaseRecyclerAdapter<VM, V : AdapterItem<VM>> : RecyclerView.Adapter<ViewHolder>() {

    var items: MutableList<VM> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val onClickRelay: PublishRelay<Pair<Int, VM>> = PublishRelay.create()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val view = holder.itemView as V
        holder.itemView.setOnClickListener { onClickRelay.accept(Pair(holder.adapterPosition, item)) }

        view.reset()
        view.setData(item)
    }

    fun onClickObserver(): Observable<Pair<Int, VM>> = onClickRelay.hide()
}