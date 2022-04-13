package tech;

import java.util.*;

/*
* "You are given N randomly* generated connected graphs. Write an algorithm to merge them into a single randomly* connected graph with the smallest number of edges possible.

* 0
*
* 1-2
*
* 3-4
* \ \
* 5-6

new_graph. ng
0 .. n - 1
ng connect_to g[randmon(0 .. n - 1)]
ng.randmonNode connect_to g[randmon(0 .. n - 1)].randmonNode

0 1 2

1: 1 - 2

new_graph: 1

0 2 -> 2

new_graph.randmonNode connect_to 2.randmonNode

0
new_graph.randmonNode connect_to 0.randmonNode

*/
public class RandomGraph {

    public static void main(String[] args) {

    }

    private Graph connect(List<Graph> graphs) {
        Graph graph = new Graph();

        int count = 0;
        while (count < graphs.size()) {
            int rindex = new Random().nextInt(graphs.size() - 1 - count);
            Graph tempGraph = graphs.get(rindex);
            if (graph.nodes.isEmpty()) {
                graph.nodes.addAll(tempGraph.nodes);
                continue;
            }

            int nodeIndex = new Random().nextInt(graph.nodes.size());
            int targetNodeIndex = new Random().nextInt(tempGraph.nodes.size());

            Node node1 = graph.nodes.get(nodeIndex);
            Node node2 = tempGraph.nodes.get(targetNodeIndex);
            node1.neighbors.add(node2.val);
            node2.neighbors.add(node1.val);

            graph.nodes.addAll(tempGraph.nodes);

            updateGraph(graphs, rindex, count);

            count++;
        }

        return graph;
    }

    private void updateGraph(List<Graph> g, int rindex, int count) {
        Graph randomGraph = g.get(rindex);
        Graph lastGraph = g.get(g.size() - 1 - count);
        Graph temp = lastGraph;
        g.set(g.size() - 1 - count, randomGraph);
        g.set(rindex, temp);
    }

    static class Graph {
        List<Node> nodes = new ArrayList<>();

        public Graph() {
        }

    }

    static class Node {
        int val;
        List<Integer> neighbors = new ArrayList<>();

        public Node() {
        }
    }

}
