package de.jamilo.hexagon.server.persistence.service;

import java.util.List;

import de.jamilo.hexagon.server.persistence.entity.HexagonEntity;
import de.jamilo.hexagon.server.persistence.repository.HexagonRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton 
public class HexagonService {

    @Inject 
    HexagonRepository hexaRepo;

    public void createHexagon(HexagonEntity o_0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createHexagon'");
    }

    public void updateHexagons(List<HexagonEntity> hexas) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateHexagons'");
    }


}
