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

public class Main {
    public static int N, M;
    public static int[][] graph;
    public static ArrayList<Node> virus = new ArrayList<>();
    public static Node[] check;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public boolean flag = true;
    public static int numOfZero = 0;
    public static int ans = Integer.MAX_VALUE;

    public static void comb(int cnt, int start) {
        if(cnt == M) {
            Queue<Node> q = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            int[][] copyGraph = new int[N][N];
            int sec = 1;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j <N; j++) {
                    if(graph[i][j] == 1)
                        copyGraph[i][j] = -1; // '-'
                    if(graph[i][j] == 2)
                        copyGraph[i][j] = -2; // '*'
                }
            }

            for(int i = 0; i < M; i++) {
                q.add(check[i]);
                copyGraph[check[i].getX()][check[i].getY()] = -3; // 활성 바이러스
            }

            while(!q.isEmpty()) {
                int len = q.size();
                for(int i = 0; i < len; i++) {
                    Node node = q.poll();
                    int x = node.getX();
                    int y = node.getY();
                    visited[x][y] = true;
                    for(int j = 0; j < 4; j++) {
                        int nx = x + dx[j];
                        int ny = y + dy[j];
                        if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                            continue;
                        if(visited[nx][ny] == false && (copyGraph[nx][ny] == 0 || copyGraph[nx][ny] == -2)) {
                            if(copyGraph[nx][ny] == 0) {
                                copyGraph[nx][ny] = sec;
                            }
                            visited[nx][ny] = true;
                            q.add(new Node(nx, ny));
                        }
                    }
                }
                sec += 1;
            }

            int numOfZero = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(copyGraph[i][j] == 0)
                        numOfZero++;
                }
            }
            if(numOfZero > 0)
                return;
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(copyGraph[i][j] == -3)
                        copyGraph[i][j] = 0;
                }
            }
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    max = Math.max(max, copyGraph[i][j]);
                }
            }

            ans = Math.min(ans, max);

            return;
        }

        for(int i = start; i < virus.size(); i++) {
            check[cnt] = virus.get(i);
            comb(cnt+1, i+1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        check = new Node[M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 2)
                    virus.add(new Node(i, j));
            }
        }

        comb(0, 0);
        if(ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(ans);
    }
}