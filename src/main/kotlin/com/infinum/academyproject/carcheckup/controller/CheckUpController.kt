package com.infinum.academyproject.carcheckup.controller

import com.infinum.academyproject.carcheckup.controller.dto.AddCheckUpDTO
import com.infinum.academyproject.carcheckup.controller.dto.CheckUpFilter
import com.infinum.academyproject.carcheckup.service.CheckUpService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RequestMapping("/checkups")
@Controller
class CheckUpController(private val checkUpService: CheckUpService) {
    @GetMapping
    fun getAllCheckUps() = ResponseEntity.ok(
        checkUpService.getAllCheckups()
    )

    @GetMapping("/search/paged")
    fun getAllCheckUps(
        @RequestParam(required = false) performedAt: LocalDateTime?,
        @RequestParam(required = false) worker: String?,
        @RequestParam(required = false) price: Int?,
        @RequestParam(required = false) carId: Long?,
        pageable: Pageable
    ): ResponseEntity<Any> {
        val filter = CheckUpFilter(performedAt, worker, price, carId)

        return ResponseEntity.ok(checkUpService.getAllCheckUps(filter, pageable))
    }

    @PostMapping
    fun addCheckUp(@RequestBody checkup: AddCheckUpDTO) = ResponseEntity.ok(
        checkUpService.addCheckUp(checkup)
    )

    @GetMapping("/manufacturers")
    fun getAllCheckUpsForManufacturers() = ResponseEntity.ok(
        checkUpService.getAllCheckUpsForManufacturers()
    )

}