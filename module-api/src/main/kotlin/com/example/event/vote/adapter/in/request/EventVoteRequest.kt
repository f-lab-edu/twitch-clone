package com.example.event.vote.adapter.`in`.request

import java.util.*
import javax.persistence.LockModeType

data class EventVoteRequest(val lockmode: LockModeType, val id: UUID)
