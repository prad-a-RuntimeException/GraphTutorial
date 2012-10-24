package com.exitcode.graph.examples;

import java.util.*;

/**
 * Date: 10/23/12
 * Time: 12:37 PM
 */
public class Escape {

    private enum Cell {Deadly, Harmful, None}


    public static void main(String[] args) {
        Escape escape = new Escape();
        int lowest = escape.lowest(new String[]{},
                new String[]{});
        System.out.println(lowest);
    }

    public int lowest(String[] harmful, String[] deadly) {

        Cell[][] grid = new Cell[501][501];
        // Mark harmful
        for (String s : harmful) {
            if (!s.isEmpty()) {
                String[] element = s.split(" ");
                int x1 = Integer.parseInt(element[0]);
                int x2 = Integer.parseInt(element[2]);
                int y1 = Integer.parseInt(element[1]);
                int y2 = Integer.parseInt(element[3]);
                for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                    for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                        grid[i][j] = Cell.Harmful;
                    }
                }
            }
        }

        //mark deadly
        for (String s : deadly) {
            if (!s.isEmpty()) {
                String[] element = s.split(" ");
                int x1 = Integer.parseInt(element[0]);
                int x2 = Integer.parseInt(element[2]);
                int y1 = Integer.parseInt(element[1]);
                int y2 = Integer.parseInt(element[3]);
                for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                    for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                        grid[i][j] = Cell.Deadly;
                    }
                }
            }
        }

        Queue<Node> vertices = new LinkedList<Node>();
        vertices.add(new Node(0, 0));
        List<Node> candidates = new ArrayList<Node>();
        grid[0][0] = Cell.Deadly;

        while (!vertices.isEmpty()) {
            Node presentNode = vertices.poll();
            // System.out.printf("%d %d \n", presentNode.x, presentNode.y);


            if (presentNode.x == 500 && presentNode.y == 500) {
                candidates.add(presentNode);
            }

            List<Node> adjNodes = new ArrayList<Node>();
            if (presentNode.x > 0 && grid[presentNode.x - 1][presentNode.y] != Cell.Deadly) {
                adjNodes.add(new Node(presentNode.x - 1, presentNode.y));
            }

            if (presentNode.y > 0 && grid[presentNode.x][presentNode.y - 1] != Cell.Deadly) {
                adjNodes.add(new Node(presentNode.x, presentNode.y - 1));
            }

            if (presentNode.x < 500 && grid[presentNode.x + 1][presentNode.y] != Cell.Deadly) {
                adjNodes.add(new Node(presentNode.x + 1, presentNode.y));
            }

            if (presentNode.y < 500 && grid[presentNode.x][presentNode.y + 1] != Cell.Deadly) {
                adjNodes.add(new Node(presentNode.x, presentNode.y + 1));
            }

            for (Node adjNode : adjNodes) {
                adjNode.cost = presentNode.cost;
                if (grid[adjNode.x][adjNode.y] == Cell.Harmful) {
                    adjNode.cost++;
                }
                grid[adjNode.x][adjNode.y] = Cell.Deadly;
                vertices.add(adjNode);
            }
        }

        if (candidates.size() == 0) {
            return -1;

        }

        Collections.sort(candidates);
        return candidates.get(candidates.size() - 1).cost;


    }


    private class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        private Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.cost = 0;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (x != node.x) return false;
            if (y != node.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        public int compareTo(Node o) {
            return new Integer(this.cost).compareTo(new Integer(o.cost));
        }
    }


}
