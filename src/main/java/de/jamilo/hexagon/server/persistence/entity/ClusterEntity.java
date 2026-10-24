package de.jamilo.hexagon.server.persistence.entity;

import java.util.UUID;

import de.jamilo.hexagon.server.domain.world.ClusterRole;
import de.jamilo.hexagon.server.domain.world.ClusterState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "world_cluster")
public class ClusterEntity {
    @Column(name = "mega_cluster_id")
    private UUID megaClusterId;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cluster_id")
    private UUID clusterId;

    @Column(name = "cluster_role")
    @Enumerated(EnumType.STRING)
    private ClusterRole role;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClusterState state;

    @Column(name = "o_0") private UUID owner0;
    @Column(name = "w_0") private UUID wild0;
    @Column(name = "w_1") private UUID wild1;
    @Column(name = "w_2") private UUID wild2;
    @Column(name = "w_3") private UUID wild3;
    @Column(name = "w_4") private UUID wild4;
    @Column(name = "w_5") private UUID wild5;
    @Column(name = "u_0") private UUID unknown0;
    @Column(name = "u_1") private UUID unknown1;
    @Column(name = "u_2") private UUID unknown2;
    @Column(name = "u_3") private UUID unknown3;
    @Column(name = "u_4") private UUID unknown4;
    @Column(name = "u_5") private UUID unknown5;
    @Column(name = "u_6") private UUID unknown6;
    @Column(name = "u_7") private UUID unknown7;
    @Column(name = "u_8") private UUID unknown8;
    @Column(name = "u_9") private UUID unknown9;
    @Column(name = "u_10") private UUID unknown10;
    @Column(name = "u_11") private UUID unknown11;

    public ClusterEntity() { }
    public ClusterEntity(ClusterState state) { this.state = state; }
    public UUID getClusterId() { return clusterId; }
    public void setClusterId(UUID clusterId) { this.clusterId = clusterId; }
    public UUID getMegaClusterId() { return megaClusterId; }
    public void setMegaClusterId(UUID megaClusterId) { this.megaClusterId = megaClusterId; }
    public ClusterRole getRole() { return role; }
    public void setRole(ClusterRole role) { this.role = role; }
    public ClusterState getState() { return state; }
    public void setState(ClusterState state) { this.state = state; }
    public UUID getOwner0() { return owner0; }
    public void setOwner0(UUID value) { owner0 = value; }
    public UUID getWild0() { return wild0; }
    public void setWild0(UUID value) { wild0 = value; }
    public UUID getWild1() { return wild1; }
    public void setWild1(UUID value) { wild1 = value; }
    public UUID getWild2() { return wild2; }
    public void setWild2(UUID value) { wild2 = value; }
    public UUID getWild3() { return wild3; }
    public void setWild3(UUID value) { wild3 = value; }
    public UUID getWild4() { return wild4; }
    public void setWild4(UUID value) { wild4 = value; }
    public UUID getWild5() { return wild5; }
    public void setWild5(UUID value) { wild5 = value; }
    public UUID getUnknown0() { return unknown0; }
    public void setUnknown0(UUID value) { unknown0 = value; }
    public UUID getUnknown1() { return unknown1; }
    public void setUnknown1(UUID value) { unknown1 = value; }
    public UUID getUnknown2() { return unknown2; }
    public void setUnknown2(UUID value) { unknown2 = value; }
    public UUID getUnknown3() { return unknown3; }
    public void setUnknown3(UUID value) { unknown3 = value; }
    public UUID getUnknown4() { return unknown4; }
    public void setUnknown4(UUID value) { unknown4 = value; }
    public UUID getUnknown5() { return unknown5; }
    public void setUnknown5(UUID value) { unknown5 = value; }
    public UUID getUnknown6() { return unknown6; }
    public void setUnknown6(UUID value) { unknown6 = value; }
    public UUID getUnknown7() { return unknown7; }
    public void setUnknown7(UUID value) { unknown7 = value; }
    public UUID getUnknown8() { return unknown8; }
    public void setUnknown8(UUID value) { unknown8 = value; }
    public UUID getUnknown9() { return unknown9; }
    public void setUnknown9(UUID value) { unknown9 = value; }
    public UUID getUnknown10() { return unknown10; }
    public void setUnknown10(UUID value) { unknown10 = value; }
    public UUID getUnknown11() { return unknown11; }
    public void setUnknown11(UUID value) { unknown11 = value; }
}
