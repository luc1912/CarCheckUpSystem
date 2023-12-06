package com.infinum.academyproject.carcheckup.controller.dto

import com.infinum.academyproject.carcheckup.entity.CarCheckUp
import java.time.LocalDateTime

class CheckUpDTO(
    var id: Long = 0,

    val performedAt: LocalDateTime,

    val worker: String,

    val price: Float,

){
    constructor(carCheckUp: CarCheckUp): this(
        carCheckUp.id,
        carCheckUp.performedAt,
        carCheckUp.worker,
        carCheckUp.price)
}