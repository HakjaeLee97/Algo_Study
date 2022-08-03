import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] gear1 = new int[9];
		int[] gear2 = new int[10];
		int[] gear3 = new int[10];
		int[] gear4 = new int[9]; // 톱니바퀴의 상태를 저장하는 배열
		//뒤에는 다른 톱니바퀴랑 맞닿는 index를 저장하는 커서
		
		String tmp = br.readLine();
		String[] gear1str = tmp.split("");
		for(int i =0; i<8; i++) {
			gear1[i] = Integer.parseInt(gear1str[i]);
		}
		gear1[8] = 2;
		
		tmp = br.readLine();
		String[] gear2str = tmp.split("");
		for(int i =0; i<8; i++) {
			gear2[i] = Integer.parseInt(gear2str[i]);
		}
		gear2[8] = 6; 
		gear2[9] = 2;
		
		tmp = br.readLine();
		String[] gear3str = tmp.split("");
		for(int i =0; i<8; i++) {
			gear3[i] = Integer.parseInt(gear3str[i]);
		}
		gear3[8] = 6; 
		gear3[9] = 2;
		
		tmp = br.readLine();
		String[] gear4str = tmp.split("");
		for(int i =0; i<8; i++) {
			gear4[i] = Integer.parseInt(gear4str[i]);
		}
		gear4[8] = 6; 
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int rot_gear = Integer.parseInt(st.nextToken()); // 돌릴 톱니바퀴
			int rot = Integer.parseInt(st.nextToken()); // 돌리는 방향
			int[] affected = new int[4]; // 같이 돌아가는 기어들 체크
			int counter = (rot_gear % 2); // 홀수번째 기어가 선택될 경우 짝수번째 기어는 반대방향으로 돌아감
			
			// 시계방향 회전 시 : 커서 1 감소
			// 반시계방향 회전 시 : 커서 1 증가
			for(int j = 0; j<4; j++) {
				if (affected[0] == 1 || rot_gear == 1) {
					affected[0] = 1;
					if( gear1[gear1[8]] != gear2[gear2[8]] ) affected[1] =1; // 2번 기어도 돌아감

				} if (affected[1] == 1 || rot_gear == 2) {
					affected[1] = 1;
					if( gear1[gear1[8]] != gear2[gear2[8]] ) affected[0] =1; // 1번 기어도 돌아감
					if( gear2[gear2[9]] != gear3[gear3[8]] ) affected[2] =1; // 3번 기어도 돌아감
	
				} if (affected[2] == 1 || rot_gear == 3) {
					affected[2] = 1;
					if( gear2[gear2[9]] != gear3[gear3[8]] ) affected[1] =1; // 2번 기어도 돌아감
					if( gear3[gear3[9]] != gear4[gear4[8]] ) affected[3] =1; // 4번 기어도 돌아감
	
				} if (affected[3] == 1 || rot_gear == 4) {
					affected[3] = 1;
					if( gear3[gear3[9]] != gear4[gear4[8]] ) affected[2] =1; // 3번 기어도 돌아감
				}
			}
			
			if (affected[0] == 1) {
				if (counter == 1)	{
					gear1[8] = Math.floorMod((gear1[8] - rot),8) ; //커서 인덱스가 -1일경우 7로 변경
				}
				else {
					gear1[8] = Math.floorMod((gear1[8] + rot),8);
				}
			} if (affected[1] == 1) {
				if (counter == 0)	{// 짝수 번째 기어가 영향 받을 때
					gear2[8] = Math.floorMod((gear2[8] - rot) , 8) ; 
					gear2[9] = Math.floorMod((gear2[9] - rot) ,8) ; 
				} else {
					gear2[8] = Math.floorMod((gear2[8] + rot) , 8) ; 
					gear2[9] = Math.floorMod((gear2[9] + rot) , 8) ; 
				}
			} if (affected[2] == 1) {
				if (counter == 1)	{
					gear3[8] = Math.floorMod((gear3[8] - rot),8);
					gear3[9] = Math.floorMod((gear3[9] - rot) ,8);
				} else {
					gear3[8] = Math.floorMod((gear3[8] + rot),8);
					gear3[9] = Math.floorMod((gear3[9] + rot) ,8);
				}
			} if (affected[3] == 1)
				if (counter == 0)	{// 짝수 번째 기어가 영향 받을 때
					gear4[8] = Math.floorMod((gear4[8] - rot) , 8);
				} else {
					gear4[8] = Math.floorMod((gear4[8] + rot), 8);
				}
			}
		int result = 0;
		result += gear1[Math.floorMod((gear1[8]-2),8)];
		result += gear2[Math.floorMod((gear2[9]-2),8)]*2;
		result += gear3[Math.floorMod((gear3[9]-2),8)]*4;
		result += gear4[Math.floorMod((gear4[8]+2),8)]*8;
		System.out.println(result);
		}


}
