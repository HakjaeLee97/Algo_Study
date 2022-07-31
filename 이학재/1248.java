package s1248;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static int V, E, a, b;
    private static int size;
    private static Node[] nodes;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
 
            nodes = new Node[V + 1];
            visited = new boolean[V + 1];
            for (int i = 1; i <= V; i++) {
                nodes[i] = new Node(i);
            }
 
            // 트리 생성
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                if (nodes[parent].leftChildIdx == 0) nodes[parent].leftChildIdx = child;
                else nodes[parent].rightChildIdx = child;
                nodes[child].parentIdx = parent;
            }
 
            // 공통 조상 찾기
            // 공통 조상을 찾고자 하는 두 노드에 대해 각각 부모 노드를 찾아가며 마킹한다.
            // 이때 하나의 노드가 이미 마킹된 부모노드를 만났다면 그게 공통 조상인 것.
            int commonParent = 1;
            while (true) {
                if (a != 1) {
                    int parent = nodes[a].parentIdx;
                    if (visited[parent]) {
                        commonParent = parent;
                        break;
                    }
                    visited[parent] = true;
                    a = parent;
                }
                if (b != 1) {
                    int parent = nodes[b].parentIdx;
                    if (visited[parent]) { // 부모노드가 이미 마킹되어 있다면 이게 최소 공통 조상노드이다.
                        commonParent = parent;
                        break;
                    }
                    visited[parent] = true;
                    b = parent;
                }
            }
            size = 0;
            get(nodes[commonParent]);
            sb.append("#").append(tc).append(" ").append(commonParent).append(" ").append(size).append("\n");
        }
        System.out.println(sb);
    }
 
    static void get(Node node) {
        size++;
        if (node.leftChildIdx != 0) get(nodes[node.leftChildIdx]);
        if (node.rightChildIdx != 0) get(nodes[node.rightChildIdx]);
    }
 
    private static class Node {
        int data;
        int parentIdx, leftChildIdx, rightChildIdx;
 
        Node(int data) {
            this.data = data;
            this.parentIdx = 0;
            this.leftChildIdx = 0;
            this.rightChildIdx = 0;
        }
    }
 
}