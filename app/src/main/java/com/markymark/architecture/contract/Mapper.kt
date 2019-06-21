package com.markymark.architecture.contract

interface Mapper<DataModel, PresentationModel> {
    fun mapModel(dataModel: DataModel): PresentationModel
}