package com.noom.interview.fullstack.sleep.presentation.formatter

import com.noom.interview.fullstack.sleep.common.Constants.DATE_FORMAT
import com.noom.interview.fullstack.sleep.common.Constants.TIME_FORMAT
import com.noom.interview.fullstack.sleep.domain.model.SleepLog
import com.noom.interview.fullstack.sleep.domain.model.aggr.SleepAvg
import com.noom.interview.fullstack.sleep.domain.model.type.SleepRating
import com.noom.interview.fullstack.sleep.presentation.model.PeriodFormatted
import com.noom.interview.fullstack.sleep.presentation.model.SleepLogAvgFormatted
import com.noom.interview.fullstack.sleep.presentation.model.SleepLogFormatted
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component
class SleepLogFormatter {
    fun formatSleepLog(sleepLog: SleepLog) = sleepLog.run {
        SleepLogFormatted (
            date = date.format(DATE_FORMAT),
            interval = PeriodFormatted(startTime.format(TIME_FORMAT), endTime.format(TIME_FORMAT)),
            totalTime = LocalTime.ofSecondOfDay(totalTime).format(TIME_FORMAT),
            rating = rating.name
        )
    }

    fun formatSleepAvg(sleepAvg: SleepAvg) = sleepAvg.run {
        SleepLogAvgFormatted (
            avgTime = LocalTime.ofSecondOfDay(avgTime).format(TIME_FORMAT),
            avgInterval = avgInterval
                .let { PeriodFormatted(it.first.format(TIME_FORMAT), it.second.format(TIME_FORMAT)) },
            period = PeriodFormatted(period.first.format(DATE_FORMAT), period.second.format(DATE_FORMAT)),
            ratingsCount = mapOf(
                SleepRating.BAD.name to (ratings[SleepRating.BAD] ?: 0),
                SleepRating.OK.name to (ratings[SleepRating.OK] ?: 0),
                SleepRating.GOOD.name to (ratings[SleepRating.GOOD] ?: 0)
            )
        )
    }
}