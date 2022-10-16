package September_first;
import java.util.*;
import java.io.*;
public class Main {

	static int N;
	static int[]arr;
	static int answer;
	static void input() throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine());;
		
		for(int i=0;i<N;i++)  arr[i] = Integer.parseInt(st.nextToken());
		
	}
	static void output() {
		System.out.println(answer);
	}
	
	static void calc() {
		Arrays.sort(arr);	
		for(int i=0;i<N;i++) if(possible(i)) answer++;

	}
	
	static boolean possible(int idx) {
		
		int start = 0;
		int end = N-1;
		int target = arr[idx];
		
		while(true) {
			
			if(idx==start) start++;
			else if(idx==end) end--;
			
			if(end<=start) return false;
			
			int sum = arr[start] + arr[end];
			
			if(sum<target) start++;
			else if(sum>target) end--;
			else if(sum==target) return true;
			
			
		}

	}
	
	public static void main(String[] args) throws Exception {
		
		input();
		calc();
		output();
		
	}
}
