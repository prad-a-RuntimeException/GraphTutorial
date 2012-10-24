package com.exitcode.graph;

import java.util.List;
import java.util.Map;

/**
 * Date: 10/22/12
 * Time: 3:06 PM
 * General Graph data structure based on Adjacency list. This structure will suffice for
 * general Traversal algorithms on unweighted graphs.
 */
public class Graph {

    // For some traversal applications, we need to maintain all the three graph states.
    public enum GraphState {
        Undiscovered, Discovered, Processed
    }

    // For simple cases this will do.
    public enum BinomialGraphState {
        Undiscovered, Processed
    }


    private final int nEdges;
    private final int nVertices;
    private final int degreeOfGraph;
    private final Map<Integer, List<Integer>> edges;
    private final boolean isDirected;

    public Graph(int nEdges, int nVertices, int degreeOfGraph, Map<Integer, List<Integer>> edges, boolean directed) {
        this.nEdges = nEdges;
        this.nVertices = nVertices;
        this.degreeOfGraph = degreeOfGraph;
        this.edges = edges;
        isDirected = directed;
    }

    public int getnEdges() {
        return nEdges;
    }

    public int getnVertices() {
        return nVertices;
    }

    public int getDegreeOfGraph() {
        return degreeOfGraph;
    }

    public Map<Integer, List<Integer>> getEdges() {
        return edges;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public boolean isEmpty() {
        return (nVertices == 0);
    }
}
