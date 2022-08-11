import java.util.*;
import java.io.*;

class Fish {
    public int x;
    public int y;
    public int dir;

    public Fish(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class Main {
    public static int[][] graph = new int[4][4];
    public static Fish[] fish = new Fish[17];
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    public static int ans = Integer.MIN_VALUE;

    public static void dfs(int[][] graph, Fish[] fish, int sX, int sY, int eat) {
        // 복사
        int[][] tempGraph = new int[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                tempGraph[i][j] = graph[i][j];
            }
        }
        Fish[] tempFish = new Fish[17];
        for(int i = 1; i < 17; i++) {
            tempFish[i] = new Fish(fish[i].x, fish[i].y, fish[i].dir);
        }

        // 상어처리
        int number = tempGraph[sX][sY];
        int sDir = tempFish[number].dir;
        tempFish[number].x = -1;
        tempFish[number].y = -1;
        tempFish[number].dir = -1;
        tempGraph[sX][sY] = -1;

        eat += number;
        ans = Math.max(ans, eat);

        for(int i = 1; i < 17; i++) {
            if(tempFish[i].x == -1) // 상어 혹은 빈칸
                continue;
            int x = tempFish[i].x;
            int y = tempFish[i].y;
            int d = tempFish[i].dir;

            int t = d;
            while(true) {
                int nx = x + dx[t];
                int ny = y + dy[t];
                int nd = t;

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || (nx == sX && ny == sY)) {
                    t = (t + 1) % 8;
                }
                else {
                    if (tempGraph[nx][ny] != -1) { // 이동할 칸에 물고기 있는 경우
                        int targetNumber = tempGraph[nx][ny];
                        tempFish[targetNumber].x = x;
                        tempFish[targetNumber].y = y;

                        tempFish[i].x = nx;
                        tempFish[i].y = ny;
                        tempFish[i].dir = nd;

                        tempGraph[x][y] = targetNumber;
                        tempGraph[nx][ny] = i;
                    } else { // 이동할 칸이 빈칸인 경우
                        tempFish[i].x = nx;
                        tempFish[i].y = ny;
                        tempFish[i].dir = nd;

                        tempGraph[x][y] = -1;
                        tempGraph[nx][ny] = i;
                    }
                    break;
                }
            }
        }
        for(int i = 1; i < 4; i++) {
            int x = sX + dx[sDir] * i;
            int y = sY + dy[sDir] * i;
            if (x < 0 || y < 0 || x >= 4 || y >= 4)
                break;
            if(tempGraph[x][y] != -1) { // 빈칸이 아니면
                dfs(tempGraph, tempFish, x, y, eat);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 4; i++) {
            st  = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 4; j++) {
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;
                graph[i][j] = n;
                fish[n] = new Fish(i, j, d);
            }
        }
        dfs(graph, fish, 0, 0, 0);
        System.out.println(ans);
    }
}