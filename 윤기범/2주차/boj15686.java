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
    public static Node[] check;
    public static ArrayList<Node> chicken = new ArrayList<>();
    public static ArrayList<Node> home = new ArrayList<>();
    public static int ans = Integer.MAX_VALUE;

    public static void comb(int cnt, int start) {
        if(cnt == M) {
            int sum = 0;
            for(int i = 0; i < home.size(); i++) {
                int temp = Integer.MAX_VALUE;
                int hx = home.get(i).getX();
                int hy = home.get(i).getY();
                for(int j = 0; j < check.length; j++) {
                    int cx = check[j].getX();
                    int cy = check[j].getY();
                    int dist = Math.abs(hx-cx) + Math.abs(hy - cy);
                    temp = Math.min(temp, dist);
                }
                sum += temp;
            }
            ans = Math.min(ans, sum);
            return;
        }

        for(int i = start; i < chicken.size(); i++) {
            check[cnt] = chicken.get(i);
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

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 1)
                    home.add(new Node(i, j));
                else if(graph[i][j] == 2)
                    chicken.add(new Node(i, j));
            }
        }

        comb(0, 0);
        System.out.println(ans);
    }
}
