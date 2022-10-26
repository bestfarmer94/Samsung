package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14889 {

	static int n;
	static int[][] s;
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		s = new int[n+1][n+1];
		StringTokenizer st;
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<n+1; j++) {
				s[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] team1 = new int[n/2];
		min = Integer.MAX_VALUE;
		dfs(0, 1, team1);
		
		sb.append(min);
		System.out.println(sb);
		br.close();
	}
	
	static void dfs(int depth, int start, int[] team1) {
		
		if(depth == n/2) {
			min = Math.min(min, power(team1));
			return;
		}
		
		for(int i=start; i<n+1; i++) {
			team1[depth] = i;
			dfs(depth+1, i+1, team1);
		}
	}
	
	static int power(int[] team1) {
		
		int power1 = 0;
		int power2 = 0;
		int[] team2 = new int[n/2];
		
		int count = 0;
		for(int i=1; i<n+1; i++) {
			if(count == n/2) {
				break;
			}
			
			int confirm = 0;
			for(int j=0; j<n/2; j++) {
				if(team1[j] == i) {
					confirm++;
					break;
				}else if(team1[j] > i) {
					break;
				}
			}
			
			if(confirm == 0) {
				team2[count] = i;
				count++;
			}
		}
		
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<n/2; j++) {
				power1 += s[team1[i]][team1[j]];
				power2 += s[team2[i]][team2[j]];
			}
		}
		
//		for(int i=0; i<team1.length; i++) {
//			System.out.print(team1[i] + " ");
//		}
//		System.out.println();
//		
//		System.out.println(power1 +" " + power2);
		
		return Math.abs(power1 - power2);
	}
}
