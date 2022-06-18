package com.example.event.vote.adapter.`in`

import com.example.event.vote.adapter.`in`.request.EventVoteRequest
import com.example.event.vote.domain.EventVoteService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.LockModeType
import javax.validation.Valid

@RestController
class EventVoteController(
    private val eventVoteService: EventVoteService,
) {

    @PostMapping(path = ["/api/event/vote"])
    fun vote(@Valid @RequestBody req: EventVoteRequest) {
        when (req.lockmode) {
            LockModeType.READ -> eventVoteService.readVote(req.id)
            LockModeType.WRITE -> eventVoteService.writeVote(req.id)
            LockModeType.PESSIMISTIC_WRITE -> eventVoteService.pessimisticWriteVote(req.id)
            LockModeType.PESSIMISTIC_READ -> eventVoteService.pessimisticReadVote(req.id)
            LockModeType.PESSIMISTIC_FORCE_INCREMENT -> eventVoteService.pessimisticForceIncrementVote(req.id)
            else -> eventVoteService.vote(req.id)
        }
    }
}
