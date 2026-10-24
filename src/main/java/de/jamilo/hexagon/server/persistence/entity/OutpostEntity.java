package de.jamilo.hexagon.server.persistence.entity;

import java.util.UUID;

import de.jamilo.hexagon.server.domain.world.HexagonState;
import jakarta.persistence.*;

@Entity
@Table(name = "world_outpost")
public class OutpostEntity {
    @Column(name = "mega_cluster_id")
    private UUID megaClusterId;

    @Column(name = "cluster_id")
    private UUID clusterId;

    @Column(name = "hex_id")
    private UUID hexId;

    @Column(name = "outpost_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private HexagonState state;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "outpost_id")
    private UUID outpostId;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(name = "edge_id")
    private int edgeId;

    public OutpostEntity() { }
    public OutpostEntity(HexagonState state) { this.state = state; }
    public UUID getOutpostId() { return outpostId; }
    public UUID getMegaClusterId() { return megaClusterId; }
    public void setMegaClusterId(UUID value) { megaClusterId = value; }
    public UUID getClusterId() { return clusterId; }
    public void setClusterId(UUID value) { clusterId = value; }
    public UUID getHexId() { return hexId; }
    public void setHexId(UUID value) { hexId = value; }
    public UUID getOwnerId() { return ownerId; }
    public void setOwnerId(UUID value) { ownerId = value; }
    public int getEdge() { return edgeId; }
    public void setEdge(int value) { edgeId = value; }
    public void setRole(int value) { edgeId = value; }
    public HexagonState getState() { return state; }
    public void setState(HexagonState state) { this.state = state; }
}
