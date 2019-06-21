package com.markymark.architecture.mapper

import com.markymark.architecture.mvvm.home.model.ColorPresentationModel
import com.markymark.architecture.contract.Mapper
import com.markymark.architecture.rest.backendModel.response.ColorDomainModel

/**
 * This is common and used by all architectures.
 * Otherwise, it would be in the feature package.
 */
class HomeColorMapper: Mapper<List<ColorDomainModel?>?, List<ColorPresentationModel>?> {

    override fun mapModel(dataModel: List<ColorDomainModel?>?): List<ColorPresentationModel>? {
        if (dataModel == null) return null

        return dataModel.filterNotNull().map { colorModelAdapter(it)!! }
    }

    private fun colorModelAdapter(cm: ColorDomainModel?): ColorPresentationModel? {
        if (cm == null) return null

        return ColorPresentationModel(
            cm.id,
            cm.name,
            cm.year,
            cm.color,
            cm.pantoneValue
        )
    }
}