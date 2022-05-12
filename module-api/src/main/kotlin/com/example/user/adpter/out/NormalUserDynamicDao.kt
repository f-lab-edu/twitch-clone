package com.example.user.adpter.out

import com.example.user.domain.model.NormalUserEntity
import com.example.user.domain.model.UserStatus
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Repository
internal class NormalUserDynamicDao(
    @PersistenceContext
    val entityManager: EntityManager,
) {
    fun find(email: String?, nickname: String?, status: UserStatus?): List<NormalUserEntity> {
        val jpql = findNormalUsersJpql(email, nickname, status)
        return findNormalUsersTypedQuery(jpql, email, nickname, status).resultList
    }

    private fun findNormalUsersJpql(
        email: String?,
        nickname: String?,
        status: UserStatus?,
    ): String {
        var jpql = "select n From NormalUserEntity n"
        var isFirstCondition = true

        if (email != null) {
            jpql += " where n.email = :email"
            isFirstCondition = false
        }
        if (nickname != null) {
            jpql += " ${conditionQuery(isFirstCondition)} n.nickname = :nickname"
            isFirstCondition = false
        }
        if (status != null) {
            jpql += " ${conditionQuery(isFirstCondition)} n.status = :status"
        }
        return jpql
    }

    private fun conditionQuery(firstCondition: Boolean): String = if (firstCondition) "where" else "and"

    private fun findNormalUsersTypedQuery(
        jpql: String,
        email: String?,
        nickname: String?,
        status: UserStatus?,
    ): TypedQuery<NormalUserEntity> {
        var query = entityManager.createQuery(jpql, NormalUserEntity::class.java)
        if (email != null) {
            query = query.setParameter("email", email)
        }
        if (nickname != null) {
            query = query.setParameter("nickname", nickname)
        }
        if (status != null) {
            query = query.setParameter("status", status)
        }
        return query
    }
}
