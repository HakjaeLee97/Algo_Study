package b1027;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		//빌딩의 높이
		int[] h = new int[N];
		for (int i = 0; i < N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		//볼 수 있는 빌딩의 개수
		int[] visible = new int[N];
		
		//왼쪽, 오른쪽으로 기울기의 최대값
		double left=0,right=0;
		//현재 검사하는 기울기
		double slope = 0;

		//각 빌딩마다 좌우로 보이는 빌딩의 개수를 구함
		for (int i = 0; i < N; i++) {
			left = -Double.MAX_VALUE;
			right = -Double.MAX_VALUE;
			//왼쪽 확인
			for(int j = i-1; j>=0; j--) {
				//기울기 계산
				slope = - 1.0 * (h[i]-h[j]) / (i - j);
				if(slope > left) {
					visible[i]++;
					left = slope;
				}
				
			}
			//오른쪽 확인
			for(int j = i+1; j<N; j++) {
				//기울기 계산
				slope =  1.0 * (h[j]-h[i]) / (j - i);
				if(slope > right) {
					visible[i]++;
					right = slope;
				}
				
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			if(visible[i] > max) {
				max = visible[i];
			}
		}
		System.out.println(max);
		br.close();
	}

}
