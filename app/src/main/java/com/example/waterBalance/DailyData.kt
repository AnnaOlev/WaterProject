package com.example.waterBalance

import java.util.*

class DailyData {

    var dailyQuantity: Double = 0.0
    var partOfNormalQuantity: Float = 0.0F
    var date: String = Date().toString()

    constructor() {
        this.dailyQuantity = 0.0
        this.partOfNormalQuantity = 0.0F
        this.date = Date().toString()
    }

    constructor(DailyQuantity: Double, PartOfNoramlQuantity: Float, Date: String) {
        this.dailyQuantity = DailyQuantity
        this.partOfNormalQuantity = PartOfNoramlQuantity
        this.date = Date
    }

    override fun toString() : String {
        return String.format("$date было выпито %.0f мл, что составляет %.3f от нормы.", dailyQuantity,partOfNormalQuantity)
    }
}