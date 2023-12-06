package com.infinum.academyproject.carcheckup.controller.dto

import java.time.LocalDateTime

data class CheckUpFilter (
    val performedAt: LocalDateTime? = null,
    val worker: String? = null,
    val price: Int? = null,
    val carId: Long? = null
)