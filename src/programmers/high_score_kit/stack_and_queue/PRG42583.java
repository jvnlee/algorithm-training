package programmers.high_score_kit.stack_and_queue;

import java.util.*;

public class PRG42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> truckQueue = new LinkedList<>();

        for (int w : truck_weights) {
            truckQueue.offer(w);
        }

        Queue<Integer> bridgeQueue = new LinkedList<>();

        for (int i = 0; i < bridge_length; i++) {
            bridgeQueue.offer(0);
        }

        int time = 0;
        int weightSum = 0;

        while (!bridgeQueue.isEmpty()) {
            time++;

            int bridgePolled = bridgeQueue.poll();
            weightSum -= bridgePolled;

            if (!truckQueue.isEmpty()) {
                if (weightSum + truckQueue.peek() <= weight) {
                    int truckPolled = truckQueue.poll();
                    bridgeQueue.offer(truckPolled);
                    weightSum += truckPolled;
                } else {
                    bridgeQueue.offer(0);
                }
            }
        }

        return time;
    }
}
