package com.markymark.architecture.mvc.base

/**
 * This interface is more of an optimization for MVC and may not be used in all MVC implementations.
 */
interface BaseMvcView {
    fun getView() : BaseMvcView

    /**
     * Indicates that the view should fetch data from the model and then update the UI
     */
    fun modelUpdated()
}