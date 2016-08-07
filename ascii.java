import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ascii {
	public static void main(String args[])throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		while(test-->0) {
			char[] s = br.readLine().toCharArray();
			int[] array = new int[27];
			int val = 0;
			boolean flag = true;
			for(int i = 0;i<s.length;i++) {
				val = s[i]-96;
				array[val]++;
			}
			for(int i=1;i<=26;i++) {
				if(array[i]==i||array[i]==0) {
					continue;
				}
				else {
					flag = false;
				}
			}
			if(flag) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
	}
}
