package com.infinum.academyproject.carcheckup.controller.dto

import com.infinum.academyproject.car.entity.Car
import com.infinum.academyproject.carcheckup.entity.CarCheckUp
import java.time.LocalDateTime

data class AddCheckUpDTO(
    val performedAt: LocalDateTime,

    val worker: String,

    val price: Float,

    val carid: Long,

    ){
    fun toCheckUp(carFetcher: (Long) -> Car): CarCheckUp {
        return CarCheckUp(
            performedAt = performedAt,
            worker = worker,
            price = price,
            car = carFetcher.invoke(carid)
        )
    }
}