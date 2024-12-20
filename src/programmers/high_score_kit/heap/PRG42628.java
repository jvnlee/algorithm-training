package programmers.high_score_kit.heap;

import java.util.*;

public class PRG42628 {
    public int[] solution(String[] operations) {
        DoubleEndedPriorityQueue depq = new DoubleEndedPriorityQueue();

        for (String op : operations) {
            String[] split = op.split(" ");
            String command = split[0];
            int operand = Integer.parseInt(split[1]);

            if (command.equals("I")) {
                depq.add(operand);
            } else {
                if (operand == 1) {
                    depq.removeMax();
                } else {
                    depq.removeMin();
                }
            }
        }

        return new int[]{depq.getMax(), depq.getMin()};
    }

    private class DoubleEndedPriorityQueue {
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        private Map<Integer, Integer> map = new HashMap<>();

        public void add(int element) {
            map.put(element, map.getOrDefault(element, 0) + 1);
            minHeap.offer(element);
            maxHeap.offer(element);
        }

        public void removeMin() {
            cleanMinHeap();

            if (isEmpty()) {
                return;
            }

            int min = minHeap.poll();

            if (map.get(min) == 1) {
                map.remove(min);
            } else {
                map.put(min, map.get(min) - 1);
            }
        }

        public void removeMax() {
            cleanMaxHeap();

            if (isEmpty()) {
                return;
            }

            int max = maxHeap.poll();

            if (map.get(max) == 1) {
                map.remove(max);
            } else {
                map.put(max, map.get(max) - 1);
            }
        }

        public int getMin() {
            cleanMinHeap();

            return minHeap.isEmpty() ? 0 : minHeap.peek();
        }

        public int getMax() {
            cleanMaxHeap();

            return maxHeap.isEmpty() ? 0 : maxHeap.peek();
        }

        public void cleanMinHeap() {
            while (!minHeap.isEmpty() && !map.containsKey(minHeap.peek())) {
                minHeap.poll();
            }
        }

        public void cleanMaxHeap() {
            while (!maxHeap.isEmpty() && !map.containsKey(maxHeap.peek())) {
                maxHeap.poll();
            }
        }

        public boolean isEmpty() {
            return map.isEmpty();
        }
    }
}