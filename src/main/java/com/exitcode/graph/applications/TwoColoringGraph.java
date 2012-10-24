package com.exitcode.graph.applications;

import com.exitcode.graph.Graph;
import com.exitcode.graph.traversals.BreathFirstTraversal;
import com.exitcode.graph.traversals.Traversal;

import java.util.Arrays;

/**
 * Date: 10/23/12
 * Time: 10:14 AM
 * A general Application of breath first traversal, where we color the graph using a two colored scheme.
 * If we cannot do that, the program will inform its status.
 */
public class TwoColoringGraph extends BreathFirstTraversal {

    protected Color[] colors;

    private enum Color {
        One, Two, Uncolored;

        /**
         * Returns one even if the graph is uncolored.
         *
         * @return
         */
        private Color complement() {
            return (this == One) ? Two : One;
        }
    }

    public boolean twoColor(Graph graph) {
        if (graph == null || graph.isEmpty()) {
            return false;
        }
        colors = new Color[graph.getnVertices()];
        Arrays.fill(colors, Color.Uncolored);
        colors[0] = Color.One;
        return super.traverse(graph, 0, Traversal.NO_TARGET);
    }


    /**
     * No Vertex terminal condition.
     *
     * @param presentNode
     * @param endVertex
     * @return
     */
    @Override
    public boolean checkVertexTerminalCondition(int presentNode, int endVertex) {
        return false;
    }

    @Override
    public boolean checkEdgeTerminalCondition(int a, int b) {
        if (colors[a] == colors[b]) {
            return true;
        } else {
            colors[b] = colors[a].complement();
            return false;
        }


    }

    @Override
    public void processEdge(int presentNode, int adjEdge) {


    }
}
