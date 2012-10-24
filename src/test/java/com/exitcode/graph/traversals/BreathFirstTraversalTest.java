package com.exitcode.graph.traversals;

import com.exitcode.graph.Graph;
import com.exitcode.graph.GraphBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Date: 10/22/12
 * Time: 3:41 PM
 */
public class BreathFirstTraversalTest {


    Graph undirectedGraph;
    Traversal breathFirstTraversal;

    @Before
    public void generateGraph() {
        undirectedGraph = GraphBuilder.generateGraph(
                new String[]{"011010", "101010", "110100", "001000", "110000", "000000"}, false);
        breathFirstTraversal = new BreathFirstTraversal();
    }

    /**
     * The simplest application of BFS, finding if path exists between the source and target vertex
     */
    @Test
    public void testTraverse_PathExists() {
        boolean pathExists = breathFirstTraversal.traverse(undirectedGraph, 0, 4);
        assertTrue(pathExists);
    }


    @Test
    public void testTraverse_PathDoesNotExist() {
        boolean pathExists = breathFirstTraversal.traverse(undirectedGraph, 0, 5);
        assertTrue(!pathExists);
    }


}
