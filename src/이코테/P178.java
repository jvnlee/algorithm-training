package 이코테;

import java.util.*;

public class P178 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Arrays.sort(arr, (o1, o2) -> o2 - o1);
        Arrays.sort(arr, Collections.reverseOrder());

        System.out.println(Arrays.toString(arr));
    }
}

/*
Arrays.sort()의 두번째 인자로 익명 Comparator 인스턴스를 넘기거나, 람다식을 이용해서 (o1, o2) -> o2 - o1 로 내림차순 정렬을 하는 방법을 사용했는데,
풀이를 보니 Collections 클래스에 기본 라이브러리로 reverseOrder() 메서드를 제공하고 있었다. 몰랐던 기능인데 이렇게 사용해도 좋을 것 같다.
 */
