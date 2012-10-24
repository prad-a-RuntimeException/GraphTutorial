package com.exitcode.graph;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphBuilder {
    private int nEdges;
    private int nVertices;
    private int degreeOfGraph;
    private Map<Integer, List<Integer>> edges;
    private boolean directed;


    public static Graph generateGraph(final String[] edges, boolean isDirected) {
        GraphBuilder graphBuilder = new GraphBuilder();
        int degreeOfVertex = Integer.MIN_VALUE;
        int nVertices = 0;
        int nEdges = 0;
        Map<Integer, List<Integer>> thisEdges = new HashMap<Integer, List<Integer>>();
        int vertexNumber = 0;
        for (String edge : edges) {
            nVertices++;
            char[] adjEdges = edge.toCharArray();
            int thisVertexDegree = 0;
            for (int i = 0; i < adjEdges.length; i++) {
                char adjEdge = adjEdges[i];
                if (adjEdge == '1') {
                    thisVertexDegree++;
                    nEdges++;
                    insertEdge(thisEdges, vertexNumber, i);
                    if (!isDirected) {
                        insertEdge(thisEdges, i, vertexNumber);
                        nEdges++;
                    }
                }
            }
            if (thisVertexDegree > degreeOfVertex) {
                degreeOfVertex = thisVertexDegree;
            }
            vertexNumber++;
        }

        return graphBuilder.setDegreeOfGraph(degreeOfVertex)
                .setDirected(isDirected)
                .setEdges(thisEdges)
                .setnEdges(
                        nEdges)
                .setnVertices(nVertices).createGraph();
    }

    private static void insertEdge(Map<Integer, List<Integer>> thisEdges, int a, int b) {
        if (thisEdges.containsKey(a)) {
            if (thisEdges.get(a).contains(b)) {
                return;
            }
            thisEdges.get(a).add(b);
        } else {
            thisEdges.put(a, Lists.newArrayList(b));
        }
    }

    public GraphBuilder setnEdges(int nEdges) {
        this.nEdges = nEdges;
        return this;
    }


    public GraphBuilder setnVertices(int nVertices) {
        this.nVertices = nVertices;
        return this;
    }

    public GraphBuilder setDegreeOfGraph(int degreeOfGraph) {
        this.degreeOfGraph = degreeOfGraph;
        return this;
    }

    public GraphBuilder setEdges(Map<Integer, List<Integer>> edges) {
        this.edges = edges;
        return this;
    }

    public GraphBuilder setDirected(boolean directed) {
        this.directed = directed;
        return this;
    }

    public Graph createGraph() {
        return new Graph(nEdges, nVertices, degreeOfGraph, edges, directed);
    }
}