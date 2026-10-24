package de.jamilo.hexagon.server.persistence.repository;

import java.util.UUID;
import de.jamilo.hexagon.server.persistence.entity.MegaClusterEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MegaClusterRepository extends CrudRepository<MegaClusterEntity, UUID> {
    public MegaClusterRepository(EntityManager entityManager) {
        super(entityManager, MegaClusterEntity.class);
    }
}




