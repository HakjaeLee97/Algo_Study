import java.util.*;
import java.io.*;

class Info implements Comparable<Info>{
	public int num;
	public int cnt;
	public Info(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Info o) {
		int r = this.cnt - o.cnt;
		if(r == 0) {
			r = this.num - o.num;
		}
		return r;
	}
	@Override
	public String toString() {
		return " [num=" + num + ", cnt=" + cnt + "]";
	}
	
}

public class Main {
	public static int r, c, k;
	public static int[][] A = new int[101][101];
	public static int time = 0;
	public static ArrayList<Info> arr;
	public static int numOfRow;
	public static int numOfCol;
	public static int[] numbers;
	
	public static void actionR() {	
		ArrayList<ArrayList<Info>> temp = new ArrayList<ArrayList<Info>>();
		int maxCol = Integer.MIN_VALUE;
		int tempNum = 0; 
		for(int i = 0; i < numOfRow; i++) {
			temp.add(new ArrayList<Info>());
		}
		
		for(int i = 0; i < numOfRow; i++) {
			arr = new ArrayList<>();
			numbers = new int[100];
			
			tempNum = 0;
			for(int j = 0; j < numOfCol; j++) {
				if(A[i][j] != 0) {
					numbers[A[i][j]-1]++;
				}
			}
			for(int j = 0; j < 100; j++) {
				if(numbers[j] != 0) {
					arr.add(new Info(j + 1, numbers[j]));
				}
			}
			Collections.sort(arr);
			for(int j = 0; j < arr.size(); j++) {
				int a = arr.get(j).num;
				int b = arr.get(j).cnt;
				temp.get(i).add(new Info(a, b));
				tempNum+=2;
			}
			maxCol = Math.max(maxCol, tempNum);
		}
		for(int i = 0; i < numOfRow; i++) {
			int idx = 0;
			for(int j = 0; j < temp.get(i).size(); j++) {
				int a = temp.get(i).get(j).num;
				int b = temp.get(i).get(j).cnt;
				A[i][idx] = a;
				A[i][idx+1] = b;
				idx += 2;
			}
			for(int j = idx; j < maxCol; j++) {
				A[i][j] = 0;
			}
			for(int j = maxCol; j < 101; j++) {
				A[i][j] = -1;
			}
		}
	}
	
	public static void actionC() {
		ArrayList<ArrayList<Info>> temp = new ArrayList<ArrayList<Info>>();
		int maxRow = Integer.MIN_VALUE;
		int tempNum = 0; 
		for(int i = 0; i < numOfCol; i++) {
			temp.add(new ArrayList<Info>());
		}
		
		for(int i = 0; i < numOfCol; i++) {
			arr = new ArrayList<>();
			numbers = new int[100];
			
			tempNum = 0;
			for(int j = 0; j < numOfRow; j++) {
				if(A[j][i] != 0) {
					numbers[A[j][i]-1]++;
				}
			}
			for(int j = 0; j < 100; j++) {
				if(numbers[j] != 0) {
					arr.add(new Info(j + 1, numbers[j]));
				}
			}
			Collections.sort(arr);
			for(int j = 0; j < arr.size(); j++) {
				int a = arr.get(j).num;
				int b = arr.get(j).cnt;
				temp.get(i).add(new Info(a, b));
				tempNum+=2;
			}
			maxRow = Math.max(maxRow, tempNum);
		}
		for(int i = 0; i < numOfCol; i++) {
			int idx = 0;
			for(int j = 0; j < temp.get(i).size(); j++) {
				int a = temp.get(i).get(j).num;
				int b = temp.get(i).get(j).cnt;
				A[idx][i] = a;
				A[idx+1][i] = b;
				idx += 2;
			}
			for(int j = idx; j < maxRow; j++) {
				A[j][i] = 0;
			}
			for(int j = maxRow; j < 101; j++) {
				A[j][i] = -1;
			}
		}
	}
	
	public static void sort() { 
		while(true) {
			if(A[r][c] == k)
				break;
			if(time >= 101) {
				time = -1;
				break;
			}
			else {
				numOfRow = 0; // 행의 개수
				numOfCol = 0; // 열의 개수
				for(int i = 0; i < 100; i++) {
					if(A[i][0] != -1) {
						numOfRow++;
					} 
					else {
						break;
					}
				}
				for(int i = 0; i < 100; i++) {
					if(A[0][i] != -1) {
						numOfCol++;
					} 
					else {
						break;
					}
				}
				if (numOfRow >= numOfCol) {
					actionR();
				} else {
					actionC();
				}
			}
			time += 1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				A[i][j] = -1; // 초기 -1로 설정
			}
		}
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sort();
		
		System.out.println(time);
	}
}	 
