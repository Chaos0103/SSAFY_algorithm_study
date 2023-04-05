import java.util.Arrays;

/**
 * https://leveloper.tistory.com/107
 * 1시간 30분 도전 하다가 솔루션.ㅠㅠ
 * 나중에 재도전 하겠습니다..
 */
class Solution {
    public int solution(int[] food_times, long k) {
        int size = food_times.length;
        int[] sortFood = sort(size, food_times);

        int food = size; // 섭취할 수 있는 음식의 수

        int idx = 0;
        int num = 0;
        long time = 0; // 총 걸린 시간

        while (time + food <= k) {
            time += food;
            num++;
            for (int i = idx; i < size; i++) {
                if (sortFood[i] == num) {
                    idx++;
                    food--;
                } else break;
            }
            if (food == 0) return -1; // 섭취할 수 있는 음식이 없다면
        }

        long count = k - time + 1;
        for (int i = 0; i < size; i++) {
            if (food_times[i] - num > 0) {
                if (--count == 0) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    private int[] sort(int n, int[] food_times) {
        int[] result = new int[n];
        System.arraycopy(food_times, 0, result, 0, n);
        Arrays.sort(result);
        return result;
    }
}