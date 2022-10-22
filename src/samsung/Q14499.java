package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14499 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int[] move = new int[k];
		
		for(int i=0; i<k; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dice = new int[7];
		
		for(int i=0; i<k; i++) {
			int temp;
			
			switch(move[i]) {
				case 1 : 
					if(y == m-1) {
						continue;
					}
					temp = dice[3];
					dice[3] = dice[1];
					dice[1] = dice[4];
					dice[4] = dice[6];
					dice[6] = temp;
					y += 1;
					break;
				
				case 2 :
					if(y == 0) {
						continue;
					}
					temp = dice[4];
					dice[4] = dice[1];
					dice[1] = dice[3];
					dice[3] = dice[6];
					dice[6] = temp;
					y -= 1;
					break;
					
				case 3 :
					if(x == 0) {
						continue;
					}
					
					temp = dice[2];
					dice[2] = dice[1];
					dice[1] = dice[5];
					dice[5] = dice[6];
					dice[6] = temp;
					x -= 1;
					break;
				
				case 4 :
					if(x == n-1) {
						continue;
					}
					
					temp = dice[5];
					dice[5] = dice[1];
					dice[1] = dice[2];
					dice[2] = dice[6];
					dice[6] = temp;
					x += 1;
			}
			
			if(map[x][y] == 0) {
				map[x][y] = dice[6];
			}else {
				dice[6] = map[x][y];
				map[x][y] = 0;
			}
			
			sb.append(dice[1]).append('\n');
		}
		
		System.out.println(sb);
		br.close();
	}

}
