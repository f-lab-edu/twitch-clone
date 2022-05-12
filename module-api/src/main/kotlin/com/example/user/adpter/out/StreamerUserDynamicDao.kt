package com.example.user.adpter.out

import com.example.user.domain.model.StreamerUser
import com.example.user.domain.model.StreamerUserEntity
import com.example.user.domain.model.StreamerUserStatus
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery

@Repository
internal class StreamerUserDynamicDao(
    @PersistenceContext
    val entityManager: EntityManager,
) {
    fun findStreamers(streamerNickname: String?, streamerUserStatus: StreamerUserStatus?): List<StreamerUser> {
        val jpql = findStreamersJpql(streamerNickname, streamerUserStatus)
        return findStreamersTypedQuery(jpql, streamerNickname, streamerUserStatus).resultList
    }

    private fun findStreamersJpql(
        streamerNickname: String?,
        streamerUserStatus: StreamerUserStatus?,
    ): String {
        var jpql = "select s From StreamerUserEntity s"
        var isFirstCondition = true
        if (streamerNickname != null) {
            jpql += " where s.streamerNickname = :streamerNickname"
            isFirstCondition = false
        }
        if (streamerUserStatus != null) {
            jpql += " ${conditionQuery(isFirstCondition)} s.streamerUserStatus = :streamerUserStatus"
        }
        return jpql
    }

    private fun conditionQuery(firstCondition: Boolean): String = if (firstCondition) "where" else "and"

    private fun findStreamersTypedQuery(
        jpql: String,
        streamerNickname: String?,
        streamerUserStatus: StreamerUserStatus?,
    ): TypedQuery<StreamerUserEntity> {
        var query = entityManager.createQuery(jpql, StreamerUserEntity::class.java)
        if (streamerNickname != null) {
            query = query.setParameter("streamerNickname", streamerNickname)
        }
        if (streamerUserStatus != null) {
            query = query.setParameter("streamerUserStatus", streamerUserStatus)
        }
        return query
    }
}
