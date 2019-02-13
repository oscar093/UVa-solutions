package not_so_mobile_839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));
	public ArrayList<String[]> mobs = new ArrayList<String[]>();
	public int counter = 0;
	
	public static void main(String[] args) throws IOException {
		Main m = new Main();
		int nbrOfCases = Integer.parseInt(m.ir.readLine());
		m.ir.readLine();
		boolean isData = true;
		for(int i = 0; i < nbrOfCases; i++) {
			m.mobs.clear();
			m.counter = -1;
			while(isData && m.ir.ready()) {
				
				String[] mob = m.ir.readLine().split(" ");
				
				if(mob.length > 1) {
					m.mobs.add(mob);
				}else {
					isData = false;
				}
			}
			if(m.valEgdu(m.mobs.get(m.incrCount()), m.counter ) > 0) {
				System.out.println("YES");
			}else {
				System.out.println("NO");	
			}
			if(i < nbrOfCases-1) {
				System.out.println();
			}
			isData = true;
		}
	}
	
	public int valEgdu(String[] mob, int currentLine) {
		int wL, wR, dL, dR;
		dL = Integer.parseInt(mob[1]);
		dR = Integer.parseInt(mob[3]);
		if(Integer.parseInt(mob[0]) < 1) {
			wL = valEgdu(mobs.get(this.incrCount()), this.counter);
		}else {
			wL = Integer.parseInt(mob[0]);
		}
		if(Integer.parseInt(mob[2]) < 1) {
			wR = valEgdu(mobs.get(this.incrCount()), this.counter);
		}else {
			wR = Integer.parseInt(mob[2]);
		}
		boolean equlibrium = (wL*dL) == (wR*dR);
		if(equlibrium) {
			return wL+wR;
		}else {
			return -1;
		}	
	}
	
	public int incrCount() {
		counter++;
		return counter;
	}
}