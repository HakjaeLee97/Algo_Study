package b1238;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, X;
    static Node[] adjList, adjListReverse;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new Node[V + 1];
        adjListReverse = new Node[V + 1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, weight, adjList[from]);
            adjListReverse[to] = new Node(from, weight, adjListReverse[to]);
        }

        int[] distFromX = dijkstra(X, adjList);
        int[] distToX = dijkstra(X, adjListReverse);

        int answer = 0;
        for (int i = 1; i <= V; i++) {
            int dist = distFromX[i] + distToX[i];
            answer = Math.max(answer, dist);
        }

        System.out.println(answer);

    }

    private static int[] dijkstra(int start, Node[] adjList) {
        int[] dist = new int[V + 1];
        isVisited = new boolean[V + 1];
        Arrays.fill(dist, 100001);
        Arrays.fill(isVisited, false);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        for (Node cur = adjList[start]; cur != null; cur = cur.next) {
            pq.offer(new Node(start, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curVertex = cur.vertex;

            if (isVisited[curVertex]) {
                continue;
            }

            isVisited[curVertex] = true;

            for (Node temp = adjList[curVertex]; temp != null; temp = temp.next) {
                int nextVertex = temp.vertex;

                if (isVisited[nextVertex]) {
                    continue;
                }

                if (dist[nextVertex] > dist[curVertex] + temp.weight) {
                    dist[nextVertex] = dist[curVertex] + temp.weight;
                    pq.offer(new Node(nextVertex, dist[nextVertex]));
                }

            }
        }

        return dist;
    }


    private static class Node implements Comparable<Node> {

        int vertex;
        Node next;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}