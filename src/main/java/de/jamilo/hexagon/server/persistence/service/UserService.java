package de.jamilo.hexagon.server.persistence.service;

import java.util.UUID;

import de.jamilo.hexagon.server.persistence.entity.UserEntity;
import de.jamilo.hexagon.server.persistence.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService extends CrudService<UserEntity, UUID> {
    @Inject
    public UserService(UserRepository repository) {
        super(repository);
    }
}

