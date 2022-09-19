package September_third;


import java.util.*;

/*
 * 
5,5,5,5,5
5,5,5,5,5
5,5,5,5,5
5,5,5,5,5

100344
120232
210312
101331

type r1 c1 r2 c2 degree

1일경우 -> 공격 2일 경우 -> 회복

result 10
 * 
 * */

public class Solution_pr_lv3_파괴되지않는건물_박한샘 {

	
	
	public static void main(String[] args) {
		Solution_파괴되지않는건물 solution = new Solution_파괴되지않는건물();
		int [][]board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
		int [][]skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
		System.out.println(solution.solution(board,skill));
	}

}

class Solution_파괴되지않는건물 {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int preSum[][] = new int[N+1][M+1]; // 누적합 배열은 size가 1 더 큼
        
        for(int i=0; i<skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1], c1 = skill[i][2];
            int r2 = skill[i][3], c2 = skill[i][4];
            int degree = skill[i][5];
            
            if(type == 1){  // destroy
                preSum[r1][c1] += -degree;
                preSum[r2+1][c1] += degree;
                preSum[r1][c2+1] += degree;
                preSum[r2+1][c2+1] += -degree;
            }else{  // repair
                preSum[r1][c1] += degree;
                preSum[r2+1][c1] += -degree;
                preSum[r1][c2+1] += -degree;
                preSum[r2+1][c2+1] += degree;
            }
        }
        
        /* 가로 누적합 계산 */
        for(int i=0; i<N+1; i++){
            int sum = 0;
            for(int j=0; j<M+1; j++){
                sum += preSum[i][j];
                preSum[i][j] = sum;
            }
        }
        
        /* 세로 누적합 계산*/        
        for(int i=0; i<M; i++){
            int sum = 0;
            for(int j=0; j<N; j++){
                sum += preSum[j][i];
                preSum[j][i] = sum;
            }
        }
        
        /* count */
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(preSum[i][j] + board[i][j] > 0 ) answer++;
            }
        }                   
        
        return answer;
    }
    
}