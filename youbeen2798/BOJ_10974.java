import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.StringTokenizer;

//10974
public class Main_10974 {

  private static int N;
  private static int arr[] = new int[9];
  private static boolean selected[] = new boolean[9];

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); //ÀÔ·Â °ª
    perm(0);
  }
  public static void perm(int cnt){
      if(cnt == N){
        for(int i = 0; i < N; i++){
          System.out.print(arr[i] + " ");
        }
        System.out.println();
        return;
      }
      for(int i = 0; i < N; i++){
        if(selected[i]){
          continue;
        }
        arr[cnt] = i + 1;
        selected[i] = true;
        perm(cnt + 1);
        selected[i] = false;
      }

  }

}
