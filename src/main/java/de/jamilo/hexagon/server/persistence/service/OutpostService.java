package de.jamilo.hexagon.server.persistence.service;

import java.util.UUID;

import de.jamilo.hexagon.server.persistence.entity.OutpostEntity;
import de.jamilo.hexagon.server.persistence.repository.OutpostRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OutpostService extends CrudService<OutpostEntity, UUID> {
    @Inject
    public OutpostService(OutpostRepository repository) {
        super(repository);
    }
}

