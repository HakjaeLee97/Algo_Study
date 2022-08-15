import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
		static int row, col;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[101][101]; //인덱스는 1부터 시작함

		row = 3;
		col = 3;
		
//		for(int i = 1; i<= 3; i++) arr[i][0] =3; // 배열 각 행의 첫 원소는 size
//		for(int i = 1; i<= 3; i++) arr[0][i] =3; // 배열 각 열의 첫 원소는 size
		
		for(int i = 1; i<= 3; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 1; j<=3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		while(arr[r][c] != k && time <= 100) { //&& r<= row && c <= col) {
			if(row >= col) { // 행이 열보다 많을 때
				doR(arr);
			} else {
				doC(arr);
			}
			time++;
		}
		if(time == 101) time = -1;
		System.out.println(time);
		
		
	}
	public static void doR(int[][] arr) {
	//	boolean expanded = false;
		for(int i = 1; i<= row; i++) { // 각 행마다 정렬
			Map<Integer, Integer> map = new HashMap<>();
			for(int j= 1; j <= col; j++) { 
				if(arr[i][j] == 0) continue;
				if(map.containsKey(arr[i][j])) {
					map.put(arr[i][j], map.get(arr[i][j])+1);
				} else {
					map.put(arr[i][j], 1);
				}
			}
			List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());
			entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
			    @Override
			    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
			    	int r = o1.getValue() - o2.getValue();
			    	if( r == 0) {
			    		r = o1.getKey() - o2.getKey();
			    	}
				return r;
			    }
			});
			//if(entryList.size() * 2 > col) col = entryList.size() * 2;  expanded = true;
			if(entryList.size() * 2 > col) col = entryList.size() * 2;  
			//arr[i] = new int[col];
			int index = 0;
//			if (entryList.size() < col) {
				for(int j= 1; j <= entryList.size()*2 ; j+= 2) { 
					arr[i][j] = entryList.get(index).getKey();
					arr[i][j+1] = entryList.get(index++).getValue();
				}
				for(int j= entryList.size()*2+1; j <= Math.min(col,100) ; j++) {
					arr[i][j] = 0;
				}
				
//			}
//			else {			
//				for(int j= 1; j <= Math.min(col,99); j+= 2) { 
//					arr[i][j] = entryList.get(index).getKey();
//					arr[i][j+1] = entryList.get(index++).getValue();
//				}
//			}
		}
		//if(expanded) {
	//		for (int i = 0; i < row; i++) {
//				if(arr[i].length < col) {
//					arr[i] = Arrays.copyOf(arr[i], col);
//				}
//			}
//		}
	}
	public static void doC(int[][] arr) {
	//	boolean expanded = false;
		for(int i = 1; i<= col; i++) { // 각 열마다 정렬
			Map<Integer, Integer> map = new HashMap<>();
			for(int j= 1; j <= row; j++) { 
				if(arr[j][i] == 0) continue;
				if(map.containsKey(arr[j][i])) {
					map.put(arr[j][i], map.get(arr[j][i])+1);
				} else {
					map.put(arr[j][i], 1);
				}
			}
			List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());
			entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
			    @Override
			    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
			    	int r = o1.getValue() - o2.getValue();
			    	if( r == 0) {
			    		r = o1.getKey() - o2.getKey();
			    	}
				return r;
			    }
			});
			if(entryList.size() * 2 > row) row = entryList.size() * 2; //  expanded = true;
		//	arr[i] = new int[];
			int index = 0;
			for(int j= 1; j <= entryList.size()*2; j+= 2) { 
				arr[j][i] = entryList.get(index).getKey();
				arr[j+1][i] = entryList.get(index++).getValue();
			}
			for(int j= entryList.size()*2+1; j <= Math.min(row,100) ; j++) {
				arr[j][i] = 0;
			}
		}

	
		
		
//		if(expanded) {
//			for (int i = 0; i < row; i++) {
//				if(arr[i].length < col) {
//					arr[i] = Arrays.copyOf(arr[i], col);
//				}
//			}
//		}
	}
}
