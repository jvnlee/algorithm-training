package sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {21, 10, 12, 20, 25, 13, 15, 22};
        System.out.println(Arrays.toString(mergeSort(arr)));
    }

    private static int[] mergeSort(int[] arr) {
        // 분할
        int length = arr.length;

        int[] subArr1 = new int[length / 2];
        int[] subArr2 = new int[length - (length / 2)];

        for (int i = 0; i < length / 2; i++) {
            subArr1[i] = arr[i];
        }

        for (int i = length / 2; i < length; i++) {
            subArr2[i - length / 2] = arr[i];
        }

        // 분할된 배열의 길이가 1이 될 때까지 재귀 호출
        if (subArr1.length > 1) {
            subArr1 = mergeSort(subArr1);
        }

        if (subArr2.length > 1) {
            subArr2 = mergeSort(subArr2);
        }

        // 정렬하면서 합병
        // 분할된 배열의 길이가 1이 되었을 때(더 이상 분할이 불가능할 때), 이 코드 영역에 처음 접근하게 됨
        int[] sortedArr = new int[subArr1.length + subArr2.length];

        int subArr1Idx = 0;
        int subArr2Idx = 0;
        int sortedArrIdx = 0;

        while (sortedArrIdx < length) {
            if (subArr1Idx >= subArr1.length) {
                for (int j = subArr2Idx; j < subArr2.length; j++) {
                    sortedArr[sortedArrIdx++] = subArr2[j];
                }
                break;
            }

            if (subArr2Idx >= subArr2.length) {
                for (int j = subArr1Idx; j < subArr1.length; j++) {
                    sortedArr[sortedArrIdx++] = subArr1[j];
                }
                break;
            }

            if (subArr1[subArr1Idx] <= subArr2[subArr2Idx]) {
                sortedArr[sortedArrIdx++] = subArr1[subArr1Idx++];
            } else {
                sortedArr[sortedArrIdx++] = subArr2[subArr2Idx++];
            }
        }

        return sortedArr;
    }
}
