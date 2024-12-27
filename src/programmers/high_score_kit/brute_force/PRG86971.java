package programmers.high_score_kit.brute_force;

import java.util.*;

public class PRG86971 {
    private static int totalNode;
    private static List<List<Integer>> graph;
    private static int[] subTreeSizes;
    private static int answer = Integer.MAX_VALUE;

    public int solution(int n, int[][] wires) {
        totalNode = n;
        graph = new ArrayList<>();

        for (int i = 0; i <= totalNode; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] w : wires) {
            int from = w[0];
            int to = w[1];

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        subTreeSizes = new int[totalNode + 1];

        setSubTreeSizes(1, -1);

        return answer;
    }

    public void setSubTreeSizes(int node, int parent) {
        subTreeSizes[node] = 1;

        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                setSubTreeSizes(neighbor, node);
                subTreeSizes[node] += subTreeSizes[neighbor];

                cutAndCount(neighbor);
            }
        }
    }

    private void cutAndCount(int node) {
        int firstGroupSize = subTreeSizes[node];
        int secondGroupSize = totalNode - firstGroupSize;

        answer = Math.min(answer, Math.abs(firstGroupSize - secondGroupSize));
    }
}
