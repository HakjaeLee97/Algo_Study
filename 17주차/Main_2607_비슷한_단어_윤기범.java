import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] data = new String[n];
        int[] origin = new int[26];
        int[] temp = new int[26];

        int cnt = 0;

        for(int i = 0; i < n; i++) {
            data[i] = br.readLine();
        }

        for(int i = 0; i < data[0].length(); i++) {
            origin[(int)data[0].charAt(i) - 65] += 1;
        }

        for(int i = 1; i < n; i++) {
            System.arraycopy(origin, 0, temp, 0, 26);
            for(int j = 0; j < data[i].length(); j++) {
                temp[(int) data[i].charAt(j) - 65] -= 1;
            }
            boolean flag = true;
            int numOfPlus = 0;
            int numOfMinus = 0;
            for(int j = 0; j < 26; j++) {
                if(temp[j] == -1 || temp[j] == 0 || temp[j] == 1) {
                    if(temp[j] == 1)
                        numOfPlus += 1;
                    else if(temp[j] == -1)
                        numOfMinus += 1;
                    if(numOfPlus >= 2 || numOfMinus >= 2) {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            }
            if(flag)
                cnt += 1;
        }

        System.out.println(cnt);
    }
}
