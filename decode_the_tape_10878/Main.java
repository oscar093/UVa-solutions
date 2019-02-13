package decode_the_tape_10878;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		ir.readLine();
		String input = ir.readLine();
		int charNbr = 0;
		while(input.startsWith("|")) {
			if(input.charAt(2) == 'o') {
				charNbr += 64;
			}
			if(input.charAt(3) == 'o') {
				charNbr += 32;
			}
			if(input.charAt(4) == 'o') {
				charNbr += 16;
			}
			if(input.charAt(5) == 'o') {
				charNbr += 8;
			}
			if(input.charAt(7) == 'o') {
				charNbr += 4;
			}
			if(input.charAt(8) == 'o') {
				charNbr += 2;
			}
			if(input.charAt(9) == 'o') {
				charNbr += 1;
			}
			char l = (char)charNbr;
			System.out.print(l);
			charNbr = 0;
			input = ir.readLine();
		}
	}

}
