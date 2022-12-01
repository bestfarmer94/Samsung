package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17837 {

	static int n, k;
	static int[][] map;
	static String[][] chess;
	static int[][] horse;
	static int[] dx, dy;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		horse = new int[k][3];
		chess = new String[n+1][n+1];
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<n+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			
			horse[i][0] = Integer.parseInt(st.nextToken());
			horse[i][1] = Integer.parseInt(st.nextToken());
			horse[i][2] = Integer.parseInt(st.nextToken());
			
			chess[horse[i][0]][horse[i][1]] = Integer.toString(i);
		}
		
		dx = new int[] {0, 0, 0, -1, 1};
		dy = new int[] {0, 1, -1, 0, 0};
		
		answer = move(1);
		
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}

	static int move(int time) {
		
		if(time > 1000) {
			return -1;
		}
		
		int max = 0;
		
		for(int i=0; i<horse.length; i++) {
			if(max >= 4) {
				break;
			}
			
			int[] h = horse[i];
			
			int move_point = 0;
			String curStr = chess[h[0]][h[1]];
			
			for(int j=0; j<curStr.length(); j++) {
				if(curStr.charAt(j) == i - '0') {
					move_point = j;
					break;
				}
			}
			
			curStr = "";
			String moveStr = "";
			
			for(int j=0; j<chess[h[0]][h[1]].length(); j++) {
				if(j < move_point) {
					curStr += chess[h[0]][h[1]].charAt(j);
				}else {
					moveStr += chess[h[0]][h[1]].charAt(j);
				}
			}
			
			chess[h[0]][h[1]] = curStr;
			
			int nx = h[0] + dx[h[2]];
			int ny = h[1] + dy[h[2]];
			
			if(nx > 0 && ny > 0 && nx <= n && ny <= n) {
				
				if(map[nx][ny] == 0) {
					chess[nx][ny] += moveStr;
					
				}else if(map[nx][ny] == 1) {
					chess[nx][ny] += moveStr;
					String nextStr = "";
					
					for(int k=chess[nx][ny].length()-1; k>=0; k--) {
						nextStr += chess[nx][ny].charAt(k);
					}
					
					chess[nx][ny] = nextStr;
					
				}else if(map[nx][ny] == 2) {
					
					nx -= 2 * dx[h[2]];
					ny -= 2 * dy[h[2]];
					
					if(horse[i][2] <= 2) {
						horse[i][2] = 3 - horse[i][2];
					}else {
						horse[i][2] = 7 - horse[i][2];
					}
					
					if(map[nx][ny] == 2) {
						// 이거 재귀가 되어야 하는데 함수를 쪼개야 할 것 같다.
					}
				}
			}else {
				
			}
			
		}
		
		if(max >= 4) {
			return time;
		}
		
		return move(time+1);
	}
}
