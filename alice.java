import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class alice {
	public static void main(String args[])throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		while(test-->0) {
			int n = Integer.parseInt(br.readLine());
			
			if(n%4==0||n%4==3||n%4==1) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
	}
}
