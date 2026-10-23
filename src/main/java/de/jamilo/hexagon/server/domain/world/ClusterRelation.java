package de.jamilo.hexagon.server.domain.world;

public class ClusterRelation {
    int targetCluster;
    public int getTargetCluster() {
        return targetCluster;
    }

    public void setTargetCluster(int targetCluster) {
        this.targetCluster = targetCluster;
    }

    ClusterRole targetRole;
    public ClusterRole getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(ClusterRole targetRole) {
        this.targetRole = targetRole;
    }

    int targetIndex;

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public ClusterRelation(int targetCluster, ClusterRole targetRole, int targetIndex) {
        this.targetCluster = targetCluster;
        this.targetRole = targetRole;
        this.targetIndex = targetIndex;
    }
}
