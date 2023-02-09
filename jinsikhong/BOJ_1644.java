import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class BOJ_1644 {
    static boolean[] primechk;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        primechk = new boolean[n+1];
        isPrime();
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 2; i < n; i++){
            if(!primechk[i])
                arr.add(i);
        }
        int cnt = 0;
        System.out.println(arr.toString());
        int idx1 = 1;
        int idx2 = 1;
        int sum = arr.get(0);
        while(idx1 < idx2){
            sum += arr.get(idx1);
            if(sum < n){
                idx2++;
            }else if(sum > n){
                sum = sum - arr.get(idx1);
                idx1++;
            }else if(sum == n){
                cnt++;
                idx2++;
            }
        }
        System.out.println(cnt);
    }


    //에라토스테네스의 체
    private static void isPrime(){
        for(int i = 2; i < Math.sqrt(n)+1; i++){
            if(primechk[i] == false){
                int j = 2;
                while(i * j <= n){
                    primechk[i * j] = true;
                    j+=1;
                }
            }
        }
    }

}
