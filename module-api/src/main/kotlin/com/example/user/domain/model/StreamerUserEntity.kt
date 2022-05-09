package com.example.user.domain.model

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table


@Entity
@Table(name = "streamer_user")
internal class StreamerUserEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    override val id: UUID = UUID.randomUUID(),
    @Column
    override var streamerNickname: String = "",
    @Column
    override var subscriptionCost: Int = 0,
    @Column
    @Enumerated(EnumType.STRING)
    override val streamerStatus: StreamerUserStatus = StreamerUserStatus.PENDING,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @MapsId("id")
    @JoinColumn(name = "id")
    val normalUserEntity: NormalUserEntity = NormalUserEntity(),

    @Transient
    override val email: String = "",
    @Transient
    override var password: String = "",
    @Transient
    override var status: UserStatus = UserStatus.SUSPENDED,
    @Transient
    override var nickname: String = "",

    ) : StreamerUser(id, email, password, nickname, streamerNickname, subscriptionCost) {
    companion object {
        fun from(streamerUser: StreamerUser, normalUserEntity: NormalUserEntity): StreamerUserEntity = with(streamerUser) {
            StreamerUserEntity(
                id = id,
                streamerNickname = streamerNickname,
                subscriptionCost = subscriptionCost,
                streamerStatus = streamerStatus,
                normalUserEntity = normalUserEntity
            )
        }

        fun fromList(streamerUsers: List<StreamerUser>, normalUserEntityMap: Map<UUID, NormalUserEntity>) : List<StreamerUserEntity> {
            val streamerUserEntities = mutableListOf<StreamerUserEntity>()
            streamerUsers
                .asSequence()
                .map { streamerUser -> normalUserEntityMap[streamerUser.id]?.let { from(streamerUser, it) } }
                .forEach { streamerUserEntity -> streamerUserEntity?.let { streamerUserEntities.add(it) } }

            return streamerUserEntities.toList()
        }
    }
}
