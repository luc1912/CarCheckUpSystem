package com.infinum.academyproject.car.controler.dto

import com.infinum.academyproject.car.entity.Car
import java.time.LocalDate

data class CarDTO(
    val id:Long,
    val dateWhenAdded: LocalDate,
    val manufacturer: String,
    val model: String,
    val productionYear: Int,
    val vin: String,
){
    constructor(car: Car): this (
        car.id,
        car.dateWhenAdded,
        car.cardb.manufacturer,
        car.cardb.model,
        car.productionYear,
        car.vin)
}