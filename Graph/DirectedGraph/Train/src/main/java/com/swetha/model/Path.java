package com.swetha.model;

import java.util.ArrayList;
import java.util.List;

public class Path<T> {
    private List<T> nodes;
    private int distance;

    public Path() {
        this.nodes = new ArrayList<T>();
        this.distance = 0;
    }

    public Path(List<T> nodes, int length) {
        this.nodes = nodes;
        this.distance = length;
    }

    public boolean removeLastNode(int weight) {
        if (!this.nodes.isEmpty()) {
            distance -= weight;
            this.nodes.remove(this.nodes.size() - 1);
            return true;
        } else {
            return false;
        }
    }

    public boolean appendNode(T node, int weight) {
        distance += weight;
        return this.nodes.add(node);
    }

    public List<T> getNodes() {
        return nodes;
    }

    public T last() {
        if (nodes.isEmpty()) {
            return null;
        } else {
            return nodes.get(nodes.size() - 1);
        }
    }

    public int distance() {
        return distance;
    }

    public int hopCount() {
        return Math.max(0, this.nodes.size() - 1);
    }

}

