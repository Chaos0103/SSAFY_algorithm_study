package PGS;

import sun.lwawt.macosx.CSystemTray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PGS_17677 {
    public static void main(String[] args) {
        //solution();]
        Scanner sc = new Scanner(System.in);
        String str11 = sc.nextLine();
        String str22 = sc.nextLine();
        Solution solve = new Solution();
        int an = solve.solution(str11, str22);

    }
    static class Solution {
        public int solution(String str1, String str2) {
            int answer = 0;
            str1 = str1.toLowerCase();
            str2 = str2.toLowerCase();
            String firstString = "";
            String secondString = "";

            for(int i = 0; i < str1.length(); i++){
                int temp = (int)str1.charAt(i);
                if(temp >= 97 && temp <= 122){
                    firstString+=str1.charAt(i);
                }
            }
            for(int i = 0; i < str2.length(); i++){
                int temp = (int)str2.charAt(i);
                if(temp >= 97 && temp <= 122){
                    secondString+=str2.charAt(i);
                }
            }
            String[] strOneArr = new String[firstString.length()-1];
            String[] strTwoArr = new String[secondString.length()-1];
            Map<String, Integer> map1 = new HashMap<>();
            Map<String, Integer> map2 = new HashMap<>();
            int cnt1 = 0;
            int cnt2 = 0;
            for(int i = 0; i < firstString.length()-1;i++){
                String key = firstString.substring(i, i+2);
                strOneArr[i] = key;
                map1.put(key, map1.getOrDefault(key, 0) + 1);
                cnt1++;
            }
            for(int i = 0; i < secondString.length()-1;i++){
                String key = secondString.substring(i, i+2);
                map2.put(key, map2.getOrDefault(key, 0) + 1);
                strTwoArr[i] = key;
                cnt2++;
            }
            int intersectionCnt = 0;
            if(cnt1 < cnt2){
                for(int i = 0; i < map1.size(); i++){

                    int temp = map1.get(strOneArr[i]);
                    int temp2 = map2.getOrDefault(strOneArr[i], 0);
                    if(temp2 != 0){
                        intersectionCnt += Math.min(temp, temp2);
                    }
                }
            }else{
                for(int i = 0; i < map2.size(); i++){
                    int temp = map2.get(strTwoArr[i]);
                    int temp2 = map1.getOrDefault(strTwoArr[i], 0);
                    if(temp2 != 0){
                        intersectionCnt += Math.min(temp, temp2);
                    }
                }
            }

            int result = (intersectionCnt)* 65536 / (cnt1+cnt2-intersectionCnt) ;
            System.out.println(result);


            return answer;
        }
    }
}
