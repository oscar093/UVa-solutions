package all_in_all_10340;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		Main main = new Main();

		
		while(ir.ready()) {
			String[] strinput = ir.readLine().split(" ");
			if(main.validate(strinput[0], strinput[1])) {
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
		}
	}
	
	public boolean validate(String s, String t) {
		int i = 0, j = 0;
		while(i < s.length()) {
			if(j == t.length()) {
				return false;
			}
			if(s.charAt(i) == t.charAt(j)) {
				i++;
				j++;
			}else {
				j++;
			}
		}
		return true;
	}
}
