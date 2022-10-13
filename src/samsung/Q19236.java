package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q19236 {

	static int max = 0;
	int[][] aquaMan;
	static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};	// 0 은 없는거다.
	static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st;
		
		int[][] aquaMan = new int[4][4];
		
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {	// 100의 자리가 물고기 넘버, 일의 자리가 물고기 방향으로 설정했다.
				aquaMan[i][j] = Integer.parseInt(st.nextToken())*100 + Integer.parseInt(st.nextToken());
			}
		}
		
		// 먹어 치움
		int result = aquaMan[0][0] / 100;	
		aquaMan[0][0] %= 100;
		
		fish_move(aquaMan, 0, 0, result);
		sb.append(max);
		System.out.println(sb);
		br.close();
	}
	
	static void shark_move(int[][] aquaMan, int x, int y, int result) {

		for(int i=1; i<=3; i++) {	// 갈 수 있을 만큼 이동.
			
			int nx = x + dx[aquaMan[x][y]%100] * i;
			int ny = y + dy[aquaMan[x][y]%100] * i;
			
			int[][] clone = new int[4][4];
			
			for(int j=0; j<4; j++) {
				for(int k=0; k<4; k++) {
					clone[j][k] = aquaMan[j][k];
				}
			}
			
			if(nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
				if(clone[nx][ny] != 0) {	// 먹어 치움
					int food = clone[nx][ny]/100;
					clone[nx][ny] %= 100;
					clone[x][y] = 0;
					fish_move(clone, nx, ny, result + food);
				}else {
					continue;	// 먹이가 없음.
				}
			}else {
				break;	// 배열 틀을 벗어남.
			}
		}
		
	}
	
	static void fish_move(int[][] aquaMan, int x, int y, int result) {
		
		max = Math.max(result, max);	// max 확인.
		
		for(int a=1; a<=16; a++) {
			
			int[] fish = new int[4];	// 원래 priority큐 쓸라다가 실패했는데, 다바꾸기 귀찮아서 이러한 형태를 유지.
			
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(aquaMan[i][j]/100 == a) {
						fish = new int[] {a, i, j, aquaMan[i][j]%100};
					}
				}
			}
			
			if(fish[0] == 0) {	// 해당하는 물고기가 없거나, 상어임.
				continue;
			}
			
			int i = fish[3];	// 물고기의 최초방향.
			while(true){
				int nx = fish[1] + dx[i];
				int ny = fish[2] + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < 4 && ny < 4 && !(nx == x && ny == y)) {
					if(aquaMan[nx][ny] != 0) {	// 물고기 교환.
						aquaMan[fish[1]][fish[2]] = aquaMan[nx][ny];
						aquaMan[nx][ny] = fish[0]*100 + i;
						
					}else {	// 해당 좌표에 물고기가 없을 때
						aquaMan[nx][ny] = fish[0]*100 + i;
						aquaMan[fish[1]][fish[2]] = 0;
					}
					
					break;	// 이동 했으면 다음 물고기.
				}
				
				// 이동 못하면 여기 까지 온다. 방향 전환.
				i++;
				if(i > 8) {
					i %= 8;
				}
				
				if(i == fish[3]) {	// 갈 곳 없는 물고기.
					break;
				}
			}
		}
		
		shark_move(aquaMan, x, y, result);	// 상어의 턴.
	}
}
