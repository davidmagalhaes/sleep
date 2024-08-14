package com.noom.interview.fullstack.sleep.domain.model.aggr

import com.noom.interview.fullstack.sleep.domain.model.type.SleepRating
import java.time.LocalDate
import java.time.LocalTime

data class SleepAvg (
    val avgTime: Long,
    val avgInterval: Pair<LocalTime, LocalTime>,
    val period: Pair<LocalDate, LocalDate>,
    val ratings: Map<SleepRating, Int>,
)