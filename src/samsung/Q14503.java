package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14503 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int count = 1;
		
		while(true) {
			map[r][c] = 2;
			for(int i=0; i<4; i++) {
				d--;
				if(d < 0) {
					d += 4;
				}
				
				int nx = r + dx[d];
				int ny = c + dy[d];
				
				if(map[nx][ny] != 0) {
					continue;
				}else {
					r = nx;
					c = ny;
					break;
				}
				
			}
			
			if(map[r][c] == 2) {
				int nx = r - dx[d];
				int ny = c - dy[d];
				
				if(map[nx][ny] != 1) {
					r = nx;
					c = ny;
				}else {
					break;
				}
			}else {
				count++;
			}
		}
		
		sb.append(count);
		System.out.println(sb);
		br.close();
	}
}
