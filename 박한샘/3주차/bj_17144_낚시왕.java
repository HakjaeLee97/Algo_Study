package August_second;

import java.io.*;
import java.util.*;



class Shark {
	int x, y, speed, dir, size;
}



public class bj_17144_낚시왕 {
	
	
	private static int r, c, m;
	private static int[][] map;
	
	private static int[] dx = {0, -1, 1, 0, 0}; //0, 상, 하, 우, 좌
	private static int[] dy = {0, 0, 0, 1, -1};
	
	private static HashMap<Integer, Shark> sharks = new HashMap();
	
	private static int result = 0;	
	
	public static void main(String[] args) throws IOException {
		
		//input
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		r = Integer.parseInt(st.nextToken()); //행
		c = Integer.parseInt(st.nextToken()); //열
		m = Integer.parseInt(st.nextToken()); //상어 수
		
		if (m == 0) { //상어가 없으면 0 출력
			System.out.println(0);
			return;
		}
		
		map = new int[r+1][c+1]; //인덱스 값을 위한 크기 설정
		
		for (int i=0; i<m; i++) { //상어수만큼
			
			st = new StringTokenizer(reader.readLine());
			
			Shark shark = new Shark();
			
			shark.x = Integer.parseInt(st.nextToken());
			shark.y = Integer.parseInt(st.nextToken());
			shark.speed = Integer.parseInt(st.nextToken());
			shark.dir = Integer.parseInt(st.nextToken());
			shark.size = Integer.parseInt(st.nextToken());
			
			//맵크기
			int cases = 0;
			
			//두가지 경우로 나뉘어서 계산함(명시적)
			if (shark.dir <= 2) {
				//아래 위로 계산
				//맵크기(이동거리) = 맵크기 -1 *2 (절대값 계산을 위해)
				cases = (r-1)*2;
			} else {
				//좌우로 계산
				cases = (c-1)*2;
			}
			
				//만약에 상어 속도가 map 크기보다 크다면
			if (cases >= shark.speed) {
				//상어 속도를 케이스를 나눈값의 나머지로 고정(이동시를 고려)
				shark.speed %= cases;
			}
			//맵에 상어 위치 넣어주기(사이즈별로 )
			map[shark.x][shark.y] = shark.size;
			sharks.put(shark.size, shark); //맵에넣기
		}
		
		for (int y=1; y<=c; y++) {
			fishShark(y);//상어잡기
			moveShark();//상어이동
		}
		
		System.out.println(result);
		
	}

								//낚시왕 위치
	private static void fishShark(int y) {
		
		for (int x=1; x<=r; x++) { //인덱스 조정
			if (map[x][y] != 0) { //상어가 있을겨우
				result += map[x][y]; //낚시 ㄱ
				sharks.remove(map[x][y]);
				map[x][y] = 0;
				return;
			}
		}
		
	}

	private static void moveShark() {
		
		//템프 맵
		int[][] temp = new int[r+1][c+1];
		
		Queue<Integer> failer = new LinkedList<Integer>();
		
		for (Integer key : sharks.keySet()) { //상어정보
			
			//hashmap에 들어있는 샤크 한개씩 정보를 가져온다.
			Shark shark = sharks.get(key);
			
			map[shark.x][shark.y] = 0;
			
			for (int i=0; i<shark.speed; i++) {
				//머리 모양이 1이면(위) 부딪히고 돌아오는 거 계산
				if (shark.dir == 1 && shark.x == 1) {
					shark.dir = 2;
				} 
				//아래(마지막 인덱스 값이면)
				else if (shark.dir == 2 && shark.x == r) {
					shark.dir = 1;
				} 
				//오른
				else if (shark.dir == 3 && shark.y == c) {
					shark.dir = 4;
				} 
				//왼
				else if (shark.dir == 4 && shark.y == 1) {
					shark.dir = 3;
				}
				
				//상어 위치 조정
				shark.x += dx[shark.dir];
				shark.y += dy[shark.dir];
			}
			
			if (temp[shark.x][shark.y] == 0) {
				temp[shark.x][shark.y] = shark.size; 
			} else if (temp[shark.x][shark.y] < shark.size) {
				//현재가 이전값보다 클 때 이전값 잡아먹음
				failer.add(temp[shark.x][shark.y]);
				temp[shark.x][shark.y]= shark.size; 
			} else {
				//현재가 이전값보다 작을 때
				failer.add(shark.size);
			}
			
		}//shark
		
		while (!failer.isEmpty()) {
			sharks.remove(failer.poll());
		}
		
		for (Integer key : sharks.keySet()) {
			Shark shark = sharks.get(key);
			map[shark.x][shark.y]= temp[shark.x][shark.y]; 
		}
		
	}
	
}


