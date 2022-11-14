package b9527;

import java.io.*;
import java.util.StringTokenizer;

/**
 *  No.9527: 1의 개수 세기
 *  URL: https://www.acmicpc.net/problem/9527
 *  Hint: 비트연산 + 규칙 찾기
 */

public class Main {
    static long[] bit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        initBitCount();

        long ans = getBitCount(b) - getBitCount(a - 1);
        bw.write(String.valueOf(ans));
        br.close();
        bw.close();
    }

    static long getBitCount(long x) {
        long ans = x & 1;

        for (int i = 54; i > 0; i--) {
            if ((x & (1L << i)) > 0L) { // 숫자 x의 i번째 비트가 1이면
                ans += bit[i - 1] + (x - (1L << i) + 1);
                x -= (1L << i);
            }
        }
        return ans;
    }

    // i번째 비트가 1일 때의 비트카운트(누적)
    static void initBitCount() {
        bit = new long[55]; // 입력의 최댓값인 10^16의 비트길이는 54이므로 (0부터)
        bit[0] = 1;
        for (int i = 1; i < 55; i++) {
            bit[i] = 2 * bit[i - 1] + (1L << i);
        }
    }
}

	/* 00000 0  0
	 * 00001 1  1
	 * 00010 1  2
	 * 00011 2  4
	 * 00100 1  5 
	 * 00101 2  7
	 * 00110 2  9  
	 * 00111 3  12
	 * 01000 1  13 
	 * 01001 2  15 
	 * 01010 2  17
	 * 01011 3  20
	 * 01100 2  22
	 * 01101 3  25
	 * 01110 3  28
	 * 01111 4  32
	 * 10000 1
	 * 10001 2
	 * 10010 2
	 * 10011 3
	 * 10100 2
	 * 10101 3
	 * 10110 3
	 * 10111 4
	 * 11000 2
	 * 11001 3
	 * 11010 3
	 * 11011 4
	 * 11100 3
	 * 11101 4
	 * 11110 4
	 * 11111 5
	*/

