package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15686 {

	static int n;
	static int m;
	static int[][] map;
	static int min;
	static ArrayList<int[]> home;
	static ArrayList<int[]> chicken;
	static boolean[] open;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		home = new ArrayList<int[]>();
		chicken = new ArrayList<int[]>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					home.add(new int[] {i, j});
				}
				
				if(map[i][j] == 2) {
					chicken.add(new int[] {i, j});
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		open = new boolean[chicken.size()];
		
		select_chicken(0, 0);
		
		sb.append(min);
		System.out.println(sb);
		br.close();
	}
	
	static void select_chicken(int depth, int start) {
		
		if(depth == m) {
			int distance = 0;
			
			for(int i=0; i<home.size(); i++) {
				int temp = Integer.MAX_VALUE;
				
				for(int j=0; j<chicken.size(); j++) {
					if(open[j]) {
						temp = Math.min(temp, Math.abs(home.get(i)[0] - chicken.get(j)[0])
								+ Math.abs(home.get(i)[1] - chicken.get(j)[1]));
					}
				}
				
				distance += temp;
			}
			
			if(distance < min) {
				min = distance;
			}
			
			return;
		}
		
		for(int i=start; i<chicken.size(); i++) {
			open[i] = true;
			select_chicken(depth+1, i+1);
			open[i] = false;
		}
	}

	// bfs 방식은 메모리를 너무 먹어서 안되더라.
//	static int delivery(int x, int y) {
//		
//		int distance = 0;
//		q.add(new int[] {x, y, 0});
//		int[] house = new int[3];
//		
//		while(distance == 0) {
//			house = q.poll();
//			
////			System.out.println(house[0] + " " + house[1] + " " + house[2]);
//			
//			if(clone[house[0]][house[1]] == 2) {
//				distance = house[2];
//				break;
//			}
//			
//			for(int i=0; i<4; i++) {
//				int nx = house[0] + dx[i];
//				int ny = house[1] + dy[i];
//				
//				if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
//					q.add(new int[] {nx, ny, house[2] + 1});
//				}
//			}
//		}
//		
//		q.clear();
//		return distance;
//	}
	
}
