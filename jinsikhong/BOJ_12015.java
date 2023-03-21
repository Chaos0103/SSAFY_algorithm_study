import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[N];
        ArrayList<Integer> lis = new ArrayList<>();
        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        lis.add(num[0]);
        for(int i = 1; i < N; i++){
            if(num[i] > lis.get(lis.size()-1)){ // 마지막 부분버보다 크면
                lis.add(num[i]);
            }else{ // 작으면 이분탐색
                int first = 0;
                int last = lis.size()-1;
                while(first < last){
                    int mid = (first + last) / 2;
                    if(num[mid] < num[i]){ // 왼쪽보다 더 크면
                        first = mid + 1;
                    }else{
                        last = mid;
                    }
                }
                lis.set(last, num[i]);
            }
//            System.out.println(lis.toString());
        }
        System.out.println(lis.size());
    }
}
