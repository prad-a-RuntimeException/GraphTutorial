package com.exitcode.graph.traversals;

import com.exitcode.graph.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date: 10/22/12
 * Time: 3:06 PM
 */
public class BreathFirstTraversal extends Traversal {

    private static Logger logger = LoggerFactory.getLogger(BreathFirstTraversal.class);


    private boolean bfs(Graph graph, int startVertex, int endVertex) {

        Queue<Integer> vertexQueue = new LinkedList<Integer>();
        vertexQueue.add(startVertex);
        Graph.BinomialGraphState[] graphStates = new Graph.BinomialGraphState[graph.getnVertices()];
        Arrays.fill(graphStates, Graph.BinomialGraphState.Undiscovered);
        while (!vertexQueue.isEmpty()) {
            int presentNode = vertexQueue.poll();
            graphStates[presentNode] = Graph.BinomialGraphState.Processed;
            if (checkVertexTerminalCondition(presentNode, endVertex))
                return true;
            List<Integer> adjEdges = graph.getEdges().get(presentNode);
            for (int adjEdge : adjEdges) {
                if (graphStates[adjEdge] != Graph.BinomialGraphState.Processed) {
                    if (checkEdgeTerminalCondition(presentNode, adjEdge)) {
                        return true;
                    }
                    processEdge(presentNode, adjEdge);
                    vertexQueue.add(adjEdge);
                } else if (graph.isDirected()) {
                    // This edge may not have been handled since the undirectedGraph is directed.
                    processEdge(presentNode, adjEdge);
                }
            }
        }

        return false;
    }

    @Override
    public boolean checkVertexTerminalCondition(int presentNode, int endVertex) {
        BreathFirstTraversal.logger.info("Processing vertex {}", presentNode);
        // Template method.
        if (presentNode == endVertex) {
            BreathFirstTraversal.logger.info("Found target vertex");
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEdgeTerminalCondition(int a, int b) {
        return false;
    }

    @Override
    public boolean traverse(Graph graph, int startVertex, int endVertex) {
        return bfs(graph, startVertex, endVertex);
    }

    @Override
    public void processEdge(int presentNode, int adjEdge) {
        //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    public void processVertex(final int presentNode) {

    }

}
