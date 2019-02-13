package ecological_premium_10300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
		
		double m, a, e; 
		double sum = 0;
		
		int nbrOfTestcases = Integer.parseInt(ir.readLine());
		for(int i = 0; i < nbrOfTestcases; i++) {
			int nbrOfFarmers = Integer.parseInt(ir.readLine());
			for(int j = 0; j < nbrOfFarmers; j++) {
				String maeline  = ir.readLine();
				String[] mae = maeline.split(" ");
				m = Integer.parseInt(mae[0]);
				a = Integer.parseInt(mae[1]);
				e = Integer.parseInt(mae[2]);
				
				double result = (((double)m/a)*e)*a;
				sum += result;
			}
			System.out.println(Math.round(sum));
			sum = 0;
		}
		ir.close();
	}
}
