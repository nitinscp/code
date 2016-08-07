import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;



class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder("");
		while(true) {
			String inp = br.readLine();
			if(inp.equals("0 0"))
			break;
			String line[] = inp.split(" ");
			int p = Integer.parseInt(line[0]);
			int n = Integer.parseInt(line[1]);
			for(int i=0;i<n;i++) {
				int t = Integer.parseInt(br.readLine());
				int w = p-1;
				
			}
			String emp = br.readLine();

		}
				
	}
}
