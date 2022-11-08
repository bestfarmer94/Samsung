package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16235 {

	static int n, m, k;
	static int[][] map, a;
	static ArrayList<Tree> tree;
	static Queue<int[]> death, birth;
	static int year, answer;
	static int[] dx, dy;
	
	// 극한까지 시간을 축소해야 하는 문제인데, 이번에 깔끔한 정리를 기준으로 하다보니 메서드를 너무 많이 나눠졌다.
	// 실행에는 문제없어 넘어가기로 했다.
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		a = new int[n+1][n+1];
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<n+1; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		tree = new ArrayList<Tree>();
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			tree.add(new Tree(x, y, z));
		}
		
		map = new int[n+1][n+1];
		
		for(int i=1; i<n+1; i++) {
			Arrays.fill(map[i], 5);
		}
		
		death = new LinkedList<int[]>();
		birth = new LinkedList<int[]>();
		
		dx = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
		dy = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
		spring();
		
		sb.append(answer);
		System.out.println(sb);
		br.close();
	}

	static void spring() {
//		System.out.println("year " + year);
		if(year == k) {
			answer = tree.size();
			return;
		}
		
		int count = tree.size()-1;
		
		while(count >= 0) {
			Tree t = tree.get(count);
			
			if(map[t.x][t.y] >= t.age) {
				map[t.x][t.y] -= t.age;
				tree.get(count).age++;
//				System.out.println("tree " + t.x + " " + t.y + " " + t.age);
				if(tree.get(count).age % 5 == 0) {
					birth.add(new int[] {t.x, t.y});
				}
			}else {
				death.add(new int[] {t.x, t.y, t.age, count});
			}
			
			count--;
		}
		
		summer();
	}
	
	static void summer() {
		
		int[] d = new int[4];
		
		while(death.size() != 0) {
			d = death.poll();
//			System.out.println("death " + d[0] + " " + d[1] + " " + d[2]/2 + " " + d[3]);
			map[d[0]][d[1]] += d[2]/2;
			tree.remove(d[3]);
		}
		
		autumn();
	}
	
	static void autumn() {
		
		int[] b = new int[2];
		
		while(birth.size() != 0) {
			b = birth.poll();
			
			for(int i=0; i<8; i++) {
				int nx = b[0] + dx[i];
				int ny = b[1] + dy[i];
				
				if(nx >= 1 && ny >= 1 && nx <= n && ny <= n) {
					tree.add(new Tree(nx, ny, 1));
//					System.out.println("birth " + nx + " " + ny);
				}
			}
		}
		
		winter();
	}
	
	static void winter() {
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				map[i][j] += a[i][j];
//				System.out.print(map[i][j] + " ");
			}
//			System.out.println();
		}
//		System.out.println();
		year++;
		spring();
	}
}

class Tree implements Comparable<Tree>{
	
	int age;
	int x;
	int y;
	
	public Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}
	
	@Override
	public int compareTo(Tree o) {
		return this.age - o.age;
	}
}