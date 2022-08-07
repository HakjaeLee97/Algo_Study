import java.io.*;
import java.util.*;

public class Solution {
    public static int[][] graph;
    public static int n, total;
    public static int min = Integer.MAX_VALUE;

    public static void compute(int x, int y, int d1, int d2) {
            // 경계선 설정
            boolean[][] border = new boolean[n][n];
            for(int i = 0; i <= d1; i++) {
                border[x+i][y-i] = true;
                border[x+d2+i][y+d2-i] = true;
            }
            for(int i = 0; i <= d2; i++) {
                border[x+i][y+i] = true;
                border[x+d1+i][y-d1+i] = true;
            }

            int peopleOne = 0;
            for(int i = 0; i < x + d1; i++) {
                for(int j = 0; j <= y; j++) {
                    if(border[i][j]) break;
                    peopleOne += graph[i][j];
                }
            }

            int peopleTwo = 0;
            for(int i = 0; i <= x + d2; i++) {
                for(int j = n-1; j > y; j--) {
                    if(border[i][j]) break;
                    peopleTwo += graph[i][j];
                }
            }

            int peopleThree = 0;
            for(int i = x  + d1; i < n; i++) {
                for(int j = 0; j < y-d1+d2; j++) {
                    if(border[i][j]) break;
                    peopleThree += graph[i][j];
                }
            }

            int peopleFour = 0;
            for(int i = x + d2 + 1; i < n; i++) {
                for(int j = n-1; j >= y-d1+d2; j--) {
                    if(border[i][j]) break;
                    peopleFour += graph[i][j];
                }
            }

            int perpleFive = total - (peopleOne+peopleTwo+peopleThree+peopleFour);
            int[] people = {peopleOne, peopleTwo, peopleThree, peopleFour, perpleFive};
            Arrays.sort(people);
            min = Math.min(min, people[4] - people[0]);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                total += graph[i][j];
            }
        }

        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                for(int d1 = 1; d1 < n; d1++) {
                    for(int d2 = 1; d2 < n; d2++) {
                        if (x + d1 + d2 >= n)
                            continue;
                        else if(y-d1 < 0 || y + d2 >= n)
                            continue;

                        compute(x, y, d1, d2);
                    }
                }
            }
        }
        System.out.println(min);
    }
}