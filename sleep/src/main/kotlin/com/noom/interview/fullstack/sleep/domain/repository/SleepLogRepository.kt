package com.noom.interview.fullstack.sleep.domain.repository

import com.noom.interview.fullstack.sleep.domain.model.SleepLog
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.time.LocalDate

interface SleepLogRepository: PagingAndSortingRepository<SleepLog, Long> {
    fun save(log: SleepLog)
    fun findAllByDateGreaterThanEqual(date: LocalDate, pageable: Pageable): List<SleepLog>
}