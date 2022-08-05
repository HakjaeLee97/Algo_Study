package coding2;

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
    public static int r, c, t;
    public static int[][] graph;
    public static Queue<Node> virus = new LinkedList<>();
    public static ArrayList<Node> air = new ArrayList<>();
    public static boolean[][] visited;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

public static void activate(int x1, int y1, int x2, int y2) {
	// 상단 공기 청소기
    for(int i = x1 - 1; i >= 0; i--) {
        if(graph[i+1][y1] == -1) {
            graph[i][y1] = 0;
        }
        else {
            graph[i + 1][y1] = graph[i][y1];
            graph[i][y1] = 0;
        }
    }
    for(int j = 1; j < c; j++) {
        graph[0][j-1] = graph[0][j];
        graph[0][j] = 0;
    }
    for(int i = 1; i <= x1; i++) {
        graph[i-1][c-1] = graph[i][c-1];
        graph[i][c-1] = 0;
    }
    for(int j = c-2; j >= 1; j--) {
        graph[x1][j + 1] = graph[x1][j];
        graph[x1][j] = 0;
    }
    // 하단 공기 청소기
    for(int i = x2 + 1; i < r; i++) {
        if(graph[i-1][y2] == -1) {
        	graph[i][y2] = 0;
        }
        else {
        	graph[i-1][y2] = graph[i][y2];
        	graph[i][y2] = 0;
        }
    }
    for(int j = 1; j < c; j++) {
        graph[r-1][j-1] = graph[r-1][j];
        graph[r-1][j] = 0;
    }
    for(int i = r-2; i >= x2; i--) {
        graph[i+1][c-1] = graph[i][c-1];
        graph[i][c-1] = 0;
    }
    for(int j = c-2; j >= 1; j--) {
        graph[x2][j+1] = graph[x2][j];
        graph[x2][j] = 0;
    }
}
public static void bfs() {
    int time = 0;
    // 공기청정기 좌표
    int c1x = air.get(0).getX();
    int c1y = air.get(0).getY();
    int c2x = air.get(1).getX();
    int c2y = air.get(1).getY();
    
    while(!virus.isEmpty()) {
    	// 복사할 그래프
        int[][] copyGraph = new int[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                copyGraph[i][j] = graph[i][j];
            }
        }
        visited = new  boolean[r][c];
        int len = virus.size();
        for(int i = 0; i < len; i++) {
            Node node = virus.poll();
            int x = node.getX();
            int y = node.getY();
            int cnt = 0;
            visited[x][y] = true;
            for(int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if(nx < 0 || ny < 0 || nx >= r || ny >= c)
                    continue;
                if(graph[nx][ny] != -1 && graph[x][y] / 5 > 0) { // 공기 청정기 구역이 아니면
                    copyGraph[nx][ny] += graph[x][y]/5; // 미세먼지 퍼짐
                    cnt += 1; // 구역 개수     
                }
            }
            copyGraph[x][y] = copyGraph[x][y]  - (graph[x][y] / 5) * cnt;
        }
        graph = copyGraph;
        activate(c1x, c1y, c2x, c2y);
        for(int i = 0; i < r; i++) {
        	for(int j = 0; j < c; j++) {
        		if(copyGraph[i][j] > 0)
        			virus.add(new Node(i, j));
        	}
        }
        time += 1;
        if(time == t)
            return;
    }
}
public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    t = Integer.parseInt(st.nextToken());
    graph = new int[r][c];

    // 그래프 입력받기
    for(int i = 0; i < r; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < c; j++) {
            graph[i][j] = Integer.parseInt(st.nextToken()); 
            if(graph[i][j] != 0 && graph[i][j] != -1) // 먼지 구역
                virus.offer(new Node(i, j));
            else if(graph[i][j] == -1) // 공기 청정기
                air.add(new Node(i, j));
        }
    }

    bfs(); // bfs 실행

    int sum = 0;
    for(int i = 0; i < r; i++) {
        for(int j = 0; j < c; j++){
            if(graph[i][j] == -1)
                continue;
            sum += graph[i][j];
        }
    }
    System.out.println(sum);
}
}
