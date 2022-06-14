package com.example.event.vote.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "vote")
class EventVoteEntity(

    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    val id: UUID = UUID.randomUUID(),

    var count: Long = 0L,
) {
    fun addCount() {
        count++
    }
}
