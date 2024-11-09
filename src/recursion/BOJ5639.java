package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5639 {
    private static int[] pre, post;
    private static int postIdx;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pre = new int[10000];
        post = new int[10000];
        String input;
        int idx = 0;
        
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            pre[idx++] = Integer.parseInt(input);
        }
        
        postIdx = 0;
        getPost(0, idx - 1);
        
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < idx; i++) {
            answer.append(post[i]).append("\n");
        }
        System.out.print(answer);
    }
    
    private static void getPost(int preFirst, int preLast) {
        if (preFirst > preLast) {
            return;
        }
        
        int root = pre[preFirst];
        int rightIdx = preLast + 1;
        
        for (int i = preFirst + 1; i <= preLast; i++) {
            if (pre[i] > root) {
                rightIdx = i;
                break;
            }
        }
        
        getPost(preFirst + 1, rightIdx - 1);
        getPost(rightIdx, preLast);
        post[postIdx++] = root;
    }
}