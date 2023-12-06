package com.infinum.academyproject.cardatabase.service

import com.infinum.academyproject.cardatabase.entity.CarDB
import com.infinum.academyproject.cardatabase.repository.CarDataBaseRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.cache.annotation.Cacheable


@Service
class CarDatabaseService(
    private val webClient: WebClient,
    @Value("https://62d922dd5d893b27b2df0731.mockapi.io") private val baseUrl: String,
    private val carDataBaseRepository: CarDataBaseRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)
    fun getManufacturersAndModels() {
        logger.info("CURRENT THREAD {}", Thread.currentThread().name)
        (webClient
            .get()
            .uri("$baseUrl/api/v1/cars/1")
            .retrieve()
            .bodyToMono<CarResponse>()
            .flatMapIterable { carResponse -> carResponse.cars }
            .flatMapIterable { cars ->
                cars.model.map { model ->
                    val manufacturer = cars.manufacturer
                    val modelToAdd = model
                    val existingCarDb = carDataBaseRepository.findByManufacturerAndModel(manufacturer, modelToAdd)
                    if (existingCarDb == null) {
                        val carDb = CarDB(
                            manufacturer = manufacturer,
                            model = modelToAdd
                        )
                        carDataBaseRepository.save(carDb)
                    }
                }
            }
            .collectList()
            .block() ?: emptyList())
    }

    @Cacheable("manufacturer_model")
    fun findByManufacturerAndModel(manufacturer: String, model: String): CarDB {
        return carDataBaseRepository.findByManufacturerAndModel(manufacturer, model)
            ?: throw IllegalArgumentException("Invalid manufacturer or model")
    }
}