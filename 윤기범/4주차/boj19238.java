import java.util.*;
import java.io.*;
 
public class Main {
	public static int N, M, fuel;
	public static int[][] graph;
	public static int[][] distance;
	public static int tx, ty;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	public static PriorityQueue<Person> person = new PriorityQueue<>();
	public static int ans = 0;
	
	static class Person implements Comparable<Person> {
		int x;
		int y;
		int ex;
		int ey;
		int dist;
		
		public Person(int x, int y, int ex, int ey, int dist) {
			this.x = x;
			this.y = y;
			this.ex = ex;
			this.ey = ey;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Person p) {
			int r = this.dist - p.dist;
			if(r == 0) {
				r = this.x - p.x;
				if(r == 0) {
					r = this.y - p.y;
				}
			}
			return r;
		}

		@Override
		public String toString() {
			return "Person [x=" + x + ", y=" + y + ", ex=" + ex + ", ey=" + ey + ", dist=" + dist + "]";
		}
	}
	
	public static void bfs(int x, int y) {
		distance = new int[N][N];
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		distance[i][j] = -1;
        	}
        }
        
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		distance[x][y] = 0;
		
		while(!q.isEmpty()) {
			int[] t = q.poll();
			x = t[0];
			y = t[1];
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if(graph[nx][ny] != 1 && distance[nx][ny] == -1) {
					q.offer(new int[] {nx, ny});
					distance[nx][ny] = (distance[x][y] + 1);
				}
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        
        graph = new int[N][N];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		graph[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        tx = Integer.parseInt(st.nextToken())-1;
        ty = Integer.parseInt(st.nextToken())-1;
        
        bfs(tx, ty);
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken())-1;
            person.offer(new Person(a, b, c, d, distance[a][b]));
        }
        
        while(!person.isEmpty()) {
        	Person p = person.poll();
        	if(p.dist == -1) {
        		fuel = -1;
        		break;
        	}
        	fuel -= p.dist;
        
        	bfs(p.x, p.y);
        	fuel -= distance[p.ex][p.ey];
        	if(fuel < 0) {
        		fuel = -1;
        		break;
        	}
        	else {
        		fuel += distance[p.ex][p.ey] * 2;
        	}
        	bfs(p.ex, p.ey);
        	PriorityQueue<Person> temp = new PriorityQueue<>();
         	while(!person.isEmpty()) {
         		Person p2 = person.poll();
        		temp.offer(new Person(p2.x, p2.y, p2.ex, p2.ey, distance[p2.x][p2.y]));
        	}
         	person = temp;
        }
        System.out.println(fuel);
    }	
}
