package com.swetha.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node<T> implements Comparable<Node>{
    private T data;
    private Integer distance;
    private Node previous;
    private Map<T, Integer> neighbours;

    public Node(T data) {
        this.data = data;
        this.neighbours = new HashMap<>();
    }

    public T getData() {
        return data;
    }

    public Map<T, Integer> getNeighbours() {
        return neighbours;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Path path(Node startNode,DirectedGraph graph) {
        List<T> nodes = new LinkedList<T>();
        int distance = 0;
        Node<T> currentNode = this;

        if (currentNode.getPrevious() == null) {
            return new Path(nodes, distance);
        } else {
            nodes.add(currentNode.getData());

            while (!currentNode.equals(currentNode.getPrevious())) {
                distance += graph.weightForEdge(currentNode.getPrevious().getData(), currentNode.getData());

                currentNode = currentNode.getPrevious();
                nodes.add(0, currentNode.getData());

                if (currentNode.equals(startNode)) {
                    break;
                }
            }
            return new Path(nodes, distance);
        }
    }

    /**
     * This is specifically used in dijikstra's algorithm. Priority queue uses this comparision to
     * store/poll the elements.
     * @param other
     * @return
     */
    public int compareTo(Node other) {
        return distance.compareTo(other.getDistance());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

