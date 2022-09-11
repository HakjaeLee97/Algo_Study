package a0912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_s2_22233_가희와키워드 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<String> memo = new HashSet<>();
		for (int i = 0; i < N; i++) {
			memo.add(br.readLine());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), ",");
			while(st.hasMoreTokens()) {
				String blog = st.nextToken();
				if(memo.contains(blog)) {
					memo.remove(blog);
				}
			}
			sb.append(memo.size()).append("\n");
		}
		br.close();
		System.out.print(sb);
	}

}
