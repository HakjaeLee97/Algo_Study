import java.util.*;
import java.io.*;

public class Main {
    public static final int people = 1001;
    public static ArrayList<Info> compare;
    static class Info implements Comparable<Info> {
        int a, b;

        public Info(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Info o) {
            return this.b - o.b;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] temp = new int[1001];
            int i = 0;
            while(st.hasMoreTokens()) {
                temp[i] = Integer.parseInt(st.nextToken());
                i += 1;
            }
            int[] cnt = new int[people];
            int[] rank = new int[people];
//            System.out.println(Arrays.toString(temp));

            // 등수표시
            for(int a = 0; a < n; a++) {
                cnt[temp[a]] += 1;
            }
//            System.out.println(Arrays.toString(cnt));

            i = 0;
            // 6명 들어온 팀 확인
            for(int a = 0; a < n; a++) {
                if(cnt[temp[a]] == 6) {
                    rank[i] = temp[a];
                    i += 1;
                }
            }
//            System.out.println(Arrays.toString(rank));

            // 4명 점수 확인
            int[] score = new int[people];
            int[] cntFour = new int[people];
            Arrays.fill(cntFour, 1);
            for (int a = 0; a < rank.length; a++) {
                if(cntFour[rank[a]] <= 4 && rank[a] != 0) {
                    cntFour[rank[a]] += 1;
                    score[rank[a]] += (a + 1);
                }
            }
//            System.out.println(Arrays.toString(score));

            // 점수 정렬 및 최소 점수 파악
            int minScore = 0;
            int[] cpyArr = Arrays.copyOf(score, score.length);
            Arrays.sort(cpyArr);
            for (int k : cpyArr) {
                if (k != 0) {
                    minScore = k;
                    break;
                }
            }
//            System.out.println("minScore = " + minScore);

            // 최소 점수팀이 여러 팀인지 확인
            int sameScore = 0;
            for (int j : score) {
                if (j == minScore)
                    sameScore += 1;
            }
//            System.out.println("sameScore = " + sameScore);

            // 최소 점수 팀이 한팀이면
            if(sameScore == 1) {
                for(int a = 0; a < score.length; a++) {
                    if(score[a] == minScore)
                        System.out.println(a);
                }
            } else {
                int[] tempCnt = new int[people];
                compare = new ArrayList<>();
                for(int a = 0; a < rank.length; a++) {
                    if(tempCnt[rank[a]] < 4 && score[rank[a]] == minScore)
                        tempCnt[rank[a]] += 1;
                    else if(tempCnt[rank[a]] == 4) {
                        tempCnt[rank[a]] += 1;
                        compare.add(new Info(rank[a], a + 1));
                    }
                }
                Collections.sort(compare);
                System.out.println(compare.get(0).a);
            }
        }
    }
}
