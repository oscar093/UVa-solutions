package wine_trading_in_gergovia_11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		Main main = new Main();
		
		while(ir.ready()) {
			int habitants = Integer.parseInt(ir.readLine());
			if(habitants == 0) {
				break;
			}
			String[] strline = ir.readLine().split(" ");
			int[] line = new int[strline.length];
			for(int i = 0; i < line.length;i++) {
				line[i] = Integer.parseInt(strline[i]);
			}
			System.out.println(main.letsTrade(line));
		}
	}
	
	public long letsTrade(int[] line) {
		long sum = 0, work = 0;
		
		for(int i = 0; i < line.length; i++) {
			sum += line[i];
			if(sum > 0) {
				work += sum;
			}else {
				work += -sum;
			}
		}
		return work;
	}
}
