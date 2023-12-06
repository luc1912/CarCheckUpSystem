package com.infinum.academyproject.car.controler.dto

import com.infinum.academyproject.car.entity.Car
import com.infinum.academyproject.cardatabase.entity.CarDB
import java.time.LocalDate

data class AddCarDTO(
    val dateWhenAdded: LocalDate,
    val manufacturer: String,
    val model: String,
    val productionYear: Int,
    val vin: String,
){
    fun toCar(carDB: CarDB) = Car(
        dateWhenAdded = dateWhenAdded,
        cardb = carDB,
        productionYear = productionYear,
        vin = vin)
}