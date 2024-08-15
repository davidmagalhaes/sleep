package com.noom.interview.fullstack.sleep.domain.model

import com.noom.interview.fullstack.sleep.domain.model.type.SleepRating
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "sleep_log")
data class SleepLog (
    @Id @GeneratedValue val id: Long? = null,
    @Column(columnDefinition = "DATE") val date: LocalDate,
    @Column(columnDefinition = "TIME") val startTime: LocalTime,
    @Column(columnDefinition = "TIME") val endTime: LocalTime,
    val totalTime: Long,
    @Enumerated(EnumType.ORDINAL) val rating: SleepRating
)


