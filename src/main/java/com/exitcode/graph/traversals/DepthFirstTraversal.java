package com.exitcode.graph.traversals;

import com.exitcode.graph.Graph;

import java.util.Arrays;
import java.util.List;

/**
 * Date: 10/23/12
 * Time: 1:43 PM
 */
public class DepthFirstTraversal extends Traversal {


    private Graph.GraphState[] graphStates;
    private int[] entryTimes;
    private int[] exitTimes;
    private int[] parents;
    private volatile int entryTime = 0;

    @Override
    public boolean traverse(Graph graph, int startVertex, int endVertex) {
        graphStates = new Graph.GraphState[graph.getnVertices()];
        entryTimes = new int[graph.getnVertices()];
        exitTimes = new int[graph.getnVertices()];
        parents = new int[graph.getnVertices()];


        Arrays.fill(graphStates, Graph.GraphState.Undiscovered);
        return dfs(graph, startVertex, endVertex);
    }

    private boolean dfs(Graph graph, int startVertex, int endVertex) {

        graphStates[startVertex] = Graph.GraphState.Discovered;
        entryTime = entryTime + 1;
        entryTimes[startVertex] = entryTime;
        processVertex(startVertex);
        if (checkVertexTerminalCondition(startVertex, endVertex)) {
            return true;
        }
        List<Integer> adjEdges = graph.getEdges().get(startVertex);
        boolean pathExists = false;
        for (int adjEdge : adjEdges) {
            if (graphStates[adjEdge] == Graph.GraphState.Undiscovered) {
                // not needed for cycle calculation.
                //processEdge(startVertex, adjEdge);
                parents[adjEdge] = startVertex;
                boolean dfs = dfs(graph, adjEdge, endVertex);
                if (dfs) {
                    pathExists = true;
                }
            } else if (graphStates[adjEdge] != Graph.GraphState.Processed || graph.isDirected()) {
                processEdge(startVertex, adjEdge);
            }
        }

        entryTime = entryTime + 1;
        exitTimes[startVertex] = entryTime;
        // The graph is marked as processed once all its childrens are visited.
        graphStates[startVertex] = Graph.GraphState.Processed;
        return pathExists;


    }

    @Override
    public void processEdge(int presentNode, int adjEdge) {

        System.out.printf("Processing Edge  %d --> %d \n", presentNode, adjEdge);
        if (parents[presentNode] != adjEdge) {
            System.out.printf("Cycle detected between edges %d --> %d \n", presentNode, adjEdge);
        }
    }

    @Override
    public void processVertex(int presentNode) {
        System.out.printf("Processing vertex %d \n", presentNode);
    }

    @Override
    public boolean checkVertexTerminalCondition(int presentNode, int endVertex) {
        return presentNode == endVertex;
    }

    @Override
    public boolean checkEdgeTerminalCondition(int a, int b) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
