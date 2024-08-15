package com.noom.interview.fullstack.sleep.common

import java.time.LocalTime

fun List<LocalTime>.timeAvg(): LocalTime =
    LocalTime.ofSecondOfDay( map { it.toSecondOfDay().toLong() }.average().toLong() )