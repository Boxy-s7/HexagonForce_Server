package de.jamilo.hexagon.server.persistence.service;
import java.util.List;
import java.util.UUID;

import de.jamilo.hexagon.server.persistence.entity.SessionEntity;
import de.jamilo.hexagon.server.persistence.repository.SessionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SessionService extends CrudService<SessionEntity, UUID> {
    SessionRepository repository;
    @Inject
    public SessionService(SessionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<SessionEntity> findRecentSessions() {
        return repository.findRecentSessions();
    }

}

