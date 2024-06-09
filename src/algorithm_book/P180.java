package algorithm_book;

import java.util.*;

public class P180 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        sc.nextLine();

        List<Student> board = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            board.add(new Student(line[0], Integer.valueOf(line[1])));
        }

        // board.sort((o1, o2) -> o1.score - o2.score);
        board.sort(Comparator.comparingInt(o -> o.score));

        board.forEach(s -> System.out.print(s.name + " "));
    }

    public static class Student {
        String name;
        Integer score;

        public Student(String name, Integer score) {
            this.name = name;
            this.score = score;
        }
    }
}

/*
학생들을 성적 순서대로 정렬할 때, 람다식 (o1, o2) -> o1.score - o2.score 대신 Comparator에서 제공하는 라이브러리인 comparingInt()를 사용할 수 있다는 것을 알았다.
코드의 간결성 측면에서는 큰 차이가 없지만 몰랐던 기능이라 알아두면 좋을 것 같다.
 */