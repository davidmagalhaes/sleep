package com.noom.interview.fullstack.sleep

import com.noom.interview.fullstack.sleep.domain.repository.SleepLogRepository
import com.noom.interview.fullstack.sleep.domain.usecase.GetLastSleepLogUseCase
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class GetLastSleepLogUseCaseTest {

    private val sleepLogRepository = mockk<SleepLogRepository>()
    private lateinit var uut: GetLastSleepLogUseCase

    @BeforeTest
    fun setUp() {
        uut = GetLastSleepLogUseCase(sleepLogRepository)
    }

    @Test
    fun `given an existing sleep log, when execute is invoke, then return the sleep log`() {
        every { sleepLogRepository.findAllByDateGreaterThanEqual(any(), any()) } returns mockk()

        val sleepLog = uut.execute()
        assertNotNull(sleepLog)
    }

    @Test
    fun `given non existing sleep log, when execute is invoked, then return null`() {
        val sleepLog = uut.execute()
        assertNull(sleepLog)
    }
}