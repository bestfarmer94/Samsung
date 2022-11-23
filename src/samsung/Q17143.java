package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q17143 {

	static int r, c, m;
	static int[][] map;
	static ArrayList<Shark> shark;
	static int[] dx;
	static int[] dy;
	static int fishing;
	static ArrayList<Integer> eat;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[r+1][c+1];
		shark = new ArrayList<Shark>();
		fishing = -1;
		
		// 적어놓은 이유는 Shark[] 의 형태로 놓을 수 있다는 것이 유용해 보여서.
//		static Shark[] sharks;
//		sharks[i] = new Shark(i, x, y, speed, dir, size);
//		Shark shark = sharks[grid[y][x]];
//		ans += shark.size;
//      sharks[shark.num] = new Shark(shark.num, 0, 0, 0, 0, 0);
//		Shark shark = sharks[i];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			map[a][b] = e;
			
			if(b == 1) {
				if(fishing == -1) {
					fishing = i;
				}else {
					if(a < shark.get(fishing).x) {
						fishing = i;
					}
				}
			}
			
			shark.add(new Shark(a, b, c, d, e));
		}
		
		eat = new ArrayList<Integer>();
		if(fishing != -1) {
			eat.add(fishing);
		}
		
		dx = new int[] {0, -1, 1, 0, 0};
		dy = new int[] {0, 0, 0, 1, -1};
		
		for(int i=1; i<c+1; i++) {
			move_shark_fishing(i);
		}
		
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}

	static void move_shark_fishing(int food_y) {
		
//		System.out.println(food_y + "초");
//		if(fishing != -1)
//		System.out.println(shark.get(fishing).power);
//		for(int i=1; i<r+1; i++) {
//			for(int j=1; j<c+1; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		if(fishing != -1) {
			answer += shark.get(fishing).power;
		}
		
		for(int i=0; i<eat.size(); i++) {
			int number = eat.get(i);
			map[shark.get(number).x][shark.get(number).y] = 0;
			shark.remove(number);
		}
		
		fishing = -1;
		
		Collections.sort(shark);
		
		if(food_y == c) {
			return;
		}
		
		for(int i=0; i<shark.size(); i++) {	// 이동
			
			Shark s = shark.get(i);
			map[s.x][s.y] = 0;	// map 초기화
			
			if(s.dir <= 2) {	// 행이동.
				s.speed %= 2*(r-1);
				int nx = s.x + dx[s.dir] * s.speed;
				
				if(nx >= 1 && nx <= r) {
					shark.get(i).x = nx;
				}else if(nx < 1) {
					nx = 2 - nx;
					
					if(nx < r) {
						shark.get(i).x = nx;
						shark.get(i).dir = 2;
					}else {
						nx = r - (nx - r);
						shark.get(i).x = nx;
						shark.get(i).dir = 1;
					}
					
				}else {	// nx > r
					nx = r - (nx - r);
					
					if(nx >= 1) {
						shark.get(i).x = nx;
						shark.get(i).dir = 1;
					}else {
						nx = 2 - nx;
						shark.get(i).x = nx;
						shark.get(i).dir = 2;
					}
				}
				
			}else {	// 열이동.
				s.speed %= 2*(c-1);
				int ny = s.y + dy[s.dir] * s.speed;
				
				if(ny >= 1 && ny <= c) {
					shark.get(i).y = ny;
				}else if(ny < 1) {
					ny = 2 - ny;
					
					if(ny < c) {
						shark.get(i).y = ny;
						shark.get(i).dir = 3;
					}else {
						ny = c - (ny - c);
						shark.get(i).y = ny;
						shark.get(i).dir = 4;
					}
					
				}else {	// ny > c
					ny = c - (ny - c);
					
					if(ny >= 1) {
						shark.get(i).y = ny;
						shark.get(i).dir = 4;
					}else {
						ny = 2 - ny;
						shark.get(i).y = ny;
						shark.get(i).dir = 3;
					}
				}
			}
		}
		
		eat.clear();
		
		for(int i=0; i<shark.size(); i++) {	// 약육강식
			Shark s = shark.get(i);
			
			if(map[s.x][s.y] == 0) {
				map[s.x][s.y] = s.power;
				
				if(s.y == food_y + 1) {
					if(fishing == -1) {
						fishing = i;
					}else if(s.x < shark.get(fishing).x){
						fishing = i;
					}
				}
			}else {
				eat.add(i);
			}
		}
		
		if(fishing != -1) {
			eat.add(fishing);
		}
		Collections.sort(eat, Collections.reverseOrder());
	}
}


class Shark implements Comparable<Shark>{
	
	int x;
	int y;
	int speed;
	int dir;
	int power;
	
	public Shark(int r, int c, int s, int d, int z) {
		
		this.x = r;
		this.y = c;
		this.speed = s;
		this.dir = d;
		this.power = z;
	}

	@Override
	public int compareTo(Shark o) {
		
		return o.power - this.power;
	}
}
