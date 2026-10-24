package de.jamilo.hexagon.server.persistence.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import de.jamilo.hexagon.server.domain.world.HexagonRole;
import de.jamilo.hexagon.server.domain.world.HexagonState;
import jakarta.persistence.*;

@Entity
@Table(name = "world_hexagon")
public class HexagonEntity {
    @Column(name = "mega_cluster_id")
    private UUID megaClusterId;

    @Column(name = "cluster_id")
    private UUID clusterId;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "hex_id")
    private UUID hexId;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(name = "cluster_role")
    @Enumerated(EnumType.STRING)
    private HexagonRole role;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private HexagonState state;

    @Column(name = "o_0") private UUID outpost0;
    @Column(name = "o_1") private UUID outpost1;
    @Column(name = "o_2") private UUID outpost2;
    @Column(name = "o_3") private UUID outpost3;
    @Column(name = "o_4") private UUID outpost4;
    @Column(name = "o_5") private UUID outpost5;

    @Transient
    private final List<UUID> outposts;

    @Transient
    private int spawnpoint;

    public HexagonEntity() {
        outposts = new ArrayList<>(Collections.nCopies(6, null));
    }
    public HexagonEntity(HexagonState state) { this(); this.state = state; }
    public UUID getHexId() { return hexId; }
    public UUID getMegaClusterId() { return megaClusterId; }
    public void setMegaClusterId(UUID id) { megaClusterId = id; }
    public UUID getClusterId() { return clusterId; }
    public void setClusterId(UUID id) { clusterId = id; }
    public UUID getOwnerId() { return ownerId; }
    public void setOwnerId(UUID id) { ownerId = id; }
    public HexagonRole getRole() { return role; }
    public void setRole(HexagonRole role) { this.role = role; }
    public HexagonState getState() { return state; }
    public void setState(HexagonState state) { this.state = state; }
    public int getSpawnpoint() { return spawnpoint; }
    public void setSpawnpoint(int spawnpoint) { this.spawnpoint = spawnpoint; }
    public UUID getOutpost0() { return outpost0; }
    public void setOutpost0(UUID id) { outpost0 = id; outposts.set(0, id); }
    public UUID getOutpost1() { return outpost1; }
    public void setOutpost1(UUID id) { outpost1 = id; outposts.set(1, id); }
    public UUID getOutpost2() { return outpost2; }
    public void setOutpost2(UUID id) { outpost2 = id; outposts.set(2, id); }
    public UUID getOutpost3() { return outpost3; }
    public void setOutpost3(UUID id) { outpost3 = id; outposts.set(3, id); }
    public UUID getOutpost4() { return outpost4; }
    public void setOutpost4(UUID id) { outpost4 = id; outposts.set(4, id); }
    public UUID getOutpost5() { return outpost5; }
    public void setOutpost5(UUID id) { outpost5 = id; outposts.set(5, id); }
    public List<UUID> getOutposts() { return outposts; }
    public void setOutpost(int index, UUID id) {
        switch (index) {
            case 0 -> setOutpost0(id);
            case 1 -> setOutpost1(id);
            case 2 -> setOutpost2(id);
            case 3 -> setOutpost3(id);
            case 4 -> setOutpost4(id);
            case 5 -> setOutpost5(id);
            default -> throw new IndexOutOfBoundsException("Outpost index must be between 0 and 5: " + index);
        }
    }
}
