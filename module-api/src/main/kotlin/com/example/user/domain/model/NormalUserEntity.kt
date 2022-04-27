package com.example.user.domain.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "normal_user")
class NormalUserEntity(
    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    @Type(type = "uuid-char")
    override val id: UUID,
    @Column
    override val email: String,
    @Column
    override var password: String,
    @Column
    @Enumerated(EnumType.STRING)
    override var status: UserStatus,
    @Column
    override var nickname: String
) : User.Editor by NormalUser(id, email, password, nickname) {

    companion object {
        fun from(normalUser: NormalUser): NormalUserEntity = with(normalUser) {
            NormalUserEntity(
                id = id,
                email = email,
                password = password,
                status = status,
                nickname = nickname,
            )
        }
    }
}
