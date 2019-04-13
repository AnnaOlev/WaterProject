package com.example.waterBalance

class Person {

    var DailyQuantity: Int = 0
    var NormalQuantity: Int = 0

    constructor() {
        this.DailyQuantity = 0
        this.NormalQuantity = 0
    }

    constructor(theDailyQuantity: Int, theNoramlQuantity: Int) {
        this.DailyQuantity = theDailyQuantity
        this.NormalQuantity = theNoramlQuantity
    }
}