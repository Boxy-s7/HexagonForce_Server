package de.jamilo.hexagon.server.persistence.repository;

import java.util.UUID;
import de.jamilo.hexagon.server.persistence.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UserRepository extends CrudRepository<UserEntity, UUID> {
    public UserRepository(EntityManager entityManager) {
        super(entityManager, UserEntity.class);
    }
}




