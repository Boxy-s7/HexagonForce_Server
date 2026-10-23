package de.jamilo.hexagon.server.persistence.repository;

import java.util.List;
import java.util.UUID;

import de.jamilo.hexagon.server.persistence.entity.ClusterEntity;
import de.jamilo.hexagon.server.persistence.entity.HexagonEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClusterRepository {

    private final EntityManager entityManager;

    public ClusterRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public ClusterEntity save(ClusterEntity cluster) {
        entityManager.persist(cluster);
        entityManager.flush();
        return cluster;
    }

    /**
     * Returns hexagons having at least three consecutive unset neighbor IDs.
     * Neighbor slots are treated as a ring, so n_6, n_1, n_2 are consecutive.
     */
    @Transactional
    public List<HexagonEntity> findWithThreeConsecutiveMissingNeighbors() {
        return entityManager.createQuery("""
                select h
                from HexagonEntity h
                where 
                    h.state = "UNKNOWN"
                and 
                        (h.neighbor1 is null and h.neighbor2 is null and h.neighbor3 is null)
                    or  (h.neighbor2 is null and h.neighbor3 is null and h.neighbor4 is null)
                    or  (h.neighbor3 is null and h.neighbor4 is null and h.neighbor5 is null)
                    or  (h.neighbor4 is null and h.neighbor5 is null and h.neighbor6 is null)
                    or  (h.neighbor5 is null and h.neighbor6 is null and h.neighbor1 is null)
                    or  (h.neighbor6 is null and h.neighbor1 is null and h.neighbor2 is null)
                """, HexagonEntity.class)
            .getResultList();
    }

    public ClusterEntity findById(UUID clusterId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
