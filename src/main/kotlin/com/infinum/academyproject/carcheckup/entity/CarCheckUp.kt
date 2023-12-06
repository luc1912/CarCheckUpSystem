package com.infinum.academyproject.carcheckup.entity

import com.infinum.academyproject.car.entity.Car
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "carcheckup")
class CarCheckUp(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carcheckup_seq")
    @SequenceGenerator(name = "carcheckup_seq", sequenceName = "carcheckup_seq", allocationSize = 1)
    var id: Long = 0,

    @Column(name = "performedat")
    var performedAt: LocalDateTime,

    @Column(name = "worker")
    var worker: String,

    @Column(name = "price")
    var price: Float,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carid")
    var car: Car
)
