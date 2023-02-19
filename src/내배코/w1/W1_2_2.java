package 내배코.w1;

public class W1_2_2 {
    public int solution(int story) {
        return move(story);
    }

    // 재귀 함수
    public int move(int floor) {
        if (floor <= 1) return floor; // 0이나 1층이면 마법의 돌은 0개나 1개만 사용하면 됨

        // 몇 자리의 수가 오든 10으로 나눈 나머지로 움직임을 계획하면 됨
        int toMove = floor % 10;

        // 현재의 움직임을 마치고 나서 남은 층
        int rest = floor / 10;

        // toMove 만큼 움직이고 나서 나머지 층에 대해 움직이는 것
        int case1 = toMove + move(rest);
        /*
        toMove의 10의 보수 만큼 움직이고 나서 (나머지 층 + 1)에 대해 움직이는 것
        (-> 10의 보수 만큼 움직였다는 것은 나머지 층에 올림을 해주는 것이므로 +1 해주어야함)
         */
        int case2 = (10 - toMove) + move(rest + 1);

        return Math.min(case1, case2);
    }
}
