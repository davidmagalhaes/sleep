package com.noom.interview.fullstack.sleep.domain.usecase

import com.noom.interview.fullstack.sleep.domain.model.SleepLog
import com.noom.interview.fullstack.sleep.domain.repository.SleepLogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class GetLastSleepLogUseCase(@Autowired val sleepLogRepository: SleepLogRepository) {
    fun execute(): SleepLog? = sleepLogRepository
            .findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "date")))
            .firstOrNull()
}