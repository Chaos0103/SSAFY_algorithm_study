package Bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Lecture[] lec = new Lecture[N];
        PriorityQueue<Integer> q = new PriorityQueue<>();
//        ArrayList<Integer> count =  new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
//            q.offer(new Lecture(s, f));
            lec[i] = new Lecture(s, f);
        }
        Arrays.sort(lec);

        q.offer(lec[0].finish);
        for(int i = 1; i < N; i++){
            if(lec[i].start >= q.peek()){
                q.poll();
            }
            q.offer(lec[i].finish);
        }

        System.out.println(q.size());


//        while(!q.isEmpty()){
//            Lecture cur = q.poll();
//            boolean continueflag = false;
//            for(int i = 0; i < count.size(); i++){
//                if(cur.start >= count.get(i)){
//                    count.set(i, cur.finish);
//                    continueflag = true;
//                    break;
//                }
//            }
//            if(continueflag){
//                continue;
//            }
//            count.add(cur.finish);
//        }
//        Arrays.sort(lec);
//        for(Lecture l : lec){
//            System.out.println(l.toString());
//        }

//        System.out.println(count.size());


    }

    static class Lecture implements Comparable<Lecture> {
        int start, finish;
        public Lecture(int start, int finish){
            this.start = start;
            this.finish = finish;
        }

        @Override
        public String toString() {
            return "Lecture{" +
                    "start=" + start +
                    ", finish=" + finish +
                    '}';
        }

        @Override
        public int compareTo(Lecture o) {
            return this.start - o.start;
        }
    }
}
