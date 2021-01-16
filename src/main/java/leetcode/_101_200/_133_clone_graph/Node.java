package leetcode._101_200._133_clone_graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }


    // Build the desired graph 
    public Node buildGraph() {
        /* 
            Note : All the edges are Undirected 
            Given Graph: 
            1--2 
            |  | 
            4--3 
        */
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());
        List<Node> v = new ArrayList<>();
        v.add(node2);
        v.add(node4);
        node1.neighbors = v;
        v = new ArrayList<Node>();
        v.add(node1);
        v.add(node3);
        node2.neighbors = v;
        v = new ArrayList<Node>();
        v.add(node2);
        v.add(node4);
        node3.neighbors = v;
        v = new ArrayList<Node>();
        v.add(node3);
        v.add(node1);
        node4.neighbors = v;
        return node1;
    }

}