package com.noom.interview.fullstack.sleep.domain.usecase

import com.noom.interview.fullstack.sleep.common.*
import com.noom.interview.fullstack.sleep.domain.model.aggr.SleepAvg
import com.noom.interview.fullstack.sleep.domain.model.type.SleepRating
import com.noom.interview.fullstack.sleep.domain.repository.SleepLogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalTime

@Component
class GetSleepLogAvgUseCase(@Autowired val sleepLogRepository: SleepLogRepository) {
    fun execute(): SleepAvg? {
        val sleepLogs = sleepLogRepository.findAllByDateGreaterThanEqual(
            LocalDate.now().minusMonths(1),
            Pageable.unpaged(Sort.by(Sort.Direction.DESC, "date"))
        )
        val ratings = mapOf(
            SleepRating.BAD to sleepLogs.count { it.rating == SleepRating.BAD },
            SleepRating.OK to sleepLogs.count { it.rating == SleepRating.OK },
            SleepRating.GOOD to sleepLogs.count { it.rating == SleepRating.GOOD },
        )
        return if (sleepLogs.isNotEmpty())
            SleepAvg(
                sleepLogs.sumOf { it.totalTime }.div(sleepLogs.size.toLong()),
                Pair(sleepLogs.map { it.startTime }.timeAvg(), sleepLogs.map { it.endTime }.timeAvg()),
                Pair(sleepLogs.minOf { it.date }, sleepLogs.maxOf { it.date }),
                ratings
            )
        else null
    }
}