package de.jamilo.hexagon.server.service;

import java.util.ArrayList;
import java.util.List;


import de.jamilo.hexagon.server.domain.world.HexagonState;
import de.jamilo.hexagon.server.persistence.entity.HexagonEntity;
import de.jamilo.hexagon.server.persistence.repository.HexagonRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton 
public class NewPlayerService {


    @Inject 
    HexagonRepository hexaRepo;

    public HexagonEntity createNewPlayerPlace() {
        HexagonEntity seat = this.findSeat();
        List<HexagonEntity> hexes = new ArrayList<>();
        HexagonEntity firstUnknown = this.addUnknown(hexes, seat);
        HexagonEntity firstWild = this.addWild(hexes, firstUnknown);
        HexagonEntity owned = this.addOwned(hexes, firstWild);
        return owned;
    }
    

    private HexagonEntity findSeat() {
        HexagonEntity seat = hexaRepo.findWithThreeConsecutiveMissingNeighbors().get(0);
        return seat;
    }

    private HexagonEntity addOwned(List<HexagonEntity> hexes, HexagonEntity firstWild) {
        HexagonEntity owned = new HexagonEntity();
        owned.setState(HexagonState.OWNED);
        addNeighbors(hexes, owned, firstWild );
        return null;
    }

    private void addNeighbors(List<HexagonEntity> hexes, HexagonEntity owned, HexagonEntity firstWild) {
        HexagonEntity secWild = placeFirstWild(hexes, owned, firstWild );
        HexagonEntity triWild = placeAnotherWild(hexes, owned, secWild );
        HexagonEntity forWild = placeAnotherWild(hexes, owned, triWild );
        HexagonEntity fivWild = placeAnotherWild(hexes, owned, forWild );
        HexagonEntity sixWild = placeAnotherWild(hexes, owned, fivWild );
        placeAnotherWild(hexes, owned, sixWild );
    }

    private HexagonEntity placeFirstWild(List<HexagonEntity> hexes, HexagonEntity owned, HexagonEntity firstWild) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeFirstWild'");
    }

    private HexagonEntity placeAnotherWild(List<HexagonEntity> hexes, HexagonEntity owned, HexagonEntity fivWild) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeAnotherWild'");
    }

    private HexagonEntity addWild(List<HexagonEntity> hexes, HexagonEntity firstUnknown) {
        HexagonEntity first = addFirstWild(hexes, firstUnknown);
        addWildRing(hexes, first);
        return first;
    }

    private HexagonEntity addFirstWild(List<HexagonEntity> hexes, HexagonEntity firstUnknown) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFirstWild'");
    }

    private void addWildRing(List<HexagonEntity> hexes, HexagonEntity first) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addWildRing'");
    }

    private HexagonEntity addUnknown(List<HexagonEntity> hexes, HexagonEntity seat) {
        HexagonEntity first = addFirstUnknown(hexes, seat);
        addUnknownRing(hexes, first);
        return first;
    }

    private HexagonEntity addFirstUnknown(List<HexagonEntity> hexes, HexagonEntity seat) {
        HexagonEntity unknown = new HexagonEntity(HexagonState.UNKNOWN);
        hexes.add(unknown);
        
        if (seat.getOutpost5() == null && seat.getOutpost0() == null  && seat.getOutpost1() == null) {
            unknown.setOutpost3(seat.getHexId());
            hexaRepo.save(unknown);
            seat.setOutpost0(unknown.getHexId());
            return unknown;
        }
        if (seat.getOutpost0() == null && seat.getOutpost1() == null  && seat.getOutpost2() == null) {
            unknown.setOutpost4(seat.getHexId());
            hexaRepo.save(unknown);
            seat.setOutpost1(unknown.getHexId());
            return unknown;
        }
        if (seat.getOutpost1() == null && seat.getOutpost2() == null  && seat.getOutpost3() == null) {
            unknown.setOutpost5(seat.getHexId());
            hexaRepo.save(unknown);
            seat.setOutpost2(unknown.getHexId());
            return unknown;
        }
        if (seat.getOutpost2() == null && seat.getOutpost3() == null  && seat.getOutpost4() == null) {
            unknown.setOutpost0(seat.getHexId());
            hexaRepo.save(unknown);
            seat.setOutpost3(unknown.getHexId());
            return unknown;
        }
        if (seat.getOutpost3() == null && seat.getOutpost4() == null  && seat.getOutpost5() == null) {
            unknown.setOutpost1(seat.getHexId());
            hexaRepo.save(unknown);
            seat.setOutpost4(unknown.getHexId());
            return unknown;
        }
        if (seat.getOutpost4() == null && seat.getOutpost5() == null  && seat.getOutpost0() == null) {
            unknown.setOutpost2(seat.getHexId());
            hexaRepo.save(unknown);
            seat.setOutpost5(unknown.getHexId());
            return unknown;
        }
        return null;
    }
    
    private int addSeatSpawnpoint(List<HexagonEntity> hexes, HexagonEntity seat) {
        int spawnpoint = 0;
        
        if (seat.getOutpost5() == null && seat.getOutpost0() == null  && seat.getOutpost1() == null) {
            spawnpoint = 1;
        }
        else if (seat.getOutpost0() == null && seat.getOutpost1() == null  && seat.getOutpost2() == null) {
            spawnpoint = 2;
        }

        else if (seat.getOutpost1() == null && seat.getOutpost2() == null  && seat.getOutpost3() == null) {
            spawnpoint = 3;
        }
        else if (seat.getOutpost2() == null && seat.getOutpost3() == null  && seat.getOutpost4() == null) {
            spawnpoint = 4;
        }
        else if (seat.getOutpost3() == null && seat.getOutpost4() == null  && seat.getOutpost5() == null) {
            spawnpoint = 5;
        }
        else if (seat.getOutpost4() == null && seat.getOutpost5() == null  && seat.getOutpost0() == null) {
            spawnpoint = 6;
        }
        seat.setSpawnpoint(spawnpoint);
        return spawnpoint;
    }

    private int addFirstUnknownSpawnpoint(List<HexagonEntity> hexes, HexagonEntity firstU) {
        int spawnpoint = 0;
        
        if (firstU.getOutpost5() != null) {
            spawnpoint = 1;
        }
        else if (firstU.getOutpost0() != null) {
            spawnpoint = 2;
        }

        else if (firstU.getOutpost1() != null) {
            spawnpoint = 3;
        }
        else if (firstU.getOutpost2() != null) {
            spawnpoint = 4;
        }
        else if (firstU.getOutpost3() != null) {
            spawnpoint = 5;
        }
        else if (firstU.getOutpost4() != null) {
            spawnpoint = 6;
        }
        firstU.setSpawnpoint(spawnpoint);
        return spawnpoint;
    }

    private int addAnotherUnknownSpawnpoint(List<HexagonEntity> hexes, HexagonEntity prevU) {
        int spawnpoint = 0;
        
        if (prevU.getOutpost3() != null) {
            spawnpoint = 1;
        }
        else if (prevU.getOutpost4() != null) {
            spawnpoint = 2;
        }

        else if (prevU.getOutpost5() != null) {
            spawnpoint = 3;
        }
        else if (prevU.getOutpost0() != null) {
            spawnpoint = 4;
        }
        else if (prevU.getOutpost1() != null) {
            spawnpoint = 5;
        }
        else if (prevU.getOutpost2() != null) {
            spawnpoint = 6;
        }
        prevU.setSpawnpoint(spawnpoint);
        return spawnpoint;
    }

    private int addFirstWildSpawnpoint(List<HexagonEntity> hexes, HexagonEntity firstU) {
        int spawnpoint = 0;
        
        if (firstU.getOutpost5() != null) {
            spawnpoint = 1;
        }
        else if (firstU.getOutpost0() != null) {
            spawnpoint = 2;
        }

        else if (firstU.getOutpost1() != null) {
            spawnpoint = 3;
        }
        else if (firstU.getOutpost2() != null) {
            spawnpoint = 4;
        }
        else if (firstU.getOutpost3() != null) {
            spawnpoint = 5;
        }
        else if (firstU.getOutpost4() != null) {
            spawnpoint = 6;
        }
        firstU.setSpawnpoint(spawnpoint);
        return spawnpoint;
    }

    private void addUnknownRing(List<HexagonEntity> hexes, HexagonEntity first) {
        
    }


}
