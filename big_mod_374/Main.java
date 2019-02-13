package big_mod_374;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		Main main = new Main();

		int b, p, m;

		while (ir.ready()) {
			b = Integer.parseInt(ir.readLine());
			p = Integer.parseInt(ir.readLine());
			m = Integer.parseInt(ir.readLine());
			System.out.println(main.calculate(b, p, m));
			ir.readLine();
		}
	}
	
	public int calculate(int b, int p, int m) {
		if(p == 0) {
			return 1;
		}
		if(p % 2 == 0) {
			int val = calculate(b, p/2, m);
			return (val*val) % m;
		}else if(p%2 == 1) {
			int x,y;
			x = b%m;
			y = calculate(b,p-1,m);
			return (x*y)%m;
		}
		return -1;
	}
}
