package August_second;

import java.io.*;
import java.util.*;

public class bj_15685_드래곤커브_Gold_4 {
	
    static boolean[][] map;
    static int[][] dij = {{1,0},{0,-1},{-1,0},{0,1}};
    static int answer = 0;
    static int N;
    static List<Integer> d_list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        //맵 초기화(방문여부)
        map = new boolean[101][101];
        N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());	//처음값들
            
            int y = Integer.parseInt(st.nextToken());
            
            int d = Integer.parseInt(st.nextToken());   // 방향
            
            int g = Integer.parseInt(st.nextToken());   // 세대

            dc(x, y, d, g);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    public static void dc(int x, int y, int d, int g) {
        
    	d_list = new ArrayList<>();
        d_list.add(d);

        for (int i = 1; i <= g; i++) {
            for (int j = d_list.size() - 1; j >= 0; j--) {
                d_list.add((d_list.get(j) + 1) % 4); //방향
            }
        }

        map[y][x] = true;
        for (Integer dir : d_list) {
            x += dij[dir][0];
            y += dij[dir][1];
            map[y][x] = true;
        }
    }
}