package com.infinum.academyproject.carcheckup.service

import com.infinum.academyproject.car.repository.CarRepository
import com.infinum.academyproject.carcheckup.controller.dto.AddCheckUpDTO
import com.infinum.academyproject.carcheckup.controller.dto.CheckUpDTO
import com.infinum.academyproject.carcheckup.controller.dto.CheckUpFilter
import com.infinum.academyproject.carcheckup.entity.CarCheckUp
import com.infinum.academyproject.carcheckup.repository.CheckUpRepository
import com.infinum.academyproject.carcheckup.repository.CheckUpSpecifications
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

@Service
class CheckUpService(private val carCheckUpRepository: CheckUpRepository,
                     private val carRepository: CarRepository
) {

    fun addCheckUp(dto: AddCheckUpDTO): CheckUpDTO {
        val checkup = dto.toCheckUp { carid ->
            carRepository.findById(carid)
                ?: throw IllegalArgumentException("No car with id: $carid")

        }
        return CheckUpDTO(carCheckUpRepository.save(checkup))
    }

    fun getAllCheckups() = carCheckUpRepository.findAll().map { CheckUpDTO(it) }

    fun getAllCheckUps(filter: CheckUpFilter, pageable: Pageable): Page<CarCheckUp> {
        val spec = CheckUpSpecifications.toSpecification(filter)
        return carCheckUpRepository.findAll(spec, pageable)
    }

    fun getAllCheckUpsForManufacturers(): MutableMap<String, Int> {
        val checkUpsByManufacturer = mutableMapOf<String, Int>()

        val checkups = carCheckUpRepository.findAll()

        for (checkup in checkups) {
            val manufacturerName = checkup.car.cardb.manufacturer

            checkUpsByManufacturer[manufacturerName] = checkUpsByManufacturer.getOrDefault(manufacturerName, 0) + 1
        }

        return checkUpsByManufacturer
    }

}