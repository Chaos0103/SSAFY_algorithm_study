
import java.util.Scanner;

public class PGS_60057 {
    public static void main(String[] args) {
        //solution();]
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int an = Solution(s);
        System.out.println(an);

    }
    public static int Solution(String s){
        int answer = s.length();
        for(int i = 1; i <= s.length()/2; i++){
            StringBuilder result = new StringBuilder();
            int cnt = 1;
            String first = s.substring(0, i);
            for(int j = i; j+i <= s.length(); j+=i){
                String sub = s.substring(j, j+i);
                if(first.equals(sub)){
                    cnt++;
                }else{
                    if(cnt >= 2){
                        result.append(cnt);
                        result.append(first);
                    }else{
                        result.append(first);
                    }
                    first = sub;
                    cnt = 1;
                }

            }
            if(cnt >= 2){
                result.append(cnt);
                result.append(first);
            }else{
                result.append(first);
            }

            answer = Math.min(answer, result.length());

        }
        return answer;
    }
}
