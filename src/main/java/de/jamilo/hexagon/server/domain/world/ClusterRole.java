package de.jamilo.hexagon.server.domain.world;

public enum ClusterRole {

    c_0(0, ClusterState.SPECIAL),
    p_0(0, ClusterState.SPAWNING);

    public final int index;
    public final ClusterState initialState;
    private ClusterRole(int index, ClusterState initialState){
        this.index = index;
        this.initialState = initialState;
    }
}
