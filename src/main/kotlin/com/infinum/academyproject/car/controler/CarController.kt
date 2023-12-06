package com.infinum.academyproject.car.controler

import com.infinum.academyproject.car.controler.dto.AddCarDTO
import com.infinum.academyproject.car.service.CarService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestBody

@RequestMapping("/cars")
@Controller
class CarController(
    private val carService: CarService
) {

    @GetMapping
    fun getAllCars() = ResponseEntity.ok(
        carService.getAllCars()
    )

    @GetMapping("/paged")
    fun getAllCars(pageable: Pageable) = ResponseEntity.ok(
        carService.getAllCars(pageable)
    )

    @PostMapping
    fun addCar(@RequestBody car: AddCarDTO) = ResponseEntity.ok(
        carService.addCar(car)
    )

    @GetMapping("/{id}")
    fun getCarById(@PathVariable id: Long) = ResponseEntity.ok(
        carService.getCar(id)
    )
}