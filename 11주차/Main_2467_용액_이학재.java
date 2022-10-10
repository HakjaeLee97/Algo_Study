package b2467;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] sol = new int[N];
		for (int i = 0; i < N; i++) {
			sol[i] = Integer.parseInt(st.nextToken());
		}
			
		int best = Integer.MAX_VALUE;
		//투 포인터
		int pos1 = 0;
		int pos2 = N-1;
		//해답 출력용 인덱스 저장 변수
		int ans1 = 0;
		int ans2 = 0;
		
		while(pos1 != pos2) {
//			if(pos2 >= N ) pos2 = N-1;
//			if(pos1 >= N) pos1 = N-1;
			if(Math.abs(best) > Math.abs(sol[pos1] + sol[pos2])) {
				best = sol[pos1] + sol[pos2];
				ans1 = pos1;
				ans2 = pos2;
			}
			if(0> sol[pos1] + sol[pos2]) {
				pos1++;
			}else if( 0< sol[pos1] + sol[pos2]) {
				pos2--;
			}else  {
				break;
			}
		}
		System.out.println(sol[ans1] + " " + sol[ans2]);
	}

}
