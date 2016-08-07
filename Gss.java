import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.InputStream;
class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		OutputWriter out = new OutputWriter(outputStream);
		GS solver = new GS();
		solver.solve(1,in, out);
		out.close();
	}}
class GS {
	public void solve(int testNumber, InputReader in,OutputWriter out) {
		int n = in.readInt();
		Segment tr= new Segment();
		int[] arr = new int[n];
		for(int i=0;i<n;i++)
		arr[i] = in.readInt();
		int q = in.readInt();
		tr.initialize(arr,n);
		StringBuilder ans = new StringBuilder("");
		for(int i=0;i<q;++i) {
			int u = in.readInt();
			int v = in.readInt();
			Node result = new Node();
			result = tr.query(u-1,v-1);
			long sol = result.maxSum;
			ans.append(sol+"\n");
		}
		out.print(ans);
		ans.setLength(0);
	}
}
class Segment {
	int n;
	Node[] node;
	int[] arr;
	public void initialize(int[] arr,int n) {
		this.n = n;
		int lg2 = log(n,2);
		int size = 2*(int)Math.pow(2,lg2)-1;
		node = new Node[size];
		for(int i=0;i<size;i++)
		node[i] = new Node();
		this.arr = arr;
		build(1,0,n-1);	
	}
	public void build(int ind, int start , int end) {
		if(start == end) {
			node[ind].leaf(arr[start]);
		}
		else {
			int mid = (start+end)/2;
			build(2*ind,start,mid);
			build(2*ind+1,mid+1,end);
			node[ind].merge(node[2*ind],node[2*ind+1]);
		}
	}
	public Node query(int i, int j) {
		return minQuery(1,0,n-1,i,j);
	}
	public Node minQuery(int ind,int s, int e, int i, int j) {
		if(i<=s && j>=e) 
			return node[ind];
		int mid = (s+e)/2;
		if(j<=mid)
		return minQuery(2*ind,s,mid,i,j);
		if(i>mid)
		return minQuery(2*ind+1,mid+1,e,i,j);
		node[ind].merge(minQuery(2*ind,s,mid,i,j),minQuery(2*ind+1,mid+1,e,i,j));
		return node[ind];
	}
	public int log(int x, int base) {
		return (int) Math.ceil(Math.log(x)/Math.log(base)+1e-10);
	}}class Node {
	long prefixSum,suffixSum,sum,maxSum;
	public void leaf(long val) {
		prefixSum=suffixSum=sum=maxSum=val;
	}
	public void merge(Node left, Node right) {
		sum = left.sum+right.sum;
		prefixSum = Math.max(left.prefixSum,left.sum+right.prefixSum);
		suffixSum = Math.max(right.suffixSum,right.sum+left.suffixSum);
		maxSum = Math.max(prefixSum, Math.max(suffixSum, Math.max(left.maxSum, Math.max(right.maxSum, left.suffixSum + right.prefixSum))));;
	}
	public long value() {
		return maxSum;
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
