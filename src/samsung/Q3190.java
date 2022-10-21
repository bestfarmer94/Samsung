package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q3190 {

	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n+1][n+1];
		StringTokenizer st;
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = -1;
		}
		
		int l = Integer.parseInt(br.readLine());
		String[] dir = new String[10001];
		
		for(int i=0; i<l; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String b = st.nextToken();
			dir[a] = b;
		}
		
		int x = 1;
		int y = 1;
		int length = 1;
		int time = 0;
		int dir_now = 0;
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		while(true) {
			
			time++;
			
			System.out.println(time + " " + length);
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			int nx = x + dx[dir_now];
			int ny = y + dy[dir_now];
			
			if(nx > n || ny > n || nx <= 0 || ny <= 0) {
				break;
			}
			
			if(map[nx][ny] == -1) {
				length ++;
				map[nx][ny] = time;
			}else {
				if(map[nx][ny] == 0) {
					map[nx][ny] = time;
				}else {
					if(time > map[nx][ny] + length) {
						map[nx][ny] = time;
					}else {
						break;
					}
				}
			}
			
			x = nx;
			y = ny;
			
			if(dir[time] != null) {
				if(dir[time].equals("L")) {
					dir_now -= 1;
					if(dir_now < 0) {
						dir_now += 4;
					}
				}else {
					dir_now += 1;
					dir_now %= 4;
				}
			}
		}
		
		sb.append(time);
		System.out.println(sb);
		br.close();
	}
}