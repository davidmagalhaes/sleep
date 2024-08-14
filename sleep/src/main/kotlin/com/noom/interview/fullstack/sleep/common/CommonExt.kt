package com.noom.interview.fullstack.sleep.common

import java.time.LocalTime

fun LocalTime.toSeconds() = (hour * 3600 + minute * 60 + second).toLong()

fun List<Pair<Long, Long>>.avgPair(): Pair<Long, Long> =
    fold(Pair(0L, 0L)) { acc, pair -> Pair(acc.first + pair.first, acc.second + pair.second) }
    .let { Pair(it.first / size, it.second / size) }