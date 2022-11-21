import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (stack.size() == 0) {
                stack.add(b);
            } else {
                while(stack.peek() > b) {
                    cnt += 1;
                    stack.pop();
                    if (stack.size() == 0)
                        break;
                }
                if(stack.size() == 0)
                    stack.push(b);
                else if(stack.peek() == b)
                    continue;
                else if(stack.peek() < b)
                    stack.push(b);
            }
        }

        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i) > 0)
                cnt += 1;
        }

        System.out.println(cnt);
    }
}
