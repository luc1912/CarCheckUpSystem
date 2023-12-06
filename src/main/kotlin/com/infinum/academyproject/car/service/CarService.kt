package com.infinum.academyproject.car.service

import com.infinum.academyproject.car.controler.dto.AddCarDTO
import com.infinum.academyproject.car.controler.dto.CarDTO
import com.infinum.academyproject.car.controler.dto.CarWithCheckUpsDTO
import com.infinum.academyproject.car.repository.CarRepository
import com.infinum.academyproject.carcheckup.repository.CheckUpRepository
import com.infinum.academyproject.cardatabase.service.CarDatabaseService
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

@Service
class CarService
    (private val carRepository: CarRepository,
     private val checkUpRepository: CheckUpRepository,
     private val carDatabaseService: CarDatabaseService)
{
    fun addCar(dto: AddCarDTO): CarDTO {
        val cardb = carDatabaseService.findByManufacturerAndModel(dto.manufacturer, dto.model)
        return CarDTO(
                carRepository.save(
                    dto.toCar(cardb)
                )
        )
    }

    fun getAllCars(): List<CarDTO> = carRepository.findAll().map { CarDTO(it) }


    fun getAllCars(pageable: Pageable) = carRepository.findAll(pageable).map { CarDTO(it) }

    fun getCar(id: Long): CarWithCheckUpsDTO {
        return carRepository.findById(id)?.let {
            val checkups = checkUpRepository.findByCarOrderByPerformedAtDesc(it)
            val lastCheckup = checkups.firstOrNull()?.performedAt
            val oneYearAgo = LocalDateTime.now().minusYears(1)
            val isCheckUpNecessary: Boolean = lastCheckup == null || lastCheckup.isBefore(oneYearAgo)
            CarWithCheckUpsDTO(it, checkups, isCheckUpNecessary)
        } ?: throw IllegalArgumentException("No car with id: $id")

    }
}