package com.example.event.vote.domain

import com.example.event.vote.adapter.out.EventVoteRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.LockModeType

@Service
class EventVoteService(
    private val eventVoteRepository: EventVoteRepository
) {

    @Transactional
    fun vote(id: UUID) {
        val eventVoteEntity = eventVoteRepository.findById(id).orElse(EventVoteEntity(id = id))
        eventVoteEntity.addCount()
        eventVoteRepository.save(eventVoteEntity)
    }

    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    fun pessimisticWriteVote(id: UUID) {
        val eventVoteEntity = eventVoteRepository.findById(id).orElse(EventVoteEntity(id = id))
        eventVoteEntity.addCount()
        eventVoteRepository.save(eventVoteEntity)
    }

    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    fun pessimisticReadVote(id: UUID) {
        val eventVoteEntity = eventVoteRepository.findById(id).orElse(EventVoteEntity(id = id))
        eventVoteEntity.addCount()
        eventVoteRepository.save(eventVoteEntity)
    }
}
