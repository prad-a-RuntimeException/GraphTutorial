package com.exitcode.graph.traversals;

import com.exitcode.graph.Graph;

/**
 * Date: 10/23/12
 * Time: 10:20 AM
 */
public abstract class Traversal {

    public static final int NO_TARGET = -1;

    public abstract boolean traverse(Graph graph, int startVertex, int endVertex);

    public abstract void processEdge(int presentNode, int adjEdge);

    public abstract void processVertex(final int presentNode);

    public abstract boolean checkVertexTerminalCondition(int presentNode, int endVertex);

    public abstract boolean checkEdgeTerminalCondition(int a, int b);
}
