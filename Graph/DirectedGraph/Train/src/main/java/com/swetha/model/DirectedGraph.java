package com.swetha.model;

import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class DirectedGraph<T> {

    private Map<T, Node> graph;

    public DirectedGraph() {
        graph = new HashMap<>();
    }

    public void addNode(T node) {
        Node Node = new Node(node);
        if (!graph.containsKey(node)) {
            graph.put(node, Node);
        }
    }

    public boolean contains(T node) {
        return graph.containsKey(node);
    }

    public void addEdge(T start, T end, int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be >= 0");
        }
        graph.get(start).getNeighbours().put(end, weight);
    }

    public Set<T> getNeighbours(T node) {
        return graph.get(node).getNeighbours().keySet();
    }

    public boolean edgeExists(T start, T dest) {
        return graph.get(start).getNeighbours().containsKey(dest);
    }


    public Set<T> getNodes() {
        return graph.keySet();
    }

    public Node getNode(T key) {
        return graph.get(key);
    }

    /**
     * dijkstra's algorithm to find the shortest distance between a source and destination
     * uses priority queue using distance as the comparing measure
     *
     * @param start
     * @param end
     */
    public void dijkstra(String start, String end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        // Set distance to zero for initial node, and to infinity for all other.
        Set<T> nodes = getNodes();
        for (T n : nodes) {
            Node node = getNode(n);
            node.setDistance(Integer.MAX_VALUE);
            if (start.equals(n)) {
                node.setDistance(0);
                node.setPrevious(node);
            }
            queue.add(node);
        }

        // Compute distances
        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll(); // Node with shortest distance

            if (currentNode.getData().equals(end) && !currentNode.equals(currentNode.getPrevious())) {
                break;
            }

            for (T neighbour : getNeighbours(currentNode.getData())) {
                Node neighbourNode = graph.get(neighbour);

                final int alternateDist = currentNode.getDistance() + weightForEdge(currentNode.getData(), neighbour);
                if (alternateDist < neighbourNode.getDistance() || neighbourNode.equals(neighbourNode.getPrevious())) { // shorter path to neighbour found
                    queue.remove(neighbourNode);
                    neighbourNode.setDistance(alternateDist);
                    neighbourNode.setPrevious(currentNode);
                    queue.add(neighbourNode);
                }
            }
        }
    }

    public int dfs(T currentNode, Predicate<Path> terminate, Predicate<Path> condition, Path path) {
        int total = 0;
        if (condition.test(path)) {
            // Count path as valid
            total++;
        }
        for (T neighbour : getNeighbours(currentNode)) {
            path.appendNode(neighbour, weightForEdge(currentNode, neighbour));
            if (terminate.test(path)) {
                path.removeLastNode(weightForEdge(currentNode, neighbour));
                continue; // go to next neighbour
            } else {
                total += dfs(neighbour, terminate, condition, path);
            }
            path.removeLastNode(weightForEdge(currentNode, neighbour));
        }
        return total;
    }

    public int distance(List<T> nodes) throws Exception {
        int distance = 0;
        for (int i = 0; i < nodes.size() - 1; i++) {
            T start = nodes.get(i);
            T dest = nodes.get(i + 1);

            if (edgeExists(start, dest)) {
                distance += weightForEdge(start, dest);
            } else {
                throw new Exception("NO SUCH ROUTE");
            }
        }
        return distance;
    }

    public Integer weightForEdge(T start, T dest) {
        return (Integer) graph.get(start).getNeighbours().get(dest);
    }

}

