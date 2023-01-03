package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17837 {
	
	static int n;
	static int k;
	static int[][] map;
	static int[][] horse;
	static String[][] map2;
	static int dx[];
	static int dy[];
	
	// 패배 했다. 테스트 케이스는 전부 성공했는데, 4%에서 진행이 안된다. 어느 경우에 실패하는지 결국 못찾았다.
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		map2 = new String[n+1][n+1];
		horse = new int[k][3];
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<n+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				map2[i][j] = "";
			}
		}
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			
			horse[i][0] = Integer.parseInt(st.nextToken());
			horse[i][1] = Integer.parseInt(st.nextToken());
			horse[i][2] = Integer.parseInt(st.nextToken());
			
			map2[horse[i][0]][horse[i][1]] = String.valueOf(i);
		}
		
		int turn = 1;
		dx = new int[] {0, 0, 0, -1, 1};
		dy = new int[] {0, 1, -1, 0, 0};
		
		sb.append(start_turn(turn));
		System.out.println(sb);
	}
	
	static int start_turn(int turn) {
//		System.out.println(turn);
		if(turn > 1000) {
			return -1;
		}
		
		for(int i=0; i<horse.length; i++) {
			if(move_horse(i)) {
				return turn;
			}
		}
		
		return start_turn(turn+1);
	}
	
	static boolean move_horse(int number) {
//		System.out.println("number :" + number);
//		for(int i=1; i<n+1; i++) {
//			for(int j=1; j<n+1; j++) {
//				if(map2[i][j].length() == 0) {
//					System.out.print("0 ");
//				}else {
//					System.out.print(map2[i][j] + " ");
//				}
//			}
//			System.out.println();
//		}
		
		int x = horse[number][0];
		int y = horse[number][1];
		int direction = horse[number][2];
//		System.out.println(x + " " + y);
		String horses = map2[x][y];
		int start = 0;
		String word = "";
		for(int i=0; i<horses.length(); i++) {
			if(horses.charAt(i) - '0' == number) {
				start = i;
				break;
			}
			word += horses.charAt(i);
		}
		
		map2[x][y] = word;
		word = horses.substring(start, horses.length());
		
		int nx = x + dx[direction];
		int ny = y + dy[direction];
		
		if(nx > n || ny > n || map[nx][ny] == 2 || nx == 0 || ny == 0) {
			nx = x - dx[direction];
			ny = y - dy[direction];
			change_direction(number, direction);
		}
		
		if(nx > n || ny > n || map[nx][ny] == 2 || nx == 0 || ny == 0) {
			return false;
			
		}else if(map[nx][ny] == 0) {
			map2[nx][ny] += word;
			for(int i=word.length()-1; i>=0; i--) {
				horse[word.charAt(i)-'0'][0] = nx;
				horse[word.charAt(i)-'0'][1] = ny;
			}
		}else{
			String new_word = "";
			for(int i=word.length()-1; i>=0; i--) {
				new_word += word.charAt(i);
				horse[word.charAt(i)-'0'][0] = nx;
				horse[word.charAt(i)-'0'][1] = ny;
			}
			map2[nx][ny] += new_word;
		}
		
		if(map2[nx][ny].length() >= 4) {
			return true;
		}
		
		return false;
	}
	
	static void change_direction(int number, int direction) {
		
		if(direction < 3) {
			direction = 3 - direction;
		}else {
			direction = 7 - direction;
		}
		
		horse[number][2] = direction;
	}
}	