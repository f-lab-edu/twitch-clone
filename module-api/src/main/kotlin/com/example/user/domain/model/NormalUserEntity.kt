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
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    override val id: UUID = UUID.randomUUID(),
    @Column
    override val email: String = "",
    @Column
    override var password: String = "",
    @Column
    @Enumerated(EnumType.STRING)
    override var status: UserStatus = UserStatus.SUSPENDED,
    @Column
    override var nickname: String = ""
) : NormalUser(id, email, password, nickname){

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
