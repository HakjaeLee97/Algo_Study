package b13144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        long ans = 0;

        HashSet<Integer> visited = new HashSet<>();   //숫자 체크할 해쉬셋
        int left = 0;
        int right = 0;

        while(true) {
            if(right==N) {
                if(left==N) break;

                else {
                    ans += (right-left);
                    left++;
                }
            }

            else if(!visited.contains(arr[right])) {    //포함안된 숫자면 포인터 늘림
                visited.add(arr[right]);
                right++;
            }

            else {
                ans += (right-left);          //이미 포함된 숫자면 다 더해주고 시작 idx 늘림
                visited.remove(arr[left]);
                left++;
            }
        }

        System.out.println(ans);
        br.close();
    }

}
