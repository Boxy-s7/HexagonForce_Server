package de.jamilo.hexagon.server.application.world;

import de.jamilo.hexagon.server.domain.world.ClusterRelation;
import de.jamilo.hexagon.server.domain.world.ClusterRelations;
import de.jamilo.hexagon.server.domain.world.HexagonRole;
import de.jamilo.hexagon.server.domain.world.ClusterState;
import de.jamilo.hexagon.server.persistence.entity.ClusterEntity;
import de.jamilo.hexagon.server.persistence.entity.HexagonEntity;
import de.jamilo.hexagon.server.persistence.service.ClusterService;
import de.jamilo.hexagon.server.persistence.service.HexagonService;
import jakarta.inject.Inject;

public class ClusterCreator {
    
    @Inject
    ClusterService clusterService;

    @Inject
    HexagonService hexaService;

    private HexagonEntity seat;
    private ClusterEntity cluster;
    private ClusterWrapper clusterWrapper;
    private ClusterWrapper rootCluster;

    public ClusterCreator(HexagonEntity seat) {
        this.seat = seat;
    }

    public ClusterWrapper create() {
        getRootCluster();
        generateCluster();
        linkClusters();
        generateHexagons();
        linkHexagons();
        updateCluster();
        return this.clusterWrapper;
    }

    private void linkHexagons() {
        for (HexagonEntity hex : clusterWrapper.hexas) {
            linkHexagon(hex);
        }

        hexaService.updateHexagons(clusterWrapper.hexas);
    }

    private void linkHexagon(HexagonEntity hex) {
        ClusterRelation[] relations  = ClusterRelations.relations.get(hex.getRole());
        for (int i = 0; i < 6; i++) {
            var relation = relations[i];
            if (relation.getTargetCluster() > -1) {
                linkExternalHexagon(hex, relation, i);
            } else {
                linkInternalHexagon(hex, relation, i);
            }
        }
    }

    private void linkInternalHexagon(HexagonEntity hex, ClusterRelation relation, int index) {
        HexagonEntity target = clusterWrapper.getRelationTarget(relation);
        linkHexes(index, hex, relation.getTargetIndex(), target);
    }

    private void linkExternalHexagon(HexagonEntity hex, ClusterRelation relation, int index) {
        ClusterWrapper targetCluster = clusterService.getRelationTarget(clusterWrapper, relation);
        HexagonEntity target = targetCluster.getRelationTarget(relation);
        linkHexes(index, hex, relation.getTargetIndex(), target);

    }

    private void linkHexes(int sIndex, HexagonEntity sHex, int tIndex, HexagonEntity tHex) {
        sHex.setOutpost(sIndex, tHex.getHexId());
        tHex.setOutpost(tIndex, sHex.getHexId());
    }

    private void generateHexagons() {
        for (HexagonRole role : HexagonRole.values()) { 
          generateHexagon(role);  
        }
    }

    private void generateHexagon(HexagonRole role){
        HexagonEntity hex = new HexagonEntity(role.initialState);
        hex.setRole(role);
        hex.setClusterId(cluster.getClusterId());
        hex.setSpawnpoint(role.spawnPoint);
        hexaService.createHexagon(hex);
        clusterWrapper.setHexagon(hex);
    }

    private void linkClusters() {
        rootCluster.registerCluster(this.clusterWrapper, this.seat);
    }

    private void getRootCluster() {
        rootCluster = clusterService.findCluster(seat);
    }

    private void generateCluster() {
        cluster = new ClusterEntity(ClusterState.SPAWNING);
        clusterService.createCluster(cluster);
        this.clusterWrapper = new ClusterWrapper(cluster);
    }

    public HexagonEntity getSeat() {
        return seat;
    }

    public void setSeat(HexagonEntity seat) {
        this.seat = seat;
    }

    private void updateCluster() {
        cluster.setState(ClusterState.FREE);
        clusterService.updateCluster(cluster);
    }
}
