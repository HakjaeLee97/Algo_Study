package a11월3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_s5_8979_올림픽 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] k = new int[3];
		int[][] arr = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(n == K) { // k국 성적 저장
				k = new int[] {g, s, b};
			} 
			arr[i] = new int[] {g, s, b};
		}
		br.close();
		int rank = 1;
		// k국보다 메달 많은 국가 나오면 k국 rank++ 
		for (int i = 0; i < N; i++) { // 금은동 순으로 비교
			if(k[0] < arr[i][0]) {
				rank++;
			} else if(k[0] == arr[i][0]) {
				if(k[1] < arr[i][1]) {
					rank++;
				} else if(k[1] == arr[i][1]) {
					if(k[2] < arr[i][2]) {
						rank++;
					} 
				}
			}
		}
		System.out.println(rank);
	}
}
