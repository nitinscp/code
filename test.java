import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class test {
	public static void main(String args[])throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		char a = '1';
		char b = '1';
		if((b+1)==a)
		System.out.println("YES");
		else 
		System.out.println("NO");
		String s = new String("NItin");
		String p = s.substring(0,-1);
	        String l = s.substring(4,5);
	        System.out.println(p);
	        System.out.println(l);
	}
}
