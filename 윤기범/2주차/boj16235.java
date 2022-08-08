import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Tree implements Comparable<Tree>{
    public int x;
    public int y;
    public int age;
    public Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Tree o) {
        return this.age - o.age;
    }
}

public class Main {
    public static int N, M, K;
    public static int[][] graph;
    public static int[][] arr;
    public static PriorityQueue<Tree> trees = new PriorityQueue<>();
    public static ArrayList<Tree> dead = new ArrayList<>();
    public static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void start() {
        Tree cur;
        for(int i = 0; i < 4; i++) {
            if(i == 0) { // 봄
                for(int j = 0; j < trees.size(); j++) {
                    Tree te = trees.poll();
                    int x = te.getX();
                    int y = te.getY();
                    int age = te.getAge();
                    if(arr[x][y] - age >= 0) {
                        arr[x][y] -= age;
                        trees.add(new Tree(x, y, age+1));
                    } else {
                        dead.add(new Tree(x, y, age));
                    }
                }
            }
            else if (i == 1){ // 여름
                for(Iterator<Tree> it = dead.iterator(); it.hasNext();) {
                //for(int j = 0; j < dead.size(); j++) {
                    cur = it.next();
                    int x = cur.getX();
                    int y = cur.getY();
                    int age = cur.getAge();
                    arr[x][y] += age/2;
                }
                dead.clear();
            }
            else if(i == 2) { // 가을
                List<Tree> temp = new ArrayList<>();
                int len = trees.size();
                for(int j = 0; j < len; j++) {
                    Tree te = trees.poll();
                    int x = te.getX();
                    int y = te.getY();
                    int age = te.getAge();
                    if(age % 5 == 0) {
                        for(int t = 0; t < 8; t++) {
                            int nx = x + dx[t];
                            int ny = y + dy[t];
                            if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                                continue;
                            temp.add(new Tree(nx, ny, 1));
                        }
                        trees.add(new Tree(x, y, age));
                    }
                    else {
                        trees.add(new Tree(x, y, age));
                        continue;
                    }
                }
                trees.addAll(temp);
            }
            else { // 겨울
                for(int j = 0; j <N; j++) {
                    for(int k = 0; k < N; k++) {
                        arr[j][k] += graph[j][k];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        arr = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = 5;
            }
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x-1, y-1, age));
        }
        int year = 0;
        while(true) {
            year++;
            start();
            if(year == K) {
                System.out.println(trees.size());
                break;
            }
        }
    }
}
