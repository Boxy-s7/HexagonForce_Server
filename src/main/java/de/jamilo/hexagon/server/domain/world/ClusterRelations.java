package de.jamilo.hexagon.server.domain.world;

import java.util.HashMap;
import java.util.Map;

public class ClusterRelations {

    public final static Map<ClusterRole, ClusterRelation[]> relations = new HashMap<>(
            Map.ofEntries(
                    Map.entry(ClusterRole.o_0, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.w_0, 3),
                            new ClusterRelation(-1, ClusterRole.w_1, 4),
                            new ClusterRelation(-1, ClusterRole.w_2, 5),
                            new ClusterRelation(-1, ClusterRole.w_3, 0),
                            new ClusterRelation(-1, ClusterRole.w_4, 1),
                            new ClusterRelation(-1, ClusterRole.w_5, 2)
                    }),
                    Map.entry(ClusterRole.w_0, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.u_0, 3),
                            new ClusterRelation(-1, ClusterRole.u_1, 4),
                            new ClusterRelation(-1, ClusterRole.w_1, 5),
                            new ClusterRelation(-1, ClusterRole.o_0, 0),
                            new ClusterRelation(-1, ClusterRole.w_5, 1),
                            new ClusterRelation(-1, ClusterRole.u_11, 2)
                    }),
                    Map.entry(ClusterRole.w_1, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.u_1, 3),
                            new ClusterRelation(-1, ClusterRole.u_2, 4),
                            new ClusterRelation(-1, ClusterRole.u_3, 5),
                            new ClusterRelation(-1, ClusterRole.w_2, 0),
                            new ClusterRelation(-1, ClusterRole.o_0, 1),
                            new ClusterRelation(-1, ClusterRole.w_0, 2)
                    }),
                    Map.entry(ClusterRole.w_2, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.w_1, 3),
                            new ClusterRelation(-1, ClusterRole.u_3, 4),
                            new ClusterRelation(-1, ClusterRole.u_4, 5),
                            new ClusterRelation(-1, ClusterRole.u_5, 0),
                            new ClusterRelation(-1, ClusterRole.w_3, 1),
                            new ClusterRelation(-1, ClusterRole.o_0, 2)
                    }),
                    Map.entry(ClusterRole.w_3, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.o_0, 3),
                            new ClusterRelation(-1, ClusterRole.w_2, 4),
                            new ClusterRelation(-1, ClusterRole.u_5, 5),
                            new ClusterRelation(-1, ClusterRole.u_6, 0),
                            new ClusterRelation(-1, ClusterRole.u_7, 1),
                            new ClusterRelation(-1, ClusterRole.w_4, 2)
                    }),
                    Map.entry(ClusterRole.w_4, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.w_5, 3),
                            new ClusterRelation(-1, ClusterRole.o_0, 4),
                            new ClusterRelation(-1, ClusterRole.w_3, 5),
                            new ClusterRelation(-1, ClusterRole.u_7, 0),
                            new ClusterRelation(-1, ClusterRole.u_8, 1),
                            new ClusterRelation(-1, ClusterRole.u_9, 2)
                    }),
                    Map.entry(ClusterRole.w_5, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.u_11, 3),
                            new ClusterRelation(-1, ClusterRole.w_0, 4),
                            new ClusterRelation(-1, ClusterRole.o_0, 5),
                            new ClusterRelation(-1, ClusterRole.w_4, 0),
                            new ClusterRelation(-1, ClusterRole.u_9, 1),
                            new ClusterRelation(-1, ClusterRole.u_10, 2)
                    }),
                    Map.entry(ClusterRole.u_0, new ClusterRelation[] {
                            new ClusterRelation(0, ClusterRole.u_6, 3),
                            new ClusterRelation(-1, ClusterRole.s_0, 4),
                            new ClusterRelation(-1, ClusterRole.u_1, 5),
                            new ClusterRelation(-1, ClusterRole.w_0, 0),
                            new ClusterRelation(-1, ClusterRole.u_11, 1),
                            new ClusterRelation(0, ClusterRole.s_3, 2)
                    }),
                    Map.entry(ClusterRole.u_1, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.s_0, 3),
                            new ClusterRelation(1, ClusterRole.s_4, 4),
                            new ClusterRelation(-1, ClusterRole.u_2, 5),
                            new ClusterRelation(-1, ClusterRole.w_1, 0),
                            new ClusterRelation(-1, ClusterRole.w_0, 1),
                            new ClusterRelation(-1, ClusterRole.u_0, 2)
                    }),
                    Map.entry(ClusterRole.u_2, new ClusterRelation[] {
                            new ClusterRelation(1, ClusterRole.s_4, 3),
                            new ClusterRelation(1, ClusterRole.u_8, 4),
                            new ClusterRelation(-1, ClusterRole.s_1, 5),
                            new ClusterRelation(-1, ClusterRole.u_3, 0),
                            new ClusterRelation(-1, ClusterRole.w_4, 1),
                            new ClusterRelation(-1, ClusterRole.u_1, 2)
                    }),
                    Map.entry(ClusterRole.u_3, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.u_2, 3),
                            new ClusterRelation(-1, ClusterRole.s_1, 4),
                            new ClusterRelation(2, ClusterRole.s_5, 5),
                            new ClusterRelation(-1, ClusterRole.u_4, 0),
                            new ClusterRelation(-1, ClusterRole.w_2, 1),
                            new ClusterRelation(-1, ClusterRole.w_1, 2)
                    }),
                    Map.entry(ClusterRole.u_4, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.u_3, 3),
                            new ClusterRelation(2, ClusterRole.s_5, 4),
                            new ClusterRelation(2, ClusterRole.u_10, 5),
                            new ClusterRelation(-1, ClusterRole.s_2, 0),
                            new ClusterRelation(-1, ClusterRole.u_5, 1),
                            new ClusterRelation(-1, ClusterRole.w_2, 2)
                    }),
                    Map.entry(ClusterRole.u_5, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.w_2, 3),
                            new ClusterRelation(-1, ClusterRole.u_4, 4),
                            new ClusterRelation(-1, ClusterRole.s_2, 5),
                            new ClusterRelation(3, ClusterRole.s_0, 0),
                            new ClusterRelation(-1, ClusterRole.u_6, 1),
                            new ClusterRelation(-1, ClusterRole.w_3, 2)
                    }),
                    Map.entry(ClusterRole.u_6, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.w_3, 3),
                            new ClusterRelation(-1, ClusterRole.w_5, 4),
                            new ClusterRelation(3, ClusterRole.s_0, 5),
                            new ClusterRelation(3, ClusterRole.u_0, 0),
                            new ClusterRelation(-1, ClusterRole.s_3, 1),
                            new ClusterRelation(-1, ClusterRole.u_7, 2)
                    }),
                    Map.entry(ClusterRole.u_7, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.w_4, 3),
                            new ClusterRelation(-1, ClusterRole.w_3, 4),
                            new ClusterRelation(-1, ClusterRole.u_6, 5),
                            new ClusterRelation(-1, ClusterRole.s_3, 0),
                            new ClusterRelation(4, ClusterRole.s_1, 1),
                            new ClusterRelation(-1, ClusterRole.u_8, 2)
                    }),
                    Map.entry(ClusterRole.u_8, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.u_9, 3),
                            new ClusterRelation(-1, ClusterRole.w_4, 4),
                            new ClusterRelation(-1, ClusterRole.u_7, 5),
                            new ClusterRelation(4, ClusterRole.s_1, 0),
                            new ClusterRelation(4, ClusterRole.u_2, 1),
                            new ClusterRelation(-1, ClusterRole.s_4, 2)
                    }),
                    Map.entry(ClusterRole.u_9, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.u_10, 3),
                            new ClusterRelation(-1, ClusterRole.w_5, 4),
                            new ClusterRelation(-1, ClusterRole.w_4, 5),
                            new ClusterRelation(-1, ClusterRole.u_8, 0),
                            new ClusterRelation(-1, ClusterRole.s_4, 1),
                            new ClusterRelation(5, ClusterRole.s_2, 2)
                    }),
                    Map.entry(ClusterRole.u_10, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.s_5, 3),
                            new ClusterRelation(-1, ClusterRole.u_11, 4),
                            new ClusterRelation(-1, ClusterRole.w_5, 5),
                            new ClusterRelation(-1, ClusterRole.u_9, 0),
                            new ClusterRelation(5, ClusterRole.s_2, 1),
                            new ClusterRelation(5, ClusterRole.u_4, 2)
                    }),
                    Map.entry(ClusterRole.u_11, new ClusterRelation[] {
                            new ClusterRelation(0, ClusterRole.s_3, 3),
                            new ClusterRelation(-1, ClusterRole.u_0, 4),
                            new ClusterRelation(-1, ClusterRole.w_0, 5),
                            new ClusterRelation(-1, ClusterRole.w_5, 0),
                            new ClusterRelation(-1, ClusterRole.u_10, 1),
                            new ClusterRelation(-1, ClusterRole.s_5, 2)
                    }),
                    Map.entry(ClusterRole.s_0, new ClusterRelation[] {
                            new ClusterRelation(0, ClusterRole.u_5, 3),
                            new ClusterRelation(0, ClusterRole.s_2, 4),
                            new ClusterRelation(1, ClusterRole.s_4, 5),
                            new ClusterRelation(-1, ClusterRole.u_1, 0),
                            new ClusterRelation(-1, ClusterRole.u_0, 1),
                            new ClusterRelation(0, ClusterRole.u_6, 2)
                    }),
                    Map.entry(ClusterRole.s_1, new ClusterRelation[] {
                            new ClusterRelation(1, ClusterRole.u_8, 3),
                            new ClusterRelation(1, ClusterRole.u_7, 4),
                            new ClusterRelation(1, ClusterRole.s_3, 5),
                            new ClusterRelation(2, ClusterRole.s_5, 0),
                            new ClusterRelation(-1, ClusterRole.u_3, 1),
                            new ClusterRelation(-1, ClusterRole.u_2, 2)
                    }),
                    Map.entry(ClusterRole.s_2, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.u_4, 3),
                            new ClusterRelation(2, ClusterRole.u_10, 4),
                            new ClusterRelation(2, ClusterRole.u_9, 5),
                            new ClusterRelation(2, ClusterRole.s_4, 0),
                            new ClusterRelation(3, ClusterRole.s_0, 1),
                            new ClusterRelation(-1, ClusterRole.u_5, 2)
                    }),
                    Map.entry(ClusterRole.s_3, new ClusterRelation[] {
                            new ClusterRelation(-1, ClusterRole.u_7, 3),
                            new ClusterRelation(-1, ClusterRole.u_6, 4),
                            new ClusterRelation(3, ClusterRole.u_0, 5),
                            new ClusterRelation(3, ClusterRole.u_11, 0),
                            new ClusterRelation(3, ClusterRole.s_5, 1),
                            new ClusterRelation(4, ClusterRole.s_1, 2)
                    }),
                    Map.entry(ClusterRole.s_4, new ClusterRelation[] {
                            new ClusterRelation(5, ClusterRole.s_2, 3),
                            new ClusterRelation(-1, ClusterRole.u_9, 4),
                            new ClusterRelation(-1, ClusterRole.u_8, 5),
                            new ClusterRelation(4, ClusterRole.u_2, 0),
                            new ClusterRelation(4, ClusterRole.u_1, 1),
                            new ClusterRelation(4, ClusterRole.s_0, 2)
                    }),
                    Map.entry(ClusterRole.s_5, new ClusterRelation[] {
                            new ClusterRelation(5, ClusterRole.s_1, 3),
                            new ClusterRelation(0, ClusterRole.s_3, 4),
                            new ClusterRelation(-1, ClusterRole.u_11, 5),
                            new ClusterRelation(-1, ClusterRole.u_10, 0),
                            new ClusterRelation(5, ClusterRole.u_4, 1),
                            new ClusterRelation(5, ClusterRole.u_3, 2)
                    })));
}
