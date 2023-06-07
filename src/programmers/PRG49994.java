package programmers;

import java.util.*;

public class PRG49994 {
    public int solution(String dirs) {
        Set<Move> set = new HashSet<>();

        int curX = 0;
        int curY = 0;

        for (char d : dirs.toCharArray()) {
            if (d == 'U') {
                if (curY + 1 > 5) continue;
                set.add(new Move(curX, curY, curX, ++curY));
            }
            if (d == 'D') {
                if (curY - 1 < -5) continue;
                set.add(new Move(curX, curY, curX, --curY));
            }
            if (d == 'R') {
                if (curX + 1 > 5) continue;
                set.add(new Move(curX, curY, ++curX, curY));
            }
            if (d == 'L') {
                if (curX - 1 < -5) continue;
                set.add(new Move(curX, curY, --curX, curY));
            }
        }

        return set.size();
    }

    public class Move {
        int fromX;
        int fromY;
        int toX;
        int toY;

        public Move(int fromX, int fromY, int toX, int toY) {
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Move m = (Move) o;

            return (fromY == m.fromY && fromX == m.fromX && toY == m.toY && toX == m.toX)
                    || (fromY == m.toY && fromX == m.toX && toY == m.fromY && toX == m.fromX);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fromX, fromY, toX, toY) + Objects.hash(toX, toY, fromX, fromY);
        }
    }
}
