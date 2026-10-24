package de.jamilo.hexagon.server.persistence.service;

import java.util.UUID;

import de.jamilo.hexagon.server.application.world.ClusterWrapper;
import de.jamilo.hexagon.server.domain.world.ClusterRelation;
import de.jamilo.hexagon.server.persistence.entity.ClusterEntity;
import de.jamilo.hexagon.server.persistence.entity.HexagonEntity;
import de.jamilo.hexagon.server.persistence.repository.ClusterRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClusterService extends CrudService<ClusterEntity, UUID> {
    private final ClusterRepository clusterRepo;

    @Inject
    public ClusterService(ClusterRepository clusterRepo) {
        super(clusterRepo);
        this.clusterRepo = clusterRepo;
    }

    public ClusterWrapper findCluster(HexagonEntity hexagon) {
        ClusterEntity cluster = clusterRepo.findById(hexagon.getClusterId());
        return cluster == null ? null : new ClusterWrapper(cluster);
    }

    public ClusterWrapper createCluster(ClusterEntity cluster) {
        return new ClusterWrapper(clusterRepo.create(cluster));
    }

    public ClusterWrapper updateCluster(ClusterEntity cluster) {
        return new ClusterWrapper(clusterRepo.update(cluster));
    }

    public ClusterWrapper getRelationTarget(ClusterWrapper cluster, ClusterRelation relation) {
        return cluster.getNeigborCluster(relation);
    }
}

