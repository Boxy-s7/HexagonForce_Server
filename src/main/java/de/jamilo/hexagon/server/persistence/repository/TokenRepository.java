package de.jamilo.hexagon.server.persistence.repository;

import de.jamilo.hexagon.server.persistence.entity.TokenEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TokenRepository extends CrudRepository<TokenEntity, String> {
    public TokenRepository(EntityManager entityManager) {
        super(entityManager, TokenEntity.class);
    }
}



