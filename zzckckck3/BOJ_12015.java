package Algorithm_230321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_12015 {
    static int N;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        for(int i = 0; i < N; i++) {
            if(list.get(list.size()-1) < arr[i]) {
                list.add(arr[i]);
            }
            else {
                int left = 1;
                int right = list.size()-1;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if(list.get(mid) < arr[i]) {
                        left = mid + 1;
                    }else {
                        right = mid;
                    }
                }
                list.set(right, arr[i]);
            }
        }
        System.out.println(list.size() - 1);
    }
}