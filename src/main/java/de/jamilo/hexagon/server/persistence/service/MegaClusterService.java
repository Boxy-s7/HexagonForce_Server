package de.jamilo.hexagon.server.persistence.service;

import java.util.UUID;

import de.jamilo.hexagon.server.persistence.entity.MegaClusterEntity;
import de.jamilo.hexagon.server.persistence.repository.MegaClusterRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MegaClusterService extends CrudService<MegaClusterEntity, UUID> {
    @Inject
    public MegaClusterService(MegaClusterRepository repository) {
        super(repository);
    }
}

