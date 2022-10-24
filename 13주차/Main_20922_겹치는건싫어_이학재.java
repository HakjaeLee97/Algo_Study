package b20922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] freq = new int[100001];
		int[] seq = new int[N];
		int maxlen = 1;
		int tmp;
		st =  new StringTokenizer(br.readLine()," ");
		seq[0] = Integer.parseInt(st.nextToken());
		freq[seq[0]]++;
		int pos1 = 0;
		int pos2 = 0;
		while(pos2< N) {

			maxlen = Math.max(pos2-pos1+1, maxlen);
			pos2++;
			if(pos2>=N) break;
			tmp = Integer.parseInt(st.nextToken());
			freq[tmp]++;
			seq[pos2] = tmp;
			//늘어난 pos2 커서로 인해 조건이 불만족 될경우
			if(freq[tmp]>K) {
				while(seq[pos1] != tmp) {
					freq[seq[pos1]]--;
					pos1++;
				}
				freq[seq[pos1]]--;
				pos1++;
			}

		}
		System.out.println(maxlen);
		br.close();
	}

}
