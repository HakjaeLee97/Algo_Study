import java.util.*;
import java.io.*;

class Node {
    private int x;
    private int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
}
public class Solution {
    public static int n, m;
    public static int ans = Integer.MAX_VALUE;
    public static int[][] graph;
    public static ArrayList<Node> chicken = new ArrayList<>();
    public static ArrayList<Node> house = new ArrayList<>();
    public static boolean[] check;

    public static void combination(int idx, int cnt) {
        if(cnt == m) { // 치킨집을 체크한 경우
            int sum = 0;

            for(int i = 0; i < house.size(); i++) {
                int temp = (int)1e9;
                int hx = house.get(i).getX();
                int hy = house.get(i).getY();
                for(int j = 0; j < chicken.size(); j++) {
                    if(check[j] == true) {
                        int cx = chicken.get(j).getX();
                        int cy = chicken.get(j).getY();
                        int dist = Math.abs(hx - cx) + Math.abs(hy - cy);

                        temp = Math.min(temp, dist);
                    }
                }
                sum += temp;
            }
            ans = Math.min(ans, sum);
        }

        for(int i = idx; i < chicken.size(); i++) {
            check[i] = true;
            combination(i + 1, cnt + 1);
            check[i] = false;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 1) house.add(new Node(i, j));
                else if(graph[i][j] == 2) chicken.add(new Node(i, j));
            }
        }

        check = new boolean[chicken.size()];

        combination(0, 0);
        System.out.println(ans);
    }
}


