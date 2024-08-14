package com.noom.interview.fullstack.sleep.domain.usecase

import com.noom.interview.fullstack.sleep.domain.model.SleepLog
import com.noom.interview.fullstack.sleep.domain.model.type.SleepRating
import com.noom.interview.fullstack.sleep.domain.repository.SleepLogRepository
import com.noom.interview.fullstack.sleep.common.toSeconds
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalTime

@Component
class CreateSleepLogUseCase(@Autowired val sleepLogRepository: SleepLogRepository) {
    fun execute(date: LocalDate, startTime: LocalTime, endTime: LocalTime, rating: SleepRating) {
        sleepLogRepository.save(
            SleepLog(
                date = date,
                startTime = startTime,
                endTime = endTime,
                totalTime = endTime.toSeconds() - startTime.toSeconds(),
                rating = rating
            )
        )
    }
}