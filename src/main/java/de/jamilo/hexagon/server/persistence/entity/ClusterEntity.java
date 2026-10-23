package de.jamilo.hexagon.server.persistence.entity;

import java.util.UUID;

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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "c_0", nullable = true)
    private UUID cluster0;

    @Column(name = "c_1", nullable = true)
    private UUID cluster1;

    @Column(name = "c_2", nullable = true)
    private UUID cluster2;

    @Column(name = "c_3", nullable = true)
    private UUID cluster3;

    @Column(name = "c_4", nullable = true)
    private UUID cluster4;

    @Column(name = "c_5", nullable = true)
    private UUID cluster5;


    @Column(name = "o_0")
    private UUID owner0;

    @Column(name = "w_0")
    private UUID wild0;

    @Column(name = "w_1")
    private UUID wild1;

    @Column(name = "w_2")
    private UUID wild2;

    @Column(name = "w_3")
    private UUID wild3;

    @Column(name = "w_4")
    private UUID wild4;

    @Column(name = "w_5")
    private UUID wild5;

    @Column(name = "u_0")
    private UUID unknown0;

    @Column(name = "u_1")
    private UUID unknown1;

    @Column(name = "u_2")
    private UUID unknown2;

    @Column(name = "u_3")
    private UUID unknown3;

    @Column(name = "u_4")
    private UUID unknown4;

    @Column(name = "u_5")
    private UUID unknown5;

    @Column(name = "u_6")
    private UUID unknown6;

    @Column(name = "u_7")
    private UUID unknown7;

    @Column(name = "u_8")
    private UUID unknown8;

    @Column(name = "u_9")
    private UUID unknown9;

    @Column(name = "u_10")
    private UUID unknown10;

    @Column(name = "u_11")
    private UUID unknown11;

    @Column(name = "s_0")
    private UUID special0;

    @Column(name = "s_1")
    private UUID special1;

    @Column(name = "s_2")
    private UUID special2;

    @Column(name = "s_3")
    private UUID special3;

    @Column(name = "s_4")
    private UUID special4;

    @Column(name = "s_5")
    private UUID special5;


    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCluster0() {
        return cluster0;
    }

    public void setCluster0(UUID cluster0) {
        this.cluster0 = cluster0;
    }

    public UUID getCluster1() {
        return cluster1;
    }

    public void setCluster1(UUID cluster1) {
        this.cluster1 = cluster1;
    }

    public UUID getCluster2() {
        return cluster2;
    }

    public void setCluster2(UUID cluster2) {
        this.cluster2 = cluster2;
    }

    public UUID getCluster3() {
        return cluster3;
    }

    public void setCluster3(UUID cluster3) {
        this.cluster3 = cluster3;
    }

    public UUID getCluster4() {
        return cluster4;
    }

    public void setCluster4(UUID cluster4) {
        this.cluster4 = cluster4;
    }

    public UUID getCluster5() {
        return cluster5;
    }

    public void setCluster5(UUID cluster5) {
        this.cluster5 = cluster5;
    }

    public UUID getOwner0() {
        return owner0;
    }

    public void setOwner0(UUID owner0) {
        this.owner0 = owner0;
    }

    public UUID getWild0() {
        return wild0;
    }

    public void setWild0(UUID wild0) {
        this.wild0 = wild0;
    }

    public UUID getWild1() {
        return wild1;
    }

    public void setWild1(UUID wild1) {
        this.wild1 = wild1;
    }

    public UUID getWild2() {
        return wild2;
    }

    public void setWild2(UUID wild2) {
        this.wild2 = wild2;
    }

    public UUID getWild3() {
        return wild3;
    }

    public void setWild3(UUID wild3) {
        this.wild3 = wild3;
    }

    public UUID getWild4() {
        return wild4;
    }

    public void setWild4(UUID wild4) {
        this.wild4 = wild4;
    }

    public UUID getWild5() {
        return wild5;
    }

    public void setWild5(UUID wild5) {
        this.wild5 = wild5;
    }

    public UUID getUnknown0() {
        return unknown0;
    }

    public void setUnknown0(UUID unknown0) {
        this.unknown0 = unknown0;
    }

    public UUID getUnknown1() {
        return unknown1;
    }

    public void setUnknown1(UUID unknown1) {
        this.unknown1 = unknown1;
    }

    public UUID getUnknown2() {
        return unknown2;
    }

    public void setUnknown2(UUID unknown2) {
        this.unknown2 = unknown2;
    }

    public UUID getUnknown3() {
        return unknown3;
    }

    public void setUnknown3(UUID unknown3) {
        this.unknown3 = unknown3;
    }

    public UUID getUnknown4() {
        return unknown4;
    }

    public void setUnknown4(UUID unknown4) {
        this.unknown4 = unknown4;
    }

    public UUID getUnknown5() {
        return unknown5;
    }

    public void setUnknown5(UUID unknown5) {
        this.unknown5 = unknown5;
    }

    public UUID getUnknown6() {
        return unknown6;
    }

    public void setUnknown6(UUID unknown6) {
        this.unknown6 = unknown6;
    }

    public UUID getUnknown7() {
        return unknown7;
    }

    public void setUnknown7(UUID unknown7) {
        this.unknown7 = unknown7;
    }

    public UUID getUnknown8() {
        return unknown8;
    }

    public void setUnknown8(UUID unknown8) {
        this.unknown8 = unknown8;
    }

    public UUID getUnknown9() {
        return unknown9;
    }

    public void setUnknown9(UUID unknown9) {
        this.unknown9 = unknown9;
    }

    public UUID getUnknown10() {
        return unknown10;
    }

    public void setUnknown10(UUID unknown10) {
        this.unknown10 = unknown10;
    }

    public UUID getUnknown11() {
        return unknown11;
    }

    public void setUnknown11(UUID unknown11) {
        this.unknown11 = unknown11;
    }

    public UUID getSpecial0() {
        return special0;
    }

    public void setSpecial0(UUID special0) {
        this.special0 = special0;
    }

    public UUID getSpecial1() {
        return special1;
    }

    public void setSpecial1(UUID special1) {
        this.special1 = special1;
    }

    public UUID getSpecial2() {
        return special2;
    }

    public void setSpecial2(UUID special2) {
        this.special2 = special2;
    }

    public UUID getSpecial3() {
        return special3;
    }

    public void setSpecial3(UUID special3) {
        this.special3 = special3;
    }

    public UUID getSpecial4() {
        return special4;
    }

    public void setSpecial4(UUID special4) {
        this.special4 = special4;
    }

    public UUID getSpecial5() {
        return special5;
    }

    public void setSpecial5(UUID special5) {
        this.special5 = special5;
    }

    public ClusterState getState() {
        return state;
    }

    public void setState(ClusterState state) {
        this.state = state;
    }

    @Column (name = "state", nullable = false)
    @Enumerated(EnumType.STRING)    
    private ClusterState state;


    public ClusterEntity() {
    }

    public ClusterEntity(ClusterState state) {
        this();
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public UUID getcluster1() {
        return cluster1;
    }

    public UUID getcluster2() {
        return cluster2;
    }

    public UUID getcluster3() {
        return cluster3;
    }

    public UUID getcluster4() {
        return cluster4;
    }

    public UUID getcluster5() {
        return cluster5;
    }

    public UUID getcluster0() {
        return cluster0;
    }
}