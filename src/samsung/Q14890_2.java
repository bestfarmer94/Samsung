package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14890_2 {		// 좀 더 깔끔한 버전, 앞 행이나 앞 열을 뒤질 필요없이 순간순간 count 하면서 확인한다.

	static int n;
	static int l;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		
		for(int i=0; i<n; i++) {
			int[] hor = map[i];
			int[] ver = new int[n];
			
			for(int j=0; j<n; j++) {
				ver[j] = map[j][i];
			}
			
			if(mountain(hor)) {
				answer++;
			}
			
			if(mountain(ver)) {
				answer++;
			}
		}
		
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}

	static boolean mountain(int[] road) {
		
		int height = road[0];
		int count = 1;
		boolean confirm = false;
		
		for(int i=1; i<n; i++) {
			
			if(height == road[i]) {
				count++;
			}else if(Math.abs(height - road[i]) > 1) {
				return false;
			}else if(height < road[i]) {
				if(count >= l) {
					count = 1;
					height = road[i];
				}else {
					return false;
				}
			}else if(height > road[i]) {
				if(confirm == false) {
					confirm = true;
					count = 1;
					height = road[i];
				}else {
					return false;
				}
			}
			//
			if(confirm && count == l) {
				count = 0;
				confirm = false;
			}
		}
		
		if(confirm) {
			return false;
		}
		
		return true;
	}
}
