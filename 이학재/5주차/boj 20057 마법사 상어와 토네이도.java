package b20057;

public class Main {
	
	static int outSand = 0;
	static int dx[] = {-2,-1,-1,-1,0,1,1,1,2,0};
	static int dy[] = {0,-1,0,1,-2,-1,0,1,0,-1};
	static int[] sandcalc = {2,10,7,1,5,10,7,1,2,1};
	static int N;
	
	static int[] winddx = {0,1,0,-1};
	static int[] winddy = {-1,0,1,0};
			

	
	public static void main(String[] args) throws Exception {
		N =read();
		int[][] map = new int[N][N];
		
		for(int i =0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = read();
			}
		}
		int x = N/2, y= N/2;
		int offset = 1;
		int movecnt = 0;
		int dir = 0; //왼쪽 0, 아래 1, 오른쪽 2, 위 3
		while(x != 0 || y != 0) {
			x = x + winddx[dir];
			y = y + winddy[dir];
			wind(x,y,map,dir);
			movecnt++;
			if(movecnt == offset) {
				dir = (dir +1) % 4;
				movecnt = 0;
				if(dir%2 ==0)offset++;
			}
		}
		System.out.println(outSand);
		
	}
	public static void wind(int x, int y, int[][]map, int dir) {
		//x,y로 토네이도가 이동할 때 
		int sand = map[x][y];
		int alpha = sand;
		int nx = 0,ny = 0;
		for(int i =0; i<10; i++) {
			
			switch(dir) {
			case 0: //왼쪽
				nx = x + dx[i];
				ny = y + dy[i];
				break;
			case 1://하
				nx = x - dy[i];
				ny = y - dx[i];
				break;
			case 2://오른쪽
				nx = x + dx[i];
				ny = y - dy[i];
				break;
			case 3://상
				nx = x + dy[i];
				ny = y - dx[i];
				break;
			}
			if(i <9) {		
				if(nx < 0 || nx >= N || ny < 0|| ny>= N) { //밖으로 나갈 때
					int tmp = sand * sandcalc[i]/100;
					outSand += tmp;
					alpha -= tmp;
				}else {
					int tmp = sand * sandcalc[i]/100;
					map[nx][ny] += tmp;
					alpha -= tmp;
				}
			}else {
				if(nx < 0 || nx >= N || ny < 0|| ny>= N) { //밖으로 나갈 때
					outSand += alpha;
				}else {
					map[nx][ny] += alpha;
				}
			}
			
		}
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		if(c == 13) System.in.read();
		return n;
	}

}
