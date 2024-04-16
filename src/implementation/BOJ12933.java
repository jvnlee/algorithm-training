package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ12933 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        if (input[0] != 'q' || input.length % 5 != 0) { // 울음이 q로 시작하지 않거나 입력 길이가 5의 배수가 아니면 유효하지 않음
            System.out.println(-1);
            return;
        }

        char[] sound = {'q', 'u', 'a', 'c', 'k'}; // 올바른 울음소리 체크를 위한 기준 배열

        int count = 0; // 오리의 수
        int soundIdx = 0; // sound 배열을 순회하는 용도의 인덱스

        for (int i = 0; i < input.length; i++) {
            List<Character> quacks = new ArrayList<>();

            // 현재 글자부터 끝까지 탐색 (이전 글자는 탐색하지 않기 위함)
            for (int j = i; j < input.length; j++) {
                if (input[j] != '_' && input[j] == sound[soundIdx]) {
                    quacks.add(input[j]);
                    input[j] = '_'; // 사용한 울음 글자는 언더스코어로 치환

                    soundIdx++;
                    soundIdx %= 5; // 마지막 글자인 k까지 온 경우엔 다시 맨 처음 글자로
                }
            }

            if (quacks.size() != 0) {
                if (quacks.get(quacks.size() - 1) != 'k') { // 수집한 울음소리의 마지막 글자가 k가 아니면 유효하지 않음
                    System.out.println(-1);
                    return;
                }

                count++; // 위의 조건문을 타지 않은 유효한 울음소리 모음이라면 정상 오리 1마리로 간주
            }
        }

        System.out.println(count);
    }
}
