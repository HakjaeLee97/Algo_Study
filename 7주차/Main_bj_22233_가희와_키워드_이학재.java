package b22233;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<String> set = new HashSet<>(N);
		for(int i = 0 ; i<N; i++) {
			set.add(br.readLine());
		}
		int cnt = N;
		for(int i = 0; i<M;i++) {
			st = new StringTokenizer(br.readLine(),",");
			while(st.hasMoreElements()) {
				String tmp = st.nextToken();
				if(set.contains(tmp)) {
					set.remove(tmp);
					cnt--;
				}
			}
			sb.append(cnt).append("\n");
			
		}
		System.out.print(sb);
		br.close();
	}

}
