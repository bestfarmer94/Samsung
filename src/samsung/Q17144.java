package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17144 {

	static int r, c, t;
	static int[][] map, clone;
	static int time;
	static int dust;
	static int machine;
	static int[] dx, dy;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		clone = new int[r][c];
		dust = 0;
		
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					machine = i;
				}else {
					dust += map[i][j];
				}
			}
		}
		
		dx = new int[] {-1, 0, 1, 0};
		dy = new int[] {0, 1, 0, -1};
		diffusion();
		
		sb.append(dust);
		System.out.println(sb);
		br.close();
	}

	static void diffusion() {
		
		if(time == t) {
			return;
		}
		
		for(int i=0; i<r; i++) {
			clone[i] = map[i].clone();
		}
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j] >= 5) {
					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if(nx >= 0 && ny >= 0 && nx < r && ny < c) {
							if(map[nx][ny] != -1) {
								clone[nx][ny] += map[i][j]/5;
								clone[i][j] -= map[i][j]/5;
							}
						}
					}
				}
			}
		}
		//
		System.out.println(time);
		for(int i=0; i<r; i++) {
			map[i] = clone[i].clone();
			for(int j=0; j<c; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		cleaning();
	}
	
	static void cleaning() {
		
		dust -= map[machine-2][0];
		dust -= map[machine+1][0];
		
		// 위쪽
		for(int i=machine-2; i>0; i--) {	// 0 열
			map[i][0] = map[i-1][0];
		}
		
		for(int i=0; i<c-1; i++) {	// 0 행
			map[0][i] = map[0][i+1];
		}
		
		for(int i=0; i<machine-1; i++) {	// c-1 열
			map[i][c-1] = map[i+1][c-1];
		}
		
		for(int i=c-1; i>1; i--) {	// machine-1 행
			map[machine-1][i] = map[machine-1][i-1];
		}
		
		map[machine-1][1] = 0;
		
		// 아래쪽
		for(int i=machine+1; i<r-1; i++) {	// 0 열
			map[i][0] = map[i+1][0];
		}
		
		for(int i=0; i<c-1; i++) {	// 0 행
			map[r-1][i] = map[r-1][i+1];
		}
		
		for(int i=r-1; i>machine; i--) {	// c-1 열
			map[i][c-1] = map[i-1][c-1];
		}
		
		for(int i=c-1; i>1; i--) {	// machine 행
			map[machine][i] = map[machine][i-1];
		}
		
		map[machine][1] = 0;
		
		time++;
		diffusion();
	}
}
