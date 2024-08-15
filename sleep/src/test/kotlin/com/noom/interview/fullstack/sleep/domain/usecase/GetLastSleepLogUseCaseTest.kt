package com.noom.interview.fullstack.sleep.domain.usecase

import com.noom.interview.fullstack.sleep.domain.model.SleepLog
import com.noom.interview.fullstack.sleep.domain.repository.SleepLogRepository
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
        val pageMock = mockk<Page<SleepLog>>()

        every { pageMock.iterator() } returns mutableListOf(mockk<SleepLog>()).listIterator()
        every { sleepLogRepository.findAll(any<Pageable>()) } returns pageMock

        val sleepLog = uut.execute()
        assertNotNull(sleepLog)
    }

    @Test
    fun `given non existing sleep log, when execute is invoked, then return null`() {
        val pageMock = mockk<Page<SleepLog>>()

        every { pageMock.iterator() } returns mutableListOf<SleepLog>().listIterator()
        every { sleepLogRepository.findAll(any<Pageable>()) } returns pageMock

        val sleepLog = uut.execute()
        assertNull(sleepLog)
    }
}