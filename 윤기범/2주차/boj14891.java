import java.io.*;
import java.util.*;

public class Main {
    public static void move(int o, int d) {
        int temp = d;
        int temp2 = d;
        int check = arr[o-1][2];
        int check2 = arr[o-1][6];
        if(temp == -1){ // 반시계
            int tempElem = arr[o-1][0];
            for(int j = 1; j < 8; j++) {
                arr[o-1][j-1] = arr[o-1][j];
            }
            arr[o-1][7] = tempElem;
        }
        else { // 시계
            int tempElem = arr[o-1][7];
            for(int j = 6; j >= 0; j--) {
                arr[o-1][j+1] = arr[o-1][j];
            }
            arr[o-1][0] = tempElem;
        }
        for(int i = o-2; i >= 0; i--) {
            temp *= -1;
            if(check2 == arr[i][2])
                break;
            else {
                check2 = arr[i][6];
                if(temp == 1) {
                    int tempElem = arr[i][7];
                    for(int j = 6; j >= 0; j--) {
                        arr[i][j+1] = arr[i][j];
                    }
                    arr[i][0] = tempElem;
                }
                else {
                    int tempElem = arr[i][0];
                    for(int j = 1; j < 8; j++) {
                        arr[i][j-1] = arr[i][j];
                    }
                    arr[i][7] = tempElem;
                }
            }
        }
        for(int i = o; i < 4; i++) {
            temp2 *= -1;
            if(check == arr[i][6])
                break;
            else {
                check = arr[i][2];
                if(temp2 == 1) {
                    int tempElem = arr[i][7];
                    for(int j = 6; j >= 0; j--) {
                        arr[i][j+1] = arr[i][j];
                    }
                    arr[i][0] = tempElem;
                }
                else {
                    int tempElem = arr[i][0];
                    for(int j = 1; j < 8; j++) {
                        arr[i][j-1] = arr[i][j];
                    }
                    arr[i][7] = tempElem;
                }
            }
        }
    }

    public static int[][] arr = new int[4][8];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 4; i++) {
            String str = br.readLine();
            char[] temp = str.toCharArray();
            for(int j = 0; j < 8; j++) {
                arr[i][j] = temp[j] - '0';
            }
        }
        int k = Integer.parseInt(br.readLine());
        int sum = 0;
        for(int i = 0; i < k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            move(a, b);
        }
        sum += arr[0][0] * 1;
        sum += arr[1][0] * 2;
        sum += arr[2][0] * 4;
        sum += arr[3][0] * 8;
        System.out.println(sum);
    }
}
