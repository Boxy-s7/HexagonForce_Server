package de.jamilo.hexagon.server.application.world;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.jamilo.hexagon.server.domain.world.ClusterRelation;
import de.jamilo.hexagon.server.domain.world.ClusterRole;
import de.jamilo.hexagon.server.persistence.entity.ClusterEntity;
import de.jamilo.hexagon.server.persistence.entity.HexagonEntity;
import de.jamilo.hexagon.server.persistence.service.ClusterService;
import de.jamilo.hexagon.server.persistence.service.HexagonService;
import jakarta.inject.Inject;


public class ClusterWrapper {
    
    @Inject
    ClusterService clusterService;

    @Inject
    HexagonService hexaService;
    
    private ClusterEntity cluster;
    public final List<HexagonEntity> hexas;

    public ClusterWrapper(ClusterEntity cluster) {
        this.cluster = cluster;
        hexas = new ArrayList<>(25);
    }

    public ClusterEntity getCluster() {
        return cluster;
    }

    public void setCluster(ClusterEntity cluster) {
        this.cluster = cluster;
    }

    public void setNeighborCluster(ClusterWrapper tCluster, HexagonEntity seat) {
        int n = seat.getClusterRole().index / 2;
        this.setNeighbor(n, tCluster.id());
        tCluster.setNeighbor(((n + 3) % 6), id());
        clusterService.updateCluster(this.cluster);
        clusterService.updateCluster(tCluster.cluster);
    }

    private void setNeighbor(int n, UUID id){
        switch(n){
            case 0: cluster.setCluster0(id);
            case 1: cluster.setCluster1(id);
            case 2: cluster.setCluster2(id);
            case 3: cluster.setCluster3(id);
            case 4: cluster.setCluster4(id);
            case 5: cluster.setCluster5(id);
            break;
        }
    }

    public void setHexagon(HexagonEntity hex) {
        switch (hex.getClusterRole()) {
            case o_0:
                cluster.setOwner0(hex.getId());
                break;
            case s_0:
                cluster.setSpecial0(hex.getId());
                break;
            case s_1:
                cluster.setSpecial1(hex.getId());
                break;
            case s_2:
                cluster.setSpecial2(hex.getId());
                break;
            case s_3:
                cluster.setSpecial3(hex.getId());
                break;
            case s_4:
                cluster.setSpecial4(hex.getId());
                break;
            case s_5:
                cluster.setSpecial5(hex.getId());
                break;
            case u_0:
                cluster.setUnknown0(hex.getId());
                break;
            case u_1:
                cluster.setUnknown1(hex.getId());
                break;
            case u_2:
                cluster.setUnknown2(hex.getId());
                break;
            case u_3:
                cluster.setUnknown3(hex.getId());
                break;
            case u_4:
                cluster.setUnknown4(hex.getId());
                break;
            case u_5:
                cluster.setUnknown5(hex.getId());
                break;
            case u_6:
                cluster.setUnknown6(hex.getId());
                break;
            case u_7:
                cluster.setUnknown7(hex.getId());
                break;
            case u_8:
                cluster.setUnknown8(hex.getId());
                break;
            case u_9:
                cluster.setUnknown9(hex.getId());
                break;
            case u_10:
                cluster.setUnknown10(hex.getId());
                break;
            case u_11:
                cluster.setUnknown11(hex.getId());
                break;
            case w_0:
                cluster.setWild0(hex.getId());
                break;
            case w_1:
                cluster.setWild1(hex.getId());
                break;
            case w_2:
                cluster.setWild2(hex.getId());
                break;
            case w_3:
                cluster.setWild3(hex.getId());
                break;
            case w_4:
                cluster.setWild4(hex.getId());
                break;
            case w_5:
                cluster.setWild5(hex.getId());
                break;
            default:
                break;
        }

        hexas.set(hex.getClusterRole().index, hex);
        clusterService.updateCluster(cluster);
    }
public UUID getHexagon(ClusterRole role) {
    switch (role) {
        case o_0:
            return cluster.getOwner0();
        case s_0:
            return cluster.getSpecial0();
        case s_1:
            return cluster.getSpecial1();
        case s_2:
            return cluster.getSpecial2();
        case s_3:
            return cluster.getSpecial3();
        case s_4:
            return cluster.getSpecial4();
        case s_5:
            return cluster.getSpecial5();
        case u_0:
            return cluster.getUnknown0();
        case u_1:
            return cluster.getUnknown1();
        case u_2:
            return cluster.getUnknown2();
        case u_3:
            return cluster.getUnknown3();
        case u_4:
            return cluster.getUnknown4();
        case u_5:
            return cluster.getUnknown5();
        case u_6:
            return cluster.getUnknown6();
        case u_7:
            return cluster.getUnknown7();
        case u_8:
            return cluster.getUnknown8();
        case u_9:
            return cluster.getUnknown9();
        case u_10:
            return cluster.getUnknown10();
        case u_11:
            return cluster.getUnknown11();
        case w_0:
            return cluster.getWild0();
        case w_1:
            return cluster.getWild1();
        case w_2:
            return cluster.getWild2();
        case w_3:
            return cluster.getWild3();
        case w_4:
            return cluster.getWild4();
        case w_5:
            return cluster.getWild5();
        default:
            return null;
    }
}
    public HexagonEntity getRelationTarget(ClusterRelation relation) {
        return hexas.get(relation.getTargetRole().index);
    }

    public UUID id() {
        return this.cluster.getId();
    }

    public ClusterWrapper getNeigborCluster(ClusterRelation relation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNeigborCluster'");
    }

    public void registerCluster(ClusterWrapper clusterWrapper, HexagonEntity seat) {
        ClusterWrapper rightNeighbor = findRightNeighbor(seat);
        ClusterWrapper leftNeighbor = findLeftNeighbor(seat);
        if (rightNeighbor != null) {
            linkRightNeighbor(clusterWrapper, rightNeighbor, seat.getSpawnpoint());
        }
        if (leftNeighbor != null) {
            linkleftNeighbor(clusterWrapper, leftNeighbor);
        }
    }

    private void linkleftNeighbor(ClusterWrapper clusterWrapper, ClusterWrapper leftNeighbor) {

    }

    private void linkRightNeighbor(ClusterWrapper clusterWrapper, ClusterWrapper rightNeighbor, int spawnpoint) {
        rightNeighbor.linkRightCluster(((spawnpoint / 2 - 1) + 6) % 6);
    }

    private void linkRightCluster(int spawnpoint) {
        
    }

    private ClusterWrapper findLeftNeighbor(HexagonEntity seat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findLeftNeighbor'");
    }

    private ClusterWrapper findRightNeighbor(HexagonEntity seat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findRightNeighbor'");
    }
}
