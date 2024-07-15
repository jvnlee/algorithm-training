package priority_queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ11286_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int a1 = Math.abs(o1);
            int a2 = Math.abs(o2);

            return a1 == a2 ? o1 - o2 : a1 - a2;
        });

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x != 0){
                pq.offer(x);
            } else {
                if (pq.isEmpty()) {
                    bw.write("0\n");
                } else {
                    bw.write(pq.poll() + "\n");
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
