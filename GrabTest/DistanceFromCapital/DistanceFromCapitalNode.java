package GrabTest;

import java.util.*;

public class DistanceFromCapitalNode {


    /**
     * This solution has been deviced by me although it has not met the code test time complexity of o(M),
     * it solves the problem.
     * Concept is find the capital node and start traversing from it,
     * its first level children will have the distance from capital as 1 and proceed further down while you keep
     * maintaining the distances
     * <p>
     * This is a graph traversal while maintaining distance with each node
     * <p>
     * I am yet to think on the more optimized solution.
     *
     * @param T
     * @return
     */
    public static int[] solution(int[] T) {
        List<Set<Integer>> adj = new ArrayList<>();
        int[] res = new int[T.length];
        int capital = -1;

        for (int i = 0; i < T.length; i++) adj.add(new HashSet<>());
        for (int i = 0; i < T.length; i++) {
            if (T[i] == i) {
                capital = i;
                continue;
            }
            adj.get(i).add(T[i]);
            adj.get(T[i]).add(i);
        }
        if (capital == -1) {
            return new int[0];
        }
        Queue<DisNode> queue = new LinkedList<>();
        queue.offer(new DisNode(capital, 0));
        List<DisNode> nodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            DisNode poll = queue.poll();
            nodes.add(poll);
            int node = poll.node;
            int dis = poll.disFromCap;

            Set<Integer> values = adj.get(node);
            for (int value : values) {
                adj.get(value).remove(node);
                queue.offer(new DisNode(value, dis + 1));
            }
        }

        for (DisNode d : nodes) {
            if (d.node != capital)
                res[d.disFromCap -1]++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] T = {9, 1, 4, 9, 0, 4, 8, 9, 0, 1};
        int[] res = solution(T);
        System.out.println(res);
    }

}

class DisNode {
    int node;
    int disFromCap;

    DisNode(int node, int disFromCap) {
        this.node = node;
        this.disFromCap = disFromCap;
    }
}
