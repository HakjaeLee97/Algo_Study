package Septempber_fourth;

import java.io.*;
import java.util.*;



public class Main_bj_5972_택배_배송_박한샘 {

    static class Node implements Comparable<Node> {
        int index, distance;
        
        Node (int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        private int getIndex() {
            return this.index;
        }

        private int getDistance() {
            return this.distance;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distance < o.distance) {
                return -1;
            }
            return 1;
        }
    }//Node
	    private static int n,m;
	    private final static int INF = (int) 1e9;

	    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	    private static int start = 1;
	    private static int[] d = new int[50001];

	    public static void main(String[] args) throws IOException {

		        //현서 -> 찬홍
		        //중간에 소들에게 여물을 줘야한다.
		        //최소한의 소만 만나고 싶다.

		        //N(헛간, 정점)
		        //M(길, 간선)

		        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		        StringTokenizer st = new StringTokenizer(br.readLine());

		        n = Integer.parseInt(st.nextToken());
		        m = Integer.parseInt(st.nextToken());

		        for (int i=0; i<=n; i++) {
		            graph.add(new ArrayList<>());
		        }

		        for (int i=0; i<m; i++) {
		            st = new StringTokenizer(br.readLine());

		            int a = Integer.parseInt(st.nextToken());
		            int b = Integer.parseInt(st.nextToken());
		            int c = Integer.parseInt(st.nextToken());

		            graph.get(a).add(new Node(b,c));
		            graph.get(b).add(new Node(a,c));
		        }

		        Arrays.fill(d, INF);

		        dijkstra();

		        System.out.println(d[n]);
		    }

		    private static void dijkstra() {

		        PriorityQueue<Node> pq = new PriorityQueue<>();

		        pq.add(new Node(start,0));
		        d[start] = 0;

		        while (!pq.isEmpty()) {
		            Node node = pq.poll();
		            int dist = node.distance;
		            int now = node.index;

		            if (d[now] < dist) {
		                continue;
		            }

		            for (int i=0; i<graph.get(now).size(); i++) {
		                int cost = d[now] + graph.get(now).get(i).getDistance();

		                if (cost < d[graph.get(now).get(i).getIndex()]) {
		                    d[graph.get(now).get(i).getIndex()] = cost;
		                    pq.add(new Node(graph.get(now).get(i).getIndex(), cost));
		                }
		            }
		        }

		    }//dijkstra

	
		}