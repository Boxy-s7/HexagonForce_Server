package de.jamilo.hexagon.server.persistence.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import de.jamilo.hexagon.server.domain.session.SessionRecord;
import de.jamilo.hexagon.server.persistence.entity.SessionEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class SessionRepository extends CrudRepository<SessionEntity, UUID> {
    public SessionRepository(EntityManager entityManager) {
        super(entityManager, SessionEntity.class);
    }

        @Transactional
    public List<SessionEntity> findRecentSessions() {
        OffsetDateTime since = OffsetDateTime.now().minusHours(24);
        return entityManager.createQuery(
                "select s from SessionEntity s join fetch s.user "
                        + "where s.loginAt >= :since order by s.loginAt desc",
                SessionEntity.class)
                .setParameter("since", since)
                .getResultList()
                .stream()
                .toList();
    }
}




