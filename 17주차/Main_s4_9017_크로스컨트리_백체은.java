package a11월3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_s4_9017_크로스컨트리 {

	public static void main(String[] args) throws Exception {

		// 점수 가장 작은 팀이 우승
		// 6명 안 되면 제외
		// 제외된 팀 빼고 들어온 순서대로 점수 부여
		// 동점일 경우 5번째로 들어온 선수가 빨리 들어왔어야 함

		// 팀은 200번까지 있을 수 있으니까
		// 201짜리 배열 만들어서
		// 입력 받으면서 해당 인덱스 값++
		// 0 만나면 탐색 빠져나오기 m저장해두기(팀 몇 개인지)
		
		// 이제 순서 어레이 돌면서
		// 201 배열에서 값이 6이면
		// 점수 +1 하면서 점수 배열 만들고
		
		// 이제 팀마다 점수 합산 배열(m짜리) (2차원으로 만들어서 5번째 선수 점수 기록해두기)
		// 점수 비교하고 같으면 5번째 선수 비교해서 우승팀 찾기
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			int[] teamNum = new int[201];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				teamNum[arr[i]]++;
			}
			int m = 0;
			for (int i = 1; i <= 200; i++) {
				if(teamNum[i] == 0) {
					m = i;
					break;
				}
			}
			int[][] score = new int[m][2];
//			for (int i = 0; i < m; i++) {
//				score[i][0] = 1001;
//			}
			int[] cnt = new int[m];
			int get = 0;
			for (int i = 0; i < N; i++) {
//				System.out.println(m + " " + arr[i] + " " + i);
				if(teamNum[arr[i]] == 6 && cnt[arr[i]] < 4) {
					score[arr[i]][0] += ++get;
					cnt[arr[i]]++;
				} else if(teamNum[arr[i]] == 6 && cnt[arr[i]] == 4) {
					score[arr[i]][1] = ++get;
					cnt[arr[i]]++;
				}
			}
			for (int i = 1; i < m; i++) {
				System.out.print(score[i][0] + " ");
			}System.out.println();
			for (int i = 1; i < m; i++) {
				System.out.print(score[i][1] + " ");
			}System.out.println();
			for (int i = 1; i < m; i++) {
				System.out.print(i + " ");
			}System.out.println();
			
			int min = 6000; // 6000점 이상은 못 얻으니까
			int fifth = 1001;
			int win = 0;
			for (int i = 1; i < m; i++) {
				if(score[i][0] != 0 && min > score[i][0]) {
					min = score[i][0];
					fifth = score[i][1];
					win = i;
				} else if(score[i][0] != 0 && min == score[i][0] && fifth > score[i][1]) {
					fifth = score[i][1];
					win = i;
				}
			}
			sb.append(win).append("\n");
		}
		br.close();
		System.out.print(sb);
		
	}

}
