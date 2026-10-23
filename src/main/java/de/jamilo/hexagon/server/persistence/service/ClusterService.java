package de.jamilo.hexagon.server.persistence.service;

import java.util.UUID;

import de.jamilo.hexagon.server.application.world.ClusterWrapper;
import de.jamilo.hexagon.server.domain.world.ClusterRelation;
import de.jamilo.hexagon.server.persistence.entity.ClusterEntity;
import de.jamilo.hexagon.server.persistence.entity.HexagonEntity;
import de.jamilo.hexagon.server.persistence.repository.ClusterRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton 
public class ClusterService {

    @Inject
    ClusterRepository clusterRepo;

    public ClusterWrapper findCluster(HexagonEntity seat) {
        return new ClusterWrapper(this.clusterRepo.findById(seat.getClusterId()));
    }

    public ClusterWrapper createCluster(ClusterEntity cluster) {
        return new ClusterWrapper(this.clusterRepo.save(cluster));
    }

    public ClusterWrapper updateCluster(ClusterEntity cluster) {
        return new ClusterWrapper(this.clusterRepo.save(cluster));
    }

    public ClusterWrapper getRelationTarget(ClusterWrapper cluster, ClusterRelation relation) {
        
        int rootNeighbor = relation.getTargetIndex() - 1;
        
        return cluster.getNeigborCluster(relation);
    }
}
