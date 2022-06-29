package com.example.event.vote.domain

import com.example.event.vote.adapter.out.EventVoteRepository
import org.slf4j.Logger
import org.springframework.data.jpa.repository.Lock
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.LockModeType

@Service
class EventVoteService(
    private val eventVoteRepository: EventVoteRepository,
    private val log: Logger
) {

    @Transactional
    fun vote(id: UUID) {
        log.info("### NONE")
        val eventVoteEntity = eventVoteRepository.findById(id).orElse(EventVoteEntity(id = id))
        eventVoteEntity.addCount()
        eventVoteRepository.save(eventVoteEntity)
    }

    @Transactional
    @Lock(value = LockModeType.READ)
    fun readVote(id: UUID) {
        log.info("### READ")
        val eventVoteEntity = eventVoteRepository.findById(id).orElse(EventVoteEntity(id = id))
        eventVoteEntity.addCount()
        eventVoteRepository.save(eventVoteEntity)
    }

    @Transactional
    @Lock(value = LockModeType.WRITE)
    fun writeVote(id: UUID) {
        log.info("### WRITE")
        val eventVoteEntity = eventVoteRepository.findById(id).orElse(EventVoteEntity(id = id))
        eventVoteEntity.addCount()
        eventVoteRepository.save(eventVoteEntity)
    }

    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    fun pessimisticWriteVote(id: UUID) {
        log.info("### PESSIMISTIC_WRITE")
        val eventVoteEntity = eventVoteRepository.findById(id).orElse(EventVoteEntity(id = id))
        eventVoteEntity.addCount()
        eventVoteRepository.save(eventVoteEntity)
    }

    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    fun pessimisticReadVote(id: UUID) {
        log.info("### PESSIMISTIC_READ")
        val eventVoteEntity = eventVoteRepository.findById(id).orElse(EventVoteEntity(id = id))
        eventVoteEntity.addCount()
        eventVoteRepository.save(eventVoteEntity)
    }

    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    fun pessimisticForceIncrementVote(id: UUID) {
        log.info("### PESSIMISTIC_FORCE_INCREMENT")
        val eventVoteEntity = eventVoteRepository.findById(id).orElse(EventVoteEntity(id = id))
        eventVoteEntity.addCount()
        eventVoteRepository.save(eventVoteEntity)
    }
}
