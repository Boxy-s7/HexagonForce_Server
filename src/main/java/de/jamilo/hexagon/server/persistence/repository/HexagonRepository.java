package de.jamilo.hexagon.server.persistence.repository;

import java.util.List;
import java.util.UUID;

import de.jamilo.hexagon.server.domain.world.HexagonState;
import de.jamilo.hexagon.server.persistence.entity.HexagonEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class HexagonRepository extends CrudRepository<HexagonEntity, UUID> {
    public HexagonRepository(EntityManager entityManager) {
        super(entityManager, HexagonEntity.class);
    }

    @Transactional
    public HexagonEntity save(HexagonEntity hexagon) {
        if (hexagon.getHexId() == null) {
            return create(hexagon);
        }
        return update(hexagon);
    }

    public List<HexagonEntity> findWithThreeConsecutiveMissingNeighbors() {
        return entityManager.createQuery("""
                select h from HexagonEntity h
                where h.state = :state and (
                       (h.outpost0 is null and h.outpost1 is null and h.outpost2 is null)
                    or (h.outpost1 is null and h.outpost2 is null and h.outpost3 is null)
                    or (h.outpost2 is null and h.outpost3 is null and h.outpost4 is null)
                    or (h.outpost3 is null and h.outpost4 is null and h.outpost5 is null)
                    or (h.outpost4 is null and h.outpost5 is null and h.outpost0 is null)
                    or (h.outpost5 is null and h.outpost0 is null and h.outpost1 is null)
                )
                """, HexagonEntity.class)
                .setParameter("state", HexagonState.UNKNOWN)
                .getResultList();
    }
}


