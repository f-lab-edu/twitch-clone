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
@Table(name = "admin_user")
internal class AdminUserEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    override val id: UUID = UUID.randomUUID(),
    @Column
    override var adminNickname: String = "",

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
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

    ) : AdminUser(id, email, password, nickname, adminNickname) {
    companion object {
        fun from(adminUser: AdminUser, normalUserEntity: NormalUserEntity): AdminUserEntity = with(adminUser) {
            AdminUserEntity(
                id = id,
                adminNickname = adminNickname,
                normalUserEntity = normalUserEntity
            )
        }
    }
}
