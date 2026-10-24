package de.jamilo.hexagon.server.persistence.service;

import java.util.List;
import java.util.UUID;

import de.jamilo.hexagon.server.persistence.entity.HexagonEntity;
import de.jamilo.hexagon.server.persistence.repository.HexagonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HexagonService extends CrudService<HexagonEntity, UUID> {
    private final HexagonRepository hexaRepo;

    @Inject
    public HexagonService(HexagonRepository hexaRepo) {
        super(hexaRepo);
        this.hexaRepo = hexaRepo;
    }

    public void createHexagon(HexagonEntity hexagon) {
        hexaRepo.create(hexagon);
    }

    @Transactional
    public void updateHexagons(List<HexagonEntity> hexagons) {
        for (HexagonEntity hexagon : hexagons) {
            hexaRepo.update(hexagon);
        }
    }
}
