package BJ;
import java.util.Scanner;

public class BOJ_2003 {
//	for(int i = 0; i < n; i++) {
//		System.out.println(arr[i]);
//	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int sum = 0;
        int start=0,end=0,cnt=0;

        while(true){
            if(sum>=m){
                sum-=arr[start++];
            }
            else if(end==n){
                break;
            }else{
                sum+=arr[end++];
            }
   
            if(sum==m){
                cnt++;
            }
        }
		System.out.println(cnt);
	}

}