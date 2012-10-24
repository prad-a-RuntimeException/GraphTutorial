package com.exitcode.graph.applications;

import com.exitcode.graph.Graph;
import com.exitcode.graph.GraphBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Date: 10/23/12
 * Time: 10:45 AM
 */
public class TwoColoringGraphTest {


    Graph undirectedGraph;
    TwoColoringGraph twoColoredGraph;

    @Before
    public void generateGraph() {
        /* undirectedGraph = GraphBuilder.generateGraph(
 new String[]{"011010", "101010", "110100", "001000", "110000", "000000"}, false);*/
        undirectedGraph = GraphBuilder.generateGraph(new String[]{"01", "10"}, false);
        twoColoredGraph = new TwoColoringGraph();
    }

    @Test
    public void testTwoColor() throws Exception {
        boolean isPossible = twoColoredGraph.twoColor(undirectedGraph);
        assertTrue(!isPossible);
    }
}
