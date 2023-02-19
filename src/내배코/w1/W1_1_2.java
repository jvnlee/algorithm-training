package 내배코.w1;

public class W1_1_2 {
    public int solution(int[][] cost, int[][] order) {
        int answer = 0;

        int maxMonth = 0;
        for (int[] o : order) {
            maxMonth = Math.max(maxMonth, o[0]);
        }

        int[] monthlyOrder = new int[maxMonth];

        int required = 0;
        int made = 0;

        for (int[] o : order) {
            monthlyOrder[o[0] - 1] += o[1];
            required += o[1];
        }

        // 최대 가격으로 생산하는 경우는 제외한 루프 (맨 마지막에 따로 계산)
        for (int i = 0; i < cost.length - 1; i++) {
            if (required == 0 || made >= required) break;

            int price = cost[i][1];
            int maxAmount = cost[i + 1][0] - cost[i][0]; // 현재 가격으로 생산할 수 있는 최대량
            int rest = 0;

            for (int j = 0; j < monthlyOrder.length; j++) {
                if (required == 0 || made >= required) break;

                int thisMonthAmount = Math.min(maxAmount, required - made);

                answer += thisMonthAmount * price;
                made += thisMonthAmount;

                if (monthlyOrder[j] == 0) continue; // 납품하는 월이 아니라면 다음 달로 이동
                required -= monthlyOrder[j]; // 총 생산요구량에서 이번 달 납품량 차감

                int delivery = Math.min(made, monthlyOrder[j]); // 납품 가능한 양

                made -= delivery; // 지금까지 생산해놓은 물량에서 납품 가능한 양만큼 차감
                monthlyOrder[j] -= delivery; // 이번 달 납품량에서 납품 가능한 양만큼 차감
                rest += monthlyOrder[j]; // 현재 가격으로 생산해서 납품하고도 못채운 납품량은 다음 가격으로 생산해야함
            }

            required = rest;
            made = 0; // 현재 가격으로 생산한 것을 모두 다 납품하고도 납품량을 못채웠다는 의미이므로 생산량은 0으로 초기화해야함
        }

        // 모든 가격 구간에 대해서 생산 후 납품을 해봤는데도 못채운 양이 있다면 나머지는 최대 가격으로 생산해야함
        answer += required * cost[cost.length - 1][1];
        return answer;
    }
}
