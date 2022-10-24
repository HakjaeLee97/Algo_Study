package b20006;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static class Room{
//		int roomlevel;
//		int index;
//		User[] users;
		List<User> users = new ArrayList<>();
//		public Room(int level, String userid, int m) {
//			super();
//			this.roomlevel = level;
//			this.users = new User[m];
//			this.users[0] = new User(level, userid);
//			this.index= 1;
//		}
		
	}
	public static class User implements Comparable<User>{
		int level;
		String userid;
		public User(int level, String name) {
			super();
			this.level = level;
			this.userid = name;
		}
		@Override
		public int compareTo(User o) {
			return this.userid.compareTo(o.userid);
		}
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int p = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int l;
		String uid;
		ArrayList<Room> rooms = new ArrayList<>();
		outer: for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine()," ");
			l = Integer.parseInt(st.nextToken());
			uid = st.nextToken();
			for(Room r:rooms) {
				if(r.users.size()>= m) continue;
				
				if(r.users.get(0).level - 10 <= l && l <= r.users.get(0).level + 10 ) {
					r.users.add(new User(l,uid));
					continue outer;
				}
			}
			//맞는 방이 없으면 방을 추가한다.
			Room room = new Room();
			room.users.add(new User(l,uid));
			rooms.add(room);
			
		}
		for(Room r: rooms) {
			if(r.users.size() == m) {
				bw.write("Started!\n");
			}else {
				bw.write("Waiting!\n");
			}
			Collections.sort(r.users);
			for (int i = 0; i < r.users.size(); i++) {
				bw.write(r.users.get(i).level + " " +  r.users.get(i).userid+ '\n');
				
			}
		}
		bw.flush();
		br.close();
	}

}
