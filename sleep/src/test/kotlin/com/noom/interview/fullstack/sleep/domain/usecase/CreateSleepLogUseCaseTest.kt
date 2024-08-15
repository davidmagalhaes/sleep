package com.noom.interview.fullstack.sleep.domain.usecase

import com.noom.interview.fullstack.sleep.domain.model.SleepLog
import com.noom.interview.fullstack.sleep.domain.model.type.SleepRating
import com.noom.interview.fullstack.sleep.domain.repository.SleepLogRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import java.time.LocalDate
import java.time.LocalTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CreateSleepLogUseCaseTest {

    private val sleepLogRepository = mockk<SleepLogRepository>()
    private lateinit var uut: CreateSleepLogUseCase

    @BeforeTest
    fun setUp() {
        uut = CreateSleepLogUseCase(sleepLogRepository)
    }

    @Test
    fun `when execute is invoked, then create sleep log`() {
        val sleepLogSlot = slot<SleepLog>()
        val startTime = LocalTime.now().minusHours(1)
        val endTime = LocalTime.now()

        every { sleepLogRepository.save(capture(sleepLogSlot)) } returns Unit

        uut.execute(
            LocalDate.now(),
            startTime,
            endTime,
            SleepRating.OK
        )

        assertEquals(3600, sleepLogSlot.captured.totalTime)
    }
}