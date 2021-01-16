package leetcode._101_200._133_clone_graph;

import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 *
 * Example:
 * 1 ---- 2
 * |      |
 * |      |
 * 4 ---- 3
 *
 * Input:
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 *
 * Explanation:
 * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 *
 *
 * Note:
 *
 * The number of nodes will be between 1 and 100.
 * The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 * Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 * You must return the copy of the given node as a reference to the cloned graph.
 *
 *
 */
public class CloneGraph {

    public static void main(String[] args) {
        CloneGraph graph = new CloneGraph();
        Node root = new Node().buildGraph();

        List<Node> nodes = new CloneGraph().getNodes(root);

        System.out.println("BFS traversal of a graph before cloning");

        printNodes(nodes);

        System.out.println();

        Node newRoot = new CloneGraph().cloneGraph(root);

        System.out.println("BFS traversal of a graph after cloning");

        List<Node> newNodes = new CloneGraph().getNodes(newRoot);

        printNodes(nodes);

    }

    private static void printNodes(List<Node> nodes) {
        for (Node n : nodes) {
            System.out.println("node: " + n.val);
            for (Node neighbor : n.neighbors) {
                System.out.println("neighbor: " + neighbor.val);
            }
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // bfs nodes
        List<Node> nodes = getNodes(node);

        // copy to map
        HashMap<Node, Node> map = new HashMap<>();
        for (Node n : nodes) {
            map.computeIfAbsent(n, k -> new Node(k.val, new ArrayList<>()));
        }

        // copy edges
        for (Node n : nodes) {
            Node newNode = map.get(n);

            for (Node neighbour : n.neighbors) {
                Node newNeighbour = map.get(neighbour);

                newNode.neighbors.add(newNeighbour);
            }
        }

        return map.get(node);
    }

    private List<Node> getNodes(Node node) {
        Queue<Node> queue = new LinkedList<>();

        Set<Node> set = new HashSet<>();

        queue.add(node);

        set.add(node);

        while (!queue.isEmpty()) {
            Node head = queue.poll();

            for (Node neighbor : head.neighbors) {
                if (set.contains(neighbor)) {
                    continue;
                }

                queue.add(neighbor);

                set.add(neighbor);
            }
        }

        return new ArrayList<>(set);
    }

}
