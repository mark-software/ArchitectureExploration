package com.markymark.architecture.contract

interface AdapterItem<VM> {
    fun setData(viewModel: VM)
    fun reset()//used to avoid bugs in long lists when views are recycled
}
