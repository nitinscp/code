import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class test {
	public static void main(String args[])throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int array[] =new int[1000000];
		List<Integer> l[];	
	       	l= new ArrayList[1000000];
		for(int i=0;i<1000001;i++) {
			l[i] = new ArrayList<Integer>();
		}
		int cnt =0;
		while(true) {
			String line = br.readLine();
			if(line.startsWith("Q")) {
			
			}
			if(line.startWith("C")) {
				int x=0;
				int y=0;
				String[] s = line.split(" ");
				x = Integer.parseInt(s[1]);
				y = Integer.parseInt(s[2]);
				l[cnt].contains(x);
			}
			if(line.equals("-1")) {
				break;
			}	
			

		
	}
}
