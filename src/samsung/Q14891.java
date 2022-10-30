package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14891 {

	static char[][] gear;
	static int[] rotation;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		gear = new char[5][8];
		for(int i=1; i<=4; i++) {
			gear[i] = br.readLine().toCharArray();
		}
		
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] move = new int[k][2];
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			move[i][0] = Integer.parseInt(st.nextToken());
			move[i][1] = Integer.parseInt(st.nextToken());
		}

		rotation = new int[5];
		int score = 0;
		
		for(int i=0; i<k; i++) {
			move_gear(move[i][0], move[i][1], 0);
		}
		
		for(int i=1; i<=4; i++) {
			if(gear[i][(8-rotation[i])%8] - '0' == 1) {
				score += Math.pow(2, i-1);
			}
		}
		
		sb.append(score);
		System.out.println(sb);
		br.close();
	}
	
	static void move_gear(int now, int dir, int spread) {
		
		if(now != 4 && spread != -1) {	// 오른쪽으로 이동
			if(gear[now][(2-rotation[now]+8)%8] != gear[now+1][(6-rotation[now+1]+8)%8]) {
				move_gear(now+1, -dir, 1);
			}
		}
		
		if(now != 1 && spread != 1) {	// 왼쪽으로 이동
			if(gear[now][(6-rotation[now]+8)%8] != gear[now-1][(2-rotation[now-1]+8)%8]) {
				move_gear(now-1, -dir, -1);
			}
		}
		
		rotation[now] += dir;
		if(rotation[now] < 0) {
			rotation[now] += 8;
		}
		rotation[now] %= 8;
	}
}
