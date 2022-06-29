package com.example.event.vote.adapter.out

import com.example.event.vote.domain.EventVoteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EventVoteRepository : JpaRepository<EventVoteEntity, UUID>
