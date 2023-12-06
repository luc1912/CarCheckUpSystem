package com.infinum.academyproject.cardatabase.service

import com.fasterxml.jackson.annotation.JsonProperty

data class CarResponse(
    @JsonProperty("cars") val cars: List<CarManufacturer>
)

data class CarManufacturer(
    @JsonProperty("manufacturer") val manufacturer: String,
    @JsonProperty("models") val model: List<String>
)
