import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[102][102];
        
        for (int[] r : rectangle) {
            int x1 = r[0] * 2;
            int y1 = r[1] * 2;
            int x2 = r[2] * 2;
            int y2 = r[3] * 2;
            
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    if (x == x1 || x == x2 || y == y1 || y == y2) {
                        if (map[x][y] == 0) {
                            map[x][y] = 1;
                        }
                    } else {
                        map[x][y] = 2;
                    }
                }
            }
        }
        
        int startX = characterX * 2;
        int startY = characterY * 2;
        int targetX = itemX * 2;
        int targetY = itemY * 2;
            
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
            
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(startX, startY, 0));
            
        Set<String> visited = new HashSet<>();
        visited.add(startX + ", " + startY);
            
        while (!q.isEmpty()) {
            Point cp = q.poll();
            int cx = cp.x;
            int cy = cp.y;
            int cd = cp.d;
                
            if (cx == targetX && cy == targetY) {
                return cd / 2;
            }
                
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                String np = nx + ", " + ny;
                    
                if (nx >= 0 && nx < 102 && ny >= 0 && ny < 102 && map[nx][ny] == 1 && !visited.contains(np)) {
                    visited.add(np);
                    q.offer(new Point(nx, ny, cd + 1));
                }
            }
        }
        
        return 0;
    }
    
    private class Point {
        int x;
        int y;
        int d;
        
        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}