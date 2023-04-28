package programmers;

import java.util.*;

public class PRG81302 {
    public String[][] map;
    int[] answer = {1, 1, 1, 1, 1};

    public int[] solution(String[][] places) {
        for (int k = 0; k < 5; k++) {
            map = new String[5][5];

            for (int i = 0; i < 5; i++) {
                String[] split = places[k][i].split("");

                for (int j = 0; j < 5; j++) {
                    map[i][j] = split[j];
                }
            }

            List<Coordinate> list = extractPersonCoordinates();
            verify(list, k);
        }

        return answer;
    }

    public void verify(List<Coordinate> list, int k) {
        for (int i = 0; i < list.size(); i++) {
            Coordinate c1 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                Coordinate c2 = list.get(j);
                int distance = getDistance(c1, c2);

                if (distance > 2) continue;
                else if (distance < 2) answer[k] = 0;
                else if (distance == 2) {
                    if (c1.x == c2.x) {
                        if (map[c1.x][(c1.y + c2.y) / 2].equals("O")) answer[k] = 0;
                    } else if (c1.y == c2.y) {
                        if (map[(c1.x + c2.x) / 2][c1.y].equals("O")) answer[k] = 0;
                    } else if (Math.abs(c1.x - c2.x) == 1 && Math.abs(c1.y - c2.y) == 1) {
                        if (!map[c1.x][c2.y].equals("X") || !map[c2.x][c1.y].equals("X")) answer[k] = 0;
                    }
                }
            }
        }
    }

    public int getDistance(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
    }

    public List<Coordinate> extractPersonCoordinates() {
        List<Coordinate> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j].equals("P")) {
                    list.add(new Coordinate(i, j));
                }
            }
        }

        return list;
    }

    public class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
