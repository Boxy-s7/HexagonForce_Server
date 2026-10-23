package de.jamilo.hexagon.server.domain.world;

public enum ClusterRole {
    o_0(0, HexagonState.OWNED, -1),
    w_0(0, HexagonState.WILD, -1), w_1(1, HexagonState.WILD, -1), w_2(2, HexagonState.WILD, -1), w_3(3, HexagonState.WILD, -1), w_4(4, HexagonState.WILD, -1), w_5(5, HexagonState.WILD, -1),
    u_0(0, HexagonState.UNKNOWN, 0), u_1(1, HexagonState.UNKNOWN, -1), u_2(2, HexagonState.UNKNOWN, 1), u_3(3, HexagonState.UNKNOWN, -1), u_4(4, HexagonState.UNKNOWN, 2), u_5(5, HexagonState.UNKNOWN, -1), u_6(6, HexagonState.UNKNOWN, 3), u_7(7, HexagonState.UNKNOWN, -1), u_8(8, HexagonState.UNKNOWN, 4), u_9(9, HexagonState.UNKNOWN, -1), u_10(10, HexagonState.UNKNOWN, 5), u_11(11, HexagonState.UNKNOWN, -1),
    s_0(0, HexagonState.SPECIAL, -1), s_1(1, HexagonState.SPECIAL, -1), s_2(2, HexagonState.SPECIAL, -1), s_3(3, HexagonState.SPECIAL, -1), s_4(4, HexagonState.SPECIAL, -1), s_5(5, HexagonState.SPECIAL, -1);

    public final int index;
    public final HexagonState initialState;
    public final int spawnPoint;
    private ClusterRole(int index, HexagonState initialState, int spawnPoint){
        this.index = index;
        this.initialState = initialState;
        this.spawnPoint = spawnPoint;
    }

    //public int getNumber()
}
