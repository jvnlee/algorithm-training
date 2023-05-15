package programmers;

public class PRG150368 {
    public final int[] dc = {90, 80, 70, 60};
    public int[] result;
    public int[][] userData;
    public int[] emoticonData;
    public int[] answer = new int[2];

    public int[] solution(int[][] users, int[] emoticons) {
        userData = users;
        emoticonData = emoticons;
        result = new int[emoticonData.length];

        permutation(0);

        return answer;
    }

    public void permutation(int cnt) {
        if (cnt == emoticonData.length) {
            solve(result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            result[cnt] = dc[i];
            permutation(cnt + 1);
        }
    }

    public void solve(int[] discounts) {
        int[] purchases = new int[userData.length];
        boolean[] hasJoined = new boolean[userData.length];

        for (int i = 0; i < userData.length; i++) {
            for (int j = 0; j < discounts.length; j++) {
                if (discounts[j] <= 100 - userData[i][0]) purchases[i] += (emoticonData[j] * discounts[j] / 100);
            }

            if (purchases[i] >= userData[i][1]) {
                purchases[i] = 0;
                hasJoined[i] = true;
            }
        }

        int totalPurchase = 0;
        int totalJoins = 0;

        for (int purchase : purchases) {
            totalPurchase += purchase;
        }

        for (boolean b : hasJoined) {
            if (b) totalJoins++;
        }

        if (answer[0] == totalJoins) {
            answer[1] = Math.max(answer[1], totalPurchase);
        } else if (answer[0] < totalJoins){
            answer[0] = totalJoins;
            answer[1] = totalPurchase;
        }
    }
}
