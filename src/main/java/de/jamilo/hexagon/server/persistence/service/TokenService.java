package de.jamilo.hexagon.server.persistence.service;

import de.jamilo.hexagon.server.persistence.entity.TokenEntity;
import de.jamilo.hexagon.server.persistence.repository.TokenRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TokenService extends CrudService<TokenEntity, String> {
    @Inject
    public TokenService(TokenRepository repository) {
        super(repository);
    }
}
