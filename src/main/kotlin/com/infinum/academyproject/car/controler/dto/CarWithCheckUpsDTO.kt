package com.infinum.academyproject.car.controler.dto

import com.infinum.academyproject.car.entity.Car
import com.infinum.academyproject.carcheckup.controller.dto.CheckUpDTO
import com.infinum.academyproject.carcheckup.entity.CarCheckUp
import java.time.LocalDate

class CarWithCheckUpsDTO (
    val dateWhenAdded: LocalDate,
    val productionYear: Int,
    val manufacturer: String,
    val model: String,
    val vin: String,
    val checkups: List<CheckUpDTO>,
    val isCheckUpNecessary: Boolean
){
    constructor(car: Car, checkups: List<CarCheckUp>, isCheckUpNecessary: Boolean) : this(
        car.dateWhenAdded,
        car.productionYear,
        car.cardb.manufacturer,
        car.cardb.model,
        car.vin,
        checkups.map { CheckUpDTO(it) },
        isCheckUpNecessary
    )
}

