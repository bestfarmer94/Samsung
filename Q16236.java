package samsung;

import java.io.*;
import java.util.*;

public class Q16236 {

	static int n;
	static int[][] aquaMan;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		aquaMan = new int[n][n];
		ArrayList<Fish> fish = new ArrayList<Fish>();
		StringTokenizer st;
		
		int x = 0;
		int y = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				aquaMan[i][j] = Integer.parseInt(st.nextToken());
				if(aquaMan[i][j] == 9) {
					x = i;
					y = j;
					aquaMan[i][j] = 0;
				}else if(aquaMan[i][j] != 0) {
					fish.add(new Fish(aquaMan[i][j], i, j));
				}
			}
		}

		Collections.sort(fish);
		int baby = 2;
		int stack = 0;
		int result = 0;
		
		while(true) {
			
			if(fish.size() == 0) {
				break;
			}
			
			int min = -1;
			int number = -1;
			
			for(int i=0; i<fish.size(); i++) {
				if(fish.get(i).power >= baby) {
					break;
				}
				
				int distance = check_distance(fish.get(i).x, fish.get(i).y, x, y, baby);
				
				if(min == -1) {
					min = distance;
					number = i;
					
				}else if(min > distance){
					min = distance;
					number = i;
					
				}else {
					if(min == distance) {
						if(fish.get(i).x < fish.get(number).x) {
							min = distance;
							number = i;
							
						}else if(fish.get(i).x == fish.get(number).x) {
							if(fish.get(i).y < fish.get(number).y) {
								min = distance;
								number = i;
							}
						}
					}
				}
			}
			
			if(min == -1) {
				break;
			}else {
				result += min;
				x = fish.get(number).x;
				y = fish.get(number).y;
				fish.remove(number);
				aquaMan[x][y] = 0;
				stack++;
				
				if(stack == baby) {
					stack = 0;
					baby++;
				}
			}
		}
		
		sb.append(result);
		System.out.println(sb);
		br.close();
	}
	
	static int check_distance(int a, int b, int x, int y, int baby){
		
		visited = new boolean[n][n];
		
		int distance = -1;
		visited[x][y] = true;
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {x, y, 0});
		int[] now = new int[3];
		
		while(!q.isEmpty()) {
			now = q.poll();
			
			if(now[0] == a && now[1] == b) {
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if(baby >= aquaMan[nx][ny] && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new int[] {nx, ny, now[2]+1});
					}
				}
			}
		}
		
		if(now[0] == a || now[1] == b) {
			distance = now[2];
		}
		
		return distance;
	}
}

class Fish implements Comparable<Fish>{
	
	int power;
	int x;
	int y;
	
	public Fish(int power, int x, int y) {
		this.power = power;
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Fish o) {
		
		return this.power - o.power;
	}
}
