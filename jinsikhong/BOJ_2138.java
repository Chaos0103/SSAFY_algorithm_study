package greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2138 {
    static int[] arr;
    static int[] arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        arr2= new int[n];
        int[] result = new int[n];

        String light = br.readLine();
        char[] light_arr = light.toCharArray();
        for(int i = 0; i < n; i++){
            arr[i] = light_arr[i] -'0';
            arr2[i] = light_arr[i] -'0';
        }
        light = br.readLine();
        light_arr = light.toCharArray();
        for(int i = 0; i < n; i++){
            result[i] = light_arr[i] -'0';
        }

        swap2(0);
        swap2(1);

        int cnt = 0;
        int cnt2 = 1;
        boolean cnt_chk = true;
        boolean cnt2_chk = true;
        for(int i = 1; i < n; i++){

            int b_n = i -1;
            int b_a = i +1;

            if(arr[b_n] != result[b_n]){
                if(b_n >= 0){
                    swap(b_n);
                }
                if(b_a < n){
                    swap(b_a);
                }
                swap(i);
                cnt++;
            }

            if(arr2[b_n] != result[b_n]){
                if(b_n >= 0){
                    swap2(b_n);
                }
                if(b_a < n){
                    swap2(b_a);
                }
                swap2(i);
                cnt2++;
            }

        }

        for(int i = 0; i < n; i++){
            if(arr[i] != result[i]){
                cnt = -1;
                cnt_chk = false;
            }
            if(arr2[i] != result[i]){
                cnt2 = -1;
                cnt2_chk = false;
            }
            if(cnt_chk == false && cnt2_chk == false){
                break;
            }
        }
        int min = Integer.MIN_VALUE;
        if(cnt_chk && cnt2_chk){
            min = Math.min(cnt, cnt2);
            System.out.println(min);
        }else if(cnt_chk == false && cnt2_chk == false){
            System.out.println(-1);
        }else if(cnt_chk == true && cnt2_chk == false){
            System.out.println(cnt);
        }else{
            System.out.println(cnt2);
        }
    }
    public static void swap(int idx){
        if(arr[idx] == 0){
            arr[idx] = 1;
        }else{
            arr[idx] = 0;
        }
    }

    public static void swap2(int idx){
        if(arr2[idx] == 0){
            arr2[idx] = 1;
        }else{
            arr2[idx] = 0;
        }
    }
}
