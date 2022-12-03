package samsung;

import java.util.Arrays;

public class asd {

	public static void main(String[] args) {

		String str = "Zbcdefg";
		int[] cal = new int[str.length()];
		
		for(int i=0; i<str.length(); i++) {
			cal[i] = str.charAt(i)  - 'A';	// 숫자 A면 0 이고 그거보다 크면 숫자로 변환
		}
		//
		Arrays.sort(cal);
		
		String answer = "";
		
		for(int i=cal.length-1; i>=0; i--) {
			answer += (char)(cal[i] + 'A');	// 다시 더해줌.
		}
		
		System.out.println(answer);
	}
}
