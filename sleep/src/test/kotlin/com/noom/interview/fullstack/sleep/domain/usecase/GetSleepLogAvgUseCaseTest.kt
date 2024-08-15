package com.noom.interview.fullstack.sleep.domain.usecase

import com.noom.interview.fullstack.sleep.common.timeAvg
import com.noom.interview.fullstack.sleep.domain.model.SleepLog
import com.noom.interview.fullstack.sleep.domain.model.type.SleepRating
import com.noom.interview.fullstack.sleep.domain.repository.SleepLogRepository
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDate
import java.time.LocalTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetSleepLogAvgUseCaseTest {
    private val sleepLogRepository = mockk<SleepLogRepository>()
    private lateinit var uut: GetSleepLogAvgUseCase

    @BeforeTest
    fun setUp() {
        uut = GetSleepLogAvgUseCase(sleepLogRepository)
    }

    @Test
    fun `given non existing sleep log, when execute is invoked, then return null`() {
        every { sleepLogRepository.findAllByDateGreaterThanEqual(any(), any()) } returns emptyList()
        assertNull(uut.execute())
    }

    @Test
    fun `given existing sleep log, when execute is invoked, then return a sleep avg for last month`() {
        val date = LocalDate.now()
        val dateOneMonth = date.minusMonths(1)
        val startTime = LocalTime.now()
        val startTime2 = LocalTime.now().minusHours(10)
        val endTime = startTime.plusHours(1)
        val endTime2 = startTime2.plusHours(6)

        every { sleepLogRepository.findAllByDateGreaterThanEqual(any(), any()) } returns listOf(
            SleepLog(null, date, startTime, endTime, 3600, SleepRating.OK),
            SleepLog(null, dateOneMonth, startTime2, endTime2, 3600, SleepRating.BAD)
        )

        val sleepAvg = uut.execute()

        assertEquals(date, sleepAvg?.period?.second)
        assertEquals(dateOneMonth, sleepAvg?.period?.first)
        assertEquals(
            listOf(startTime, startTime2).timeAvg().toSecondOfDay(),
            sleepAvg?.avgInterval?.first?.toSecondOfDay()
        )
        assertEquals(
            listOf(endTime, endTime2).timeAvg().toSecondOfDay(),
            sleepAvg?.avgInterval?.second?.toSecondOfDay()
        )
        assertEquals(0, sleepAvg?.ratings?.get(SleepRating.GOOD))
        assertEquals(1, sleepAvg?.ratings?.get(SleepRating.OK))
        assertEquals(1, sleepAvg?.ratings?.get(SleepRating.BAD))
    }
}