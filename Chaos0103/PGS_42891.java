import java.util.*;

class Food implements Comparable<Food> {

    private int time;
    private int index;

    public Food(int time, int index) {
        this.time = time;
        this.index = index;
    }

    public int getTime() {
        return time;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Food other) {
        return Integer.compare(this.time, other.time);
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        long summary = 0;
        for (int i = 0; i < food_times.length; i++) {
            summary += food_times[i];
        }
        if (summary <= k) {
            return -1;
        }

        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            pq.offer(new Food(food_times[i], i + 1));
        }

        summary = 0;
        long previous = 0;
        long length = food_times.length;

        while (summary + ((pq.peek().getTime() - previous) * length) <= k) {
            int now = pq.poll().getTime();
            summary += (now - previous) * length;
            length -= 1;
            previous = now;
        }

        ArrayList<Food> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        Collections.sort(result, new Comparator<Food>() {

            @Override
            public int compare(Food a, Food b) {
                return Integer.compare(a.getIndex(), b.getIndex());
            }
        });
        return result.get((int) ((k - summary) % length)).getIndex();
    }
}
