import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.InputStream;
import java.util.*;
public class trace {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		OutputWriter out = new OutputWriter(outputStream);
		BFS solver = new BFS();
		int test = in.readInt();
		for(int i = 1;i<=test;i++)
		solver.solve(1,in, out);
		out.close();
	}
}
class BFS{
   	int[] dist;
	int[] pred;
    	List<Integer> adj[];
	int[] solution;
	public int bfs(int s,int size) {
		dist=new int[size];
		int count =0;
		pred=new int[size];
		for(int i=0;i<size;i++) {
			dist[i]=Integer.MAX_VALUE;
			pred[i]=-1;
		}
		int[] color=new int[size];
		for(int i=0;i<size;i++) {
			color[i]=0;			
		}
		int u=0;
		color[s]=1;
		dist[s]=0;
		pred[s]=-1;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		while(!queue.isEmpty()) {
			u=queue.remove();
			for(int v : adj[u]) {
				if(color[v]==0) {
					color[v]=1;
					dist[v]=dist[u]+1;
					queue.add(v);
					pred[v]=u;
				}
			}
			color[u]=2;
		}
		for(int i=0;i<size;i++) { 
			if(color[i]==2&&solution[i]==1) {
				count++;
			}
		}
			return count;
	}	
	
    public void solve(int testNumber, InputReader in, OutputWriter out) {
 		int n = in.readInt();	
		adj = new ArrayList[n*n];
		for(int i=0;i<n*n;i++) {
			adj[i] = new ArrayList<Integer>();
		}	
		char matrix[][] = new char[n][n];
		for (int i = 0; i < n; i++) {
           		for(int j=0;j<n;j++) {
				matrix[i][j] = in.readCharacter();
			}
		}
		int l=-1,r=-1,u=-1,d=-1,c=-1;	
		for(int i=1;i<n-1;i++) {
				for(int j=1;j<n-1;j++) {
				c = n*i+j;
				u = n*(i-1)+j;
				d = n*(i+1)+j;
				r = c+1;
				l = c-1;
				if(matrix[i][j]=='O'&&matrix[i-1][j]=='O') {
					adj[c].add(u);
					adj[u].add(c);
				}
				if(matrix[i][j]=='O'&&matrix[i+1][j]=='O') {
					adj[c].add(d);
					adj[d].add(c);
				}
				if(matrix[i][j]=='O'&&matrix[i][j-1]=='O') {
					adj[c].add(l);
					adj[l].add(c);
				}
				if(matrix[i][j]=='O'&&matrix[i][j+1]=='O') {
					adj[c].add(r);
					adj[r].add(c);
				}
			}
		}
		int t=n-1;
		for(int i=0;i<n-1;i++) {
			if(matrix[0][i]=='O'&&matrix[0][i+1]=='O') {
				adj[i].add(i+1);
				adj[i+1].add(i);
			}
			if(matrix[t][i]=='O'&&matrix[t][i+1]=='O') {
				adj[(n*t)+i].add((n*t)+i+1);
				adj[(n*t)+i+1].add((n*t)+i);
			}
			if(matrix[i][0]=='O'&&matrix[(i+1)][0]=='O') {
				adj[i*n].add((i+1)*n);
				adj[(i+1)*n].add(i*n);
			}
			if(matrix[i][t]=='O'&&matrix[(i+1)][t]=='O') {
				adj[i*n+t].add((i+1)*n+t);
				adj[(i+1)*n+t].add(i*n+t);
			}
		}
		int test = in.readInt();
		solution = new int[n*n];
		int m = test;
		while(test-->0) {
			int x = in.readInt()-1;
			int y = in.readInt()-1;
			solution[x*n+y] = 1;
		}	
		int val = bfs(0,n*n);		
		//out.printLine(val);
		if(m==val) {	
			out.printLine("Yes");
		}
		else 
			out.printLine("No");

		
	}
}
class InputReader {
 
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;
 
	public InputReader(InputStream stream) {
		this.stream = stream;
	}
 
	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}
 
	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}
 public long readLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}
 
	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return isWhitespace(c);
	}
 
	public static boolean isWhitespace(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}
 
	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}
	public char readCharacter() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}
	public String next() {
		return readString();
	}
}
 
class OutputWriter {
	private final PrintWriter writer;
 
	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}
 
	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}
 
	public void print(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}
 
    public void printLine(Object...objects) {
		print(objects);
		writer.println();
	}
 
	public void close() {
		writer.close();
	}
 
}
