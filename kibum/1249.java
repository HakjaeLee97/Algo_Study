import java.util.*;
import java.io.*;

class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}

public class Solution {
    public static int n;
    public static int[][] visited;
    public static int[][] graph;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static int MAX;

    public static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = graph[x][y]; // 방문처리

        while(!q.isEmpty()) {
            Node node = q.poll();
            x = node.getX();
            y = node.getY();
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;
                if(visited[nx][ny] > graph[nx][ny] + visited[x][y]) {
                    visited[nx][ny] = graph[nx][ny] + visited[x][y];
                    q.offer(new Node(nx, ny));
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        MAX = Integer.MAX_VALUE;

        for(int t = 1; t <= test_case; t++) {
            n = Integer.parseInt(br.readLine());
            visited = new int[n][n];
            graph = new int[n][n];
            for(int i = 0; i < n; i++) {
                String str = br.readLine();
                for(int j = 0; j < n; j++) {
                    int data = str.charAt(j) - '0';
                    graph[i][j] = data;
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    visited[i][j] = MAX;
                }
            }

            bfs(0, 0);
            System.out.println("#" + t + " " + visited[n-1][n-1]);
        }
    }
}
