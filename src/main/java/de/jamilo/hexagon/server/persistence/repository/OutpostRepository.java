package de.jamilo.hexagon.server.persistence.repository;

import java.util.UUID;
import de.jamilo.hexagon.server.persistence.entity.OutpostEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class OutpostRepository extends CrudRepository<OutpostEntity, UUID> {
    public OutpostRepository(EntityManager entityManager) {
        super(entityManager, OutpostEntity.class);
    }
}




