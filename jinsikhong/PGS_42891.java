package BJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PGS_42891 {
    public static void main(String[] args) {
        int[] food_times = {6,7,4,5,8};
        int k = 20;
        Solution s = new Solution();
        System.out.println(s.solution(food_times, k));
    }



    static class Solution {
        public int solution(int[] food_times, long k) {
            PriorityQueue<Food> pq = new PriorityQueue<>();
            long sum = 0;
            for(int i = 1; i <= food_times.length; i++) {
                pq.offer(new Food(i, food_times[i-1]));
                sum += food_times[i-1];
            }
            if(sum <= k) {
                return -1;
            }

   

            long total = 0;   // 먹기 위해 사용한 시간
            long previous = 0;  // 직전에 다 먹은 음식 시간
            long length = food_times.length;    // 남은 음식 개수
            
            while (total + ((pq.peek().time - previous) * length) <= k) {
                int now = pq.poll().time;
                total += (now - previous) * length;
                length -= 1;
                previous = now;
            }

//            System.out.println(k);
            ArrayList<Food> list = new ArrayList<>();
            while(!pq.isEmpty()) {
            	list.add(pq.poll());
            }
            Collections.sort(list, new Comparator<Food>() {
                @Override
                public int compare(Food a, Food b) {
                    return Integer.compare(a.num, b.num);
                }
            });
            return list.get((int) ((k - total) % length)).num;
            
        
//            System.out.println("나왔을떄 k값 : " + k );
//            int answer = 0;
//            int idx = 1;
//            for(int i = 1; i <= k; i++) {
//                idx++;
//                if(food_times[i] == 0) {
//                    idx++;
//                    continue;
//                }
//                answer = idx;
//            }
        }
    }


    static class Food implements Comparable<Food>{
        int num, time;
        public Food(int num, int time) {
            this.num = num;
            this.time = time;
        }
        @Override
        public int compareTo(Food o) {
            return this.time - o.time;
        }
        @Override
        public String toString() {
            return "Food [num=" + num + ", time=" + time + "]";
        }

    }
}
