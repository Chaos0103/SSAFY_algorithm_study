import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] brokenBtn = new boolean[10];
    //	static String number = "";
    static String num;
    static char[] number;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        num = br.readLine();
        N = Integer.parseInt(num);
        number = new char[num.length()];
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            brokenBtn[Integer.parseInt(st.nextToken())] = true;
        }

        int plusminus = Math.abs(N - 100);

        if(plusminus == 100) { // 번호 그대로이면
            System.out.println(0);
            return;
        }
        if(M == 10) { // 버튼이 다 고장나면
            System.out.println(plusminus);
            return;
        }
        result = plusminus;

        int maxnum = findUpnum(num.charAt(0) - '0');

        String overnum = "";
        overnum += maxnum;
        int idx = 0;
        for(int i = 0; i < 10; i++){
            if(!brokenBtn[i]){
                idx = i;
                break;
            }
        }

        for(int i = 1; i < num.length(); i++){
            overnum += idx;
        }
//        System.out.println(overnum);
        result = Math.min(result, Math.abs(N - Integer.parseInt(overnum)) + overnum.length());

//        System.out.println(Integer.parseInt("00001" + 1 + 1));
        solve(0);
        System.out.println(result);



//		System.out.println(findNearBtn(7));

    }

    static String makeString(char[] temp) {
        String s = "";
        for(int i = 0; i < temp.length; i++) {
            s+= temp[i];
        }
        return s;
    }

    static void getmin(int s){
        int res = Math.abs(N - s);
        res += num.length();
        result = Math.min(result, res);

    }

    static void solve(int cnt) {
        if(cnt == num.length()) {
            String s = makeString(number);
            getmin(Integer.parseInt(s));
            return;
        }

        for(int i = 0; i < 10; i++) {
            if(!brokenBtn[i]) {
                number[cnt] = (char)( i + '0');
                solve(cnt + 1);
            }
        }


    }

    static int findUpnum(int n){
        int idxUp = n;
        boolean overTen = false;
        while(true){
            if(!brokenBtn[idxUp]){
                break;
            }
            idxUp++;
            if(idxUp > 9){
                idxUp = 0;
                overTen = true;
            }
        }
        if(overTen){
            if(idxUp == 0){
                idxUp = 1;
            }
            return idxUp * 10;
        }else{
            return idxUp;
        }
    }






//	static int findNearBtn(int n) {
//		int idxUp = n;
//		int idxDown = n;
//		int cntUp = 0;
//		int cntDown = 0;
//
//		while(idxUp < 10) {
//			cntUp++;
//			if(!brokenBtn[idxUp]) {
//				break;
//			}
//			idxUp++;
//		}
//
//		while(idxDown > 0) {
//			cntDown++;
//			if(!brokenBtn[idxDown]) {
//				break;
//			}
//			idxDown--;
//		}
//		if(idxUp > 10) {
//			cntUp = Integer.MAX_VALUE;
//		}
//		if(idxDown < 0) {
//			cntDown = Integer.MAX_VALUE;
//		}
//
//
//		if(cntUp >= cntDown) {
//			return idxDown;
//		}else {
//			return idxUp;
//		}
//	}

}
