import java.util.*;
import java.io.*;
 
public class Main {
    public static int[] dice, order; // 주사위, 순서
    public static Node[] horse; // 4개의 말 정보를 담을 배열
    public static Node start; // 시작지점
    public static int answer = Integer.MIN_VALUE;
 
    static class Node {  
        int score;        
        boolean isEmpty;  
        boolean isFinish; 
        Node next;
        Node fastPath;   
 
        public Node(int score) {
            this.score = score;
            this.isEmpty = true;
        }
 
        // 노드 연결 (연결 리스트 구조)
        public Node addNext(int score) {
            Node nextNode = new Node(score);
            this.next = nextNode;
            return nextNode;
        }
 
        // 노드 찾기 (지름길 놓는 지점을 찾기 위한 함수)
        public static Node getNode(Node start, int target) {
            Node temp = start;
            while (true) { // 시작 지점부터 탐색해가며 특정 노드를 찾습니다.
                if (temp == null) return null;
                if (temp.score == target) return temp;
                temp = temp.next;
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        dice = new int[11];
        order = new int[11];
        horse = new Node[5];
        for (int i = 1; i <= 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
 
        init(); // 윷놀이판 설정
        permutation(1); // 10개의 주사위 결과를 말들에게 할당
        System.out.println(answer);
    }
 
    private static void permutation(int depth) {
        if (depth >= 11) {
            answer = Math.max(answer, gameStart());
            return;
        }
 
        for (int i = 1; i <= 4; i++) {
            order[depth] = i;
            permutation(depth + 1);
        }
    }
 
    private static int gameStart() {
        Arrays.fill(horse, start); // 말들을 시작 지점으로 배치
 
        int score = 0;
        for (int i = 1; i <= 10; i++) {
            Node cur = horse[order[i]]; // 순열로 할당된 순서대로 말들을 움직입니다.
            cur.isEmpty = true; // 현재 있는 칸을 비워줍니다.
 
            for (int j = 1; j <= dice[i]; j++) { // 주사위에 나온 수치만큼 이동합니다.
                if (j == 1 && cur.fastPath != null) { // 처음 이동을 시작하려는 위치가 파란색 칸이라면.
                    cur = cur.fastPath; // 지름길로 이동(파란색 방향)
                } else {
                    cur = cur.next; // 빨간색 방향으로 이동
                }
            }
 
            horse[order[i]] = cur; // 이동 후, 말 위치 설정
 
            if (!cur.isEmpty && !cur.isFinish) { // 이동을 마친 칸에 다른 말이 있다면, 해당 말은 고를 수 없다.
                score = 0; // 주사위에 할당받은 말들의 순서가 무효처리.
                break;
            } else {
                cur.isEmpty = false; // 말이 존재하는 것으로 표시
                score += cur.score; // 해당 칸의 점수 추가
            }
        } // 게임종료
 
        // 다음 게임을 위해 말들의 위치를 지워줍니다.
        for (int i = 1; i <= 4; i++)
            horse[i].isEmpty = true;
 
        return score; // 획든한 점수 반환
    }
 
    private static void init() {
        start = new Node(0); // 시작 지점. (시작 지점의 스코어는 0)
 
        Node temp = start;
        for (int i = 2; i <= 40; i += 2) {
            temp = temp.addNext(i); // 윷놀이판 바깥쪽 경로 설정
        }
 
        Node end = temp.addNext(0); // 도착 지점. (도착 지점의 스코어는 0)
        end.isFinish = true;
        end.next = end; // 자기자신을 가르키도록 설정 (도착 지점을 넘어서는 이동에 대해 NPE 방지)
 
        Node crossroads = new Node(25); // 가운데 교차점 (score = 25)
 
        // 교차점(25) -> 30 -> 35 -> 40
        temp = crossroads.addNext(30);
        temp = temp.addNext(35);
        temp.next = Node.getNode(start, 40);
 
        // 10 -> 13 -> 16 -> 19 -> 25(교차점)
        temp = Node.getNode(start, 10);
        temp = temp.fastPath = new Node(13);
        temp = temp.addNext(16);
        temp = temp.addNext(19);
        temp.next = crossroads;
 
        // 20 -> 22 -> 24 -> 25(교차점)
        temp = Node.getNode(start, 20);
        temp = temp.fastPath = new Node(22);
        temp = temp.addNext(24);
        temp.next = crossroads;
 
        // 30 -> 28 -> 27 -> 26 -> 25(교차점)
        temp = Node.getNode(start, 30);
        temp = temp.fastPath = new Node(28);
        temp = temp.addNext(27);
        temp = temp.addNext(26);
        temp.next = crossroads;
    }
}
