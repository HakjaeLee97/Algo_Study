import java.util.*;
import java.io.*;

public class Main {
    public static int n, k;
    public static ArrayList<Info> info;
    public static ArrayList<Ranking> rank;
    public static int cnt = 1, num = 0, i = 1;
    public static boolean flag = true;

    static class Info implements Comparable<Info> {
        int a, b, c, d;

        public Info(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Info o) {
            int r = -(this.b - o.b);
            if (r == 0) {
                r = -(this.c - o.c);
                if (r == 0) {
                    r = -(this.d - o.d);
                }
            }
            return r;
        }
    }

    static class Ranking {
        int num, rank;

        public Ranking(int num, int rank) {
            this.num = num;
            this.rank = rank;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        info = new ArrayList<>();
        rank = new ArrayList<>();

        info.add(new Info(0, 1000000, 1000000, 1000000));

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a, b, c, d;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            info.add(new Info(a, b, c, d));
        }

        Collections.sort(info);

        while(i < n + 1) {
            while(info.get(i).b == info.get(i-1).b && info.get(i).c == info.get(i-1).c && info.get(i).d == info.get(i-1).d) {
                rank.add(new Ranking(info.get(i).a, num));
                cnt += 1;
                flag = false;
                i += 1;
                if (i >= n + 1)
                    break;
            }
            if (i >= n + 1)
                break;
            num += cnt;
            rank.add(new Ranking(info.get(i).a, num));
            cnt = 1;
            i += 1;
            flag = true;
        }

        for(int i = 0; i < n; i++) {
            if (k == rank.get(i).num) {
                System.out.println(rank.get(i).rank);
                break;
            }
        }
    }
}
