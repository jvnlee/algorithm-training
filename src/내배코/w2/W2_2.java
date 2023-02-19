package 내배코.w2;

import java.util.*;

public class W2_2 {
    public int solution(int[] people, int[] tshirts) {
        int answer = 0;

        Arrays.sort(people);
        Arrays.sort(tshirts);

        int idx = tshirts.length - 1; // 가장 큰 티셔츠의 인덱스
        for (int i = people.length - 1; i >= 0 && idx >= 0; i--) { // 가장 사이즈가 큰 사람부터 순회
            if (tshirts[idx] >= people[i]) { // 입을 수 있는 경우
                answer++;
                idx--; // 티셔츠 소진
            }
        }
        return answer;
    }

    /*
    2주차(중간고사) 2번 문제

    풀이 로직 자체는 비교적 단순함. 주어진 데이터가 정렬되어있지 않다는 것을 인식하고 정렬 먼저 하는 것이 중요.
    그 다음에 사람과 티셔츠 모두 가장 큰 케이스부터 매칭을 시도해보며 순회하면 풀림.

    내 풀이는 Stack 자료구조형을 이용해서 풀었는데, 모범 답안의 경우는 그냥 배열을 그대로 활용하고 이중 for문을 피하기 위해
    tshirts 인덱스(idx)를 따로 변수로 만들어 활용했음. 이 때 주의할 점은 for문 조건식에 idx >= 0 이 꼭 포함되어야 한다는 것.

    복잡도 측면에서는 아무래도 모범 답안의 풀이가 약간 더 우위에 있음. 코드도 훨씬 간결함.
    이중 루프를 타지 않기 위한 저 꼼수 아닌 꼼수가 생각보다 자주 보이는 풀이 패턴인데, 숙지해놓으면 유용할듯함.
     */

    public int solution2(int[] people, int[] tshirts) {
        int answer = 0;

        // 문제 조건에 정렬되어있다는 말은 없으므로 정렬
        Arrays.sort(people);
        Arrays.sort(tshirts);

        // people과 tshirts를 위한 스택을 생성하고 배열로부터 요소 삽입
        Stack<Integer> peopleStack = new Stack<>();
        Stack<Integer> tshirtsStack = new Stack<>();

        for (int person : people) {
            peopleStack.push(person);
        }

        for (int tshirt : tshirts) {
            tshirtsStack.push(tshirt);
        }

        while (!tshirtsStack.empty() && !peopleStack.empty()) {
            // 가장 사이즈를 크게 입는 사람부터 차례대로 배부
            int mySize = peopleStack.pop();
            int biggestTshirt = tshirtsStack.peek();

            // 남아있는 제일 큰 사이즈가 내 사이즈보다 작다면 못 받음
            if (mySize > biggestTshirt) continue;

            // 티셔츠 수령 (스택에서 제거)
            tshirtsStack.pop();
            answer++;
        }

        return answer;
    }

}
