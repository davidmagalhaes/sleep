package com.noom.interview.fullstack.sleep.presentation.controller

import com.google.gson.Gson
import com.noom.interview.fullstack.sleep.common.Constants
import com.noom.interview.fullstack.sleep.domain.model.type.SleepRating
import com.noom.interview.fullstack.sleep.domain.usecase.CreateSleepLogUseCase
import com.noom.interview.fullstack.sleep.domain.usecase.GetLastSleepLogUseCase
import com.noom.interview.fullstack.sleep.domain.usecase.GetSleepLogAvgUseCase
import com.noom.interview.fullstack.sleep.presentation.formatter.SleepLogFormatter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalTime

@RestController
class SleepLogController {

    @Autowired
    lateinit var getLastSleepLogUseCase: GetLastSleepLogUseCase

    @Autowired
    lateinit var getSleepLogAvgUseCase: GetSleepLogAvgUseCase

    @Autowired
    lateinit var createSleepLogUseCase: CreateSleepLogUseCase

    @Autowired
    lateinit var sleepLogFormatter: SleepLogFormatter

    @PostMapping("/sleep/create")
    fun create(date: String?, startTime: String, endTime: String, rating: String) : ResponseEntity<Any> {
        createSleepLogUseCase.execute(
            if (date != null) LocalDate.parse(date, Constants.DATE_FORMAT) else LocalDate.now(),
            LocalTime.parse(startTime, Constants.TIME_FORMAT),
            LocalTime.parse(endTime, Constants.TIME_FORMAT),
            SleepRating.valueOf(rating)
        )

        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/sleep/last")
    fun getLast() : ResponseEntity<String> {
        val sleepLog = getLastSleepLogUseCase.execute()

        return sleepLog?.let {
            ResponseEntity(Gson().toJson(sleepLogFormatter.formatSleepLog(it)), HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/sleep/avg")
    fun getAvg() : ResponseEntity<String> {
        val sleepAvg = getSleepLogAvgUseCase.execute()

        return sleepAvg?.let {
            ResponseEntity(Gson().toJson(sleepLogFormatter.formatSleepAvg(it)), HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }
}