package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q19237 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][n];
		int[][] smell = new int[n][n];
		int[][] shark = new int[m+1][4];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				smell[i][j] = k;
				if(map[i][j] != 0) {
					shark[map[i][j]][0] = i;
					shark[map[i][j]][1] = j;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<m+1; i++) {
			shark[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int[][][] direction = new int[m+1][5][5];
		
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int l=1; l<=4; l++) {
					direction[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int[] dx = {0, -1, 1, 0, 0};
		int[] dy = {0, 0, 0, -1, 1};
		int count = m;
		int time = 0;
		
		while(count != 1 && time <= 1001) {
			
			if(time < 10) {
				System.out.println(time);
				for(int a=0; a<n; a++) {
					for(int b=0; b<n; b++) {
						System.out.print(map[a][b] + " ");
					}
					System.out.println();
				}
			}
			
			for(int i=1; i<=m; i++) {
				if(shark[i][3] == 1) {
					continue;
				}
				
				int confirm = 0;
				
				for(int j=1; j<=8; j++) {
					
					int j_2 = j;
					
					if(j_2 > 4) {	// 냄새 없는 곳이 없음.
						j_2 -= 4;
						confirm = 1;
					}
					//뭔가 하긴하니
					int dir = direction[i][shark[i][2]][j_2];
					int nx = shark[i][0] + dx[dir];
					int ny = shark[i][1] + dy[dir];
					
					if(time < 10) {
						System.out.println("뭔가 하긴 하니 " + i + " " + nx + " " + ny + " " + confirm);
					}
					
					if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
						if(confirm == 0) {
							if(time >= smell[nx][ny]) {	// 냄새 없는 곳 이동.
								if(map[nx][ny] == 0) {
									shark[i][0] = nx;
									shark[i][1] = ny;
									shark[i][2] = dir;
									map[nx][ny] = i;
									if(time < 10) {
										System.out.println("move_1 " + i);
									}
								}else {
									if(nx == shark[map[nx][ny]][0] && ny == shark[map[nx][ny]][1]) {
										shark[i][3] = 1;	// 죽음.
										count -= 1;
										if(time < 10) {
											System.out.println("death " + i);
										}
									}else {	// 옛날에 다녀간 곳이라 상관없음.
										shark[i][0] = nx;
										shark[i][1] = ny;
										shark[i][2] = dir;
										map[nx][ny] = i;
										if(time < 10) {
											System.out.println("move_2 " + i);
										}
									}
								}
								
								break;		// 이동 완료.
							}else {
								continue;	// 냄새 있음. 딴데 갈거임.
							}
						}else {	// confirm == 1	// 냄새 없는 곳이 없음.
							if(map[nx][ny] == i) {	// 자기 냄새 찾음.
								shark[i][0] = nx;
								shark[i][1] = ny;
								shark[i][2] = dir;
								if(time < 10) {
									System.out.println("move_3 " + i);
								}
								break;
							}else {
								continue;	// 자기 냄새가 아님.
							}
						}
					}else {
						continue;	// 맵 벗어남. 딴데 갈거임.
					}
				}
				
			}
			
			time++;
			for(int i=1; i<=m; i++) {
				smell[shark[i][0]][shark[i][1]] = time + k;
			}
		}
		
		if(time > 1000) {
			sb.append(-1);
		}else {
			sb.append(time);
		}
		
		System.out.println(sb);
		br.close();
	}

}
