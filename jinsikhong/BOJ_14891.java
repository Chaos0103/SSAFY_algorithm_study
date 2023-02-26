package Bj;


import java.util.Arrays;
import java.util.Scanner;

//같은 극 이면 다른 방향으로 회전
// n극은 0 / S 극은 1
public class BOJ_14891 {
    static int[][] arr;

    static int[][] idxarr = new int[4][2]; // [n][0] : 3시(오른쪽) 인덱스 /
    static int r_idx;
    static int[] can_rotation = new int[4];


    static int l_idx;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new int[4][8];


        for(int i = 0; i < 4; i++){
            char[] temp = sc.nextLine().toCharArray();
            idxarr[i][0] = 2;
            idxarr[i][1] = 6;
            for(int j = 0; j < 8; j++){
                arr[i][j] = temp[j]-'0';
            }
        }


        // 데이터 받기
//        sc.nextLine();
        int k = sc.nextInt();
        int num = 0 ;
        int d = 0;

        for(int i = 0; i < k; i++){
            num = sc.nextInt();
            d = sc.nextInt();
            check(num-1, d);
            System.out.println(Arrays.toString(can_rotation));
            rotation();
        }
        int result = getresult();
        System.out.println(result);

    }

    /*
    1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점

n극 0
s극 1
     */
    static int getresult(){ //
        int sum = 0;
        for(int i = 0; i < 4; i++){
            int idx12 = idxarr[i][0];
            for(int j = 0; j < 2; j++){
                idx12--;
                if(idx12 < 0){
                    idx12 = 7;
                }
            }
            if(arr[i][idx12] == 1){
                sum += Math.pow(2, i);
            }



        }
        return sum;
    }

    static void check(int i ,int direc){
        int idxr = i+1;
        int idxl = i-1;
        can_rotation[i] = direc;
        boolean flag = true; //시계방향이면 true
        if(direc == -1){ // 반 시계이면
            flag = false;
        }
        while(idxr < 4){
            if(arr[idxr-1][idxarr[idxr-1][0]] != arr[idxr][idxarr[idxr][1]]){ // 같지 않으면 회전
                if(flag){ //이전꺼가 시계방향이면
                    can_rotation[idxr] = -1; //반시계 회전
                    flag = false;
                }else{
                    can_rotation[idxr] = 1;
                    flag = true;
                }
            }else{
                can_rotation[idxr] = 0;
                break;
            }
            idxr++;
        }
        flag = true;
        if(direc == -1){ // 반 시계이면
            flag = false;
        }
        while(idxl >= 0){
            if(arr[idxl+1][idxarr[idxl+1][1]] != arr[idxl][idxarr[idxl][0]]){ // 같지 않으면 회전
                if(flag){ //이전꺼가 시계방향이면
                    can_rotation[idxl] = -1; //반시계 회전
                    flag = false;
                }else{
                    can_rotation[idxl] = 1;
                    flag = true;
                }
            }else{
                can_rotation[idxl] = 0;
                break;
            }
            idxl--;
        }
    }



    static void rotation(){ //direc 1 : 시계 방향 , dirce -1 : 반시계
        for(int i = 0; i < 4; i ++){
            if(can_rotation[i] == 1){
                rRotion(i);
            }else if(can_rotation[i] == -1){
                lRotion(i);
            }
        }
        can_rotation = new int[4];
    }

    static void rRotion(int i){ //시계방향 회전
        idxarr[i][0]--;
        if(idxarr[i][0] < 0){
            idxarr[i][0] = 7; // 마지막 인덱스로
        }

        idxarr[i][1]--;
        if(idxarr[i][1] < 0){
            idxarr[i][1] = 7; // 마지막 인덱스로
        }
    }

    static void lRotion(int i){ //반시계방향 회전
        idxarr[i][0]++;
        if(idxarr[i][0] >= 8){
            idxarr[i][0] = 0; // 첫번째 인덱스로
        }

        idxarr[i][1]++;
        if(idxarr[i][1] >= 8){
            idxarr[i][1] = 0; // 첫번째 인덱스로
        }
    }
}
