package de.jamilo.hexagon.server.persistence.entity;

import java.util.UUID;

import de.jamilo.hexagon.server.domain.world.MegaClusterState;
import jakarta.persistence.*;

@Entity
@Table(name = "world_mega_cluster")
public class MegaClusterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "mega_cluster_id")
    private UUID megaClusterId;

    @Column(name = "mega_cluster_state")
    @Enumerated(EnumType.STRING)
    private MegaClusterState state;

    @Column(name = "c_0") private UUID center0;
    @Column(name = "p_0") private UUID player0;
    @Column(name = "p_1") private UUID player1;
    @Column(name = "p_2") private UUID player2;
    @Column(name = "p_3") private UUID player3;
    @Column(name = "p_4") private UUID player4;
    @Column(name = "p_5") private UUID player5;

    public UUID getMegaClusterId() { return megaClusterId; }
    public MegaClusterState getState() { return state; }
    public void setState(MegaClusterState state) { this.state = state; }
    public UUID getCenter0() { return center0; }
    public void setCenter0(UUID value) { center0 = value; }
    public UUID getPlayer0() { return player0; }
    public void setPlayer0(UUID value) { player0 = value; }
    public UUID getPlayer1() { return player1; }
    public void setPlayer1(UUID value) { player1 = value; }
    public UUID getPlayer2() { return player2; }
    public void setPlayer2(UUID value) { player2 = value; }
    public UUID getPlayer3() { return player3; }
    public void setPlayer3(UUID value) { player3 = value; }
    public UUID getPlayer4() { return player4; }
    public void setPlayer4(UUID value) { player4 = value; }
    public UUID getPlayer5() { return player5; }
    public void setPlayer5(UUID value) { player5 = value; }
}
