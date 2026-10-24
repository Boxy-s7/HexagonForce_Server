package de.jamilo.hexagon.server.persistence.repository;

import java.util.UUID;

import de.jamilo.hexagon.server.persistence.entity.ClusterEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ClusterRepository extends CrudRepository<ClusterEntity, UUID> {
    public ClusterRepository(EntityManager entityManager) {
        super(entityManager, ClusterEntity.class);
    }

    public ClusterEntity save(ClusterEntity cluster) {
        if (cluster.getClusterId() == null) {
            return create(cluster);
        }
        return update(cluster);
    }
}
