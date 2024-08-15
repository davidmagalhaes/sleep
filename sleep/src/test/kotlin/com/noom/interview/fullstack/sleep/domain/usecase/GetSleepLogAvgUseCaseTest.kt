package com.noom.interview.fullstack.sleep

import com.noom.interview.fullstack.sleep.domain.model.SleepLog
import com.noom.interview.fullstack.sleep.domain.model.type.SleepRating
import com.noom.interview.fullstack.sleep.domain.repository.SleepLogRepository
import com.noom.interview.fullstack.sleep.domain.usecase.GetSleepLogAvgUseCase
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.time.LocalTime
import kotlin.test.BeforeTest
import kotlin.test.Test
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
        assertNull(uut.execute())
    }

    @Test
    fun `given existing sleep log, when execute is invoked, then return a sleep avg for last month`() {
        val date = LocalDate.now()
        val dateOneMonth = date.minusMonths(1)
        val dateOneMonthOneDay = dateOneMonth.minusDays(1)

        every { sleepLogRepository.findAll(any<Pageable>()) } returns listOf(
            SleepLog(null, date, LocalTime.now().minusHours(1), LocalTime.now(), 3600, SleepRating.OK),
            SleepLog(null, date, LocalTime.now().minusHours(1), LocalTime.now(), 3600, SleepRating.BAD),
            SleepLog(null, date, LocalTime.now().minusHours(1), LocalTime.now(), 3600, SleepRating.GOOD)
        )
    }
}