
package de.jamilo.hexagon.server.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.jamilo.hexagon.server.domain.world.ClusterRole;
import de.jamilo.hexagon.server.domain.world.HexagonState;
import jakarta.persistence.*;

@Entity
@Table(name = "world_hexagon")
public class HexagonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "n_1", nullable = true)
    private UUID neighbor1;

    @Column(name = "n_2", nullable = true)
    private UUID neighbor2;

    @Column(name = "n_3", nullable = true)
    private UUID neighbor3;

    @Column(name = "n_4", nullable = true)
    private UUID neighbor4;

    @Column(name = "n_5", nullable = true)
    private UUID neighbor5;

    @Column(name = "n_6", nullable = true)
    private UUID neighbor6;


    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(name = "cluster_id")
    private UUID clusterId;

    @Column(name = "cluster_role")
    @Enumerated(EnumType.STRING)
    private ClusterRole clusterRole;


    public ClusterRole getClusterRole() {
        return clusterRole;
    }
    public void setClusterRole(ClusterRole clusterRole) {
        this.clusterRole = clusterRole;
    }
    public UUID getClusterId() {
        return clusterId;
    }
    public void setClusterId(UUID clusterId) {
        this.clusterId = clusterId;
    }
    @Column (name = "state", nullable = false)
    @Enumerated(EnumType.STRING)    
    private HexagonState state;

    @Transient
    private final List<UUID> neighbors;

    @Transient 
    private int spawnpoint;









    public int getSpawnpoint() {
        return spawnpoint;
    }
    public void setSpawnpoint(int spawnpoint) {
        this.spawnpoint = spawnpoint;
    }
    public HexagonEntity() {
        neighbors = new ArrayList<>(6 );
    }
    public HexagonEntity(HexagonState state) {
        this();
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public UUID getNeighbor1() {
        return neighbor1;
    }

    public UUID getNeighbor2() {
        return neighbor2;
    }

    public UUID getNeighbor3() {
        return neighbor3;
    }

    public UUID getNeighbor4() {
        return neighbor4;
    }

    public UUID getNeighbor5() {
        return neighbor5;
    }

    public UUID getNeighbor6() {
        return neighbor6;
    }

    public List<UUID> getNeighbors() {
        return neighbors;
    }

    public void setNeighbor1(UUID n) {
        neighbor1 = n;
        neighbors.set(0, n);
    }

    public void setNeighbor2(UUID n) {
        neighbor2 = n;
        neighbors.set(1, n);
    }

    public void setNeighbor3(UUID n) {
        neighbor3 = n;
        neighbors.set(2, n);
    }

    public void setNeighbor4(UUID n) {
        neighbor4 = n;
        neighbors.set(3, n);
    }

    public void setNeighbor5(UUID n) {
        neighbor5 = n;
        neighbors.set(4, n);
    }

    public void setNeighbor6(UUID n) {
        neighbor6 = n;
        neighbors.set(5, n);
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public HexagonState getState() {
        return state;
    }

    public void setState(HexagonState owned) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setState'");
    }
    public void setNeighbor(int index, UUID id) {
        if (index == 0) setNeighbor1(id);
        if (index == 1) setNeighbor2(id);
        if (index == 2) setNeighbor3(id);
        if (index == 3) setNeighbor4(id);
        if (index == 4) setNeighbor5(id);
        if (index == 5) setNeighbor6(id);
    }
}
