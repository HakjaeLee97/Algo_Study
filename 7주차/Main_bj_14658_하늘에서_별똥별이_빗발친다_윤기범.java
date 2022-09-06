import java.util.*;
import java.io.*;

public class Main {
    public static int N, M, L, K;
    public static int ans = Integer.MIN_VALUE;
    public static ArrayList<Node> arr = new ArrayList<>();

    static class Node {
        int xPos;
        int yPos;

        public Node(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.add(new Node(a, b));
        }

        int x = 0, nx = 0;
        int y = 0, ny = 0;
        int cnt = 0;

        for(int i = 0; i < K; i++) {
            for(int j = 0; j < K; j++) {
                cnt = 0;
                x = arr.get(i).xPos;
                y = arr.get(j).yPos;
                for(int a = 0; a < K; a++) {
                    nx = arr.get(a).xPos;
                    ny = arr.get(a).yPos;
                    if(x <= nx && x + L >= nx && y <= ny && y + L >= ny)
                        cnt++;
                }
                ans = Math.max(ans, cnt);
            }
        }
        System.out.println(K - ans);
    }
}
