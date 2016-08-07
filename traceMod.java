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
import java.io.*;
public class traceMod {
	public static void main(String[] args)throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		OutputWriter out = new OutputWriter(outputStream);
		BFS solver = new BFS();
		//int test = in.readInt();
		//for(int i = 1;i<=test;i++)
		solver.solve(1,in, out);
		out.close();
	}
}
class BFS{
	Cell path[][] ;
	Cell start;
	Cell end;
    public void solve(int testNumber, InputReader in, OutputWriter out)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String input = br.readLine();
			if(input==null) {
				break;
			}
		String[] intString = input.split(" ");
		int n = Integer.parseInt(intString[0]);
		int m = Integer.parseInt(intString[1]);
		char matrix[][] = new char[n][m];
		for (int i = 0; i < n; i++) {
           		for(int j=0;j<m;j++) {
				matrix[i][j] = in.readCharacter();
			}
		}
		int t = in.readInt();
		bfs(matrix,out);
		}
	}
	public void bfs(char[][] matrix, OutputWriter out) {		
			boolean maze[][] = new boolean[matrix.length][matrix[0].length];
			int s1=-1,s2=-1,d1=-1,d2=-1;
			Cell pred[][] = new Cell[matrix.length][matrix[0].length];
			for(int i=0;i<matrix.length;i++) {
				for(int j=0;j<matrix[0].length;j++) {
					if(matrix[i][j]=='s') {
						s1 = i;
						s2 = j;
						maze[i][j] = false;
						matrix[i][j] = 'a';
					}
					if(matrix[i][j]=='d') {
						d1 = i;
						d2 = j;
						maze[i][j] = false;
					}
					if(matrix[i][j]=='-') {
						maze[i][j] = false;
					}
					if(matrix[i][j]=='w') {
						maze[i][j] = true;
					}
				}
			}
			int[][] levelMatrix = new int[maze.length][maze[0].length];
			for (int i = 0; i < maze.length; ++i)
			for (int j = 0; j < maze[0].length; ++j)
			{
				levelMatrix[i][j] = maze[i][j] == true ? -1 : 0;
			}
			LinkedList < Cell > queue = new LinkedList < Cell >();
			 start = new Cell(s1, s2);
			 end = new Cell(d1, d2);
			queue.add(start);
			levelMatrix[start.row][start.col] = 1;
			while (!queue.isEmpty())
			{
				Cell cell = queue.poll();
				if (cell == end)
				break;
				int level = levelMatrix[cell.row][cell.col];
				Cell[] nextCells = new Cell[8];
				nextCells[7] = new Cell(cell.row+1, cell.col + 1);//se
				nextCells[6] = new Cell(cell.row+1, cell.col - 1);//sw
				nextCells[5] = new Cell(cell.row-1, cell.col + 1);//ne
				nextCells[4] = new Cell(cell.row-1, cell.col - 1);//nw
				nextCells[3] = new Cell(cell.row, cell.col - 1);
				nextCells[2] = new Cell(cell.row - 1, cell.col);
				nextCells[1] = new Cell(cell.row, cell.col + 1);
				nextCells[0] = new Cell(cell.row + 1, cell.col);
				for (Cell nextCell : nextCells)
				{
					if (nextCell.row < 0 || nextCell.col < 0)
					continue;
					if (nextCell.row == maze.length|| nextCell.col == maze[0].length)
					continue;
					if (levelMatrix[nextCell.row][nextCell.col] == 0)
					{
						queue.add(nextCell);
						levelMatrix[nextCell.row][nextCell.col] = level + 1;
						pred[nextCell.row][nextCell.col] = cell;
					}
				}
			}
			LinkedList < Cell > path = new LinkedList < Cell >();
			Cell cell = end;
			while (!cell.equals(start))
			{
				path.push(cell);
				int level = levelMatrix[cell.row][cell.col];
				Cell[] nextCells = new Cell[8];
				nextCells[0] = new Cell(cell.row + 1, cell.col);
				nextCells[1] = new Cell(cell.row, cell.col + 1);
				nextCells[2] = new Cell(cell.row - 1, cell.col);
				nextCells[3] = new Cell(cell.row, cell.col - 1);
				nextCells[7] = new Cell(cell.row+1, cell.col + 1);//se
				nextCells[6] = new Cell(cell.row+1, cell.col - 1);//sw
				nextCells[5] = new Cell(cell.row-1, cell.col + 1);//ne
				nextCells[4] = new Cell(cell.row-1, cell.col - 1);//nw
				for (Cell nextCell : nextCells)
				{
					if (nextCell.row < 0 || nextCell.col < 0)
					continue;
					if (nextCell.row == maze.length
					|| nextCell.col == maze[0].length)
					continue;
					if (levelMatrix[nextCell.row][nextCell.col] == level - 1)
					{
						cell = nextCell;
						break;
					}
				}
			}
			for(Cell c : path) {
				if(c.equals(end))
					continue;
				matrix[c.row][c.col] = 'a';
				//out.printLine(c);
			}
			for(int i=0;i<matrix.length;i++) {
				for(int j=0;j<matrix[0].length;j++) {
					out.print(matrix[i][j]+" ");
				}
				out.printLine();
			}		
		
	}
	
}
  class Cell {
	public int row;
	public int col;
	public Cell prev;
  public Cell(int row, int column)
  {
   this.row = row;
   this.col = column;
  }

  @Override
  public boolean equals(Object obj)
  {
   if (this == obj)
    return true;
   if ((obj == null) || (obj.getClass() != this.getClass()))
    return false;
   Cell cell = (Cell) obj;
   if (row == cell.row && col == cell.col)
    return true;
   return false;
  }

  @Override
  public String toString()
  {
   return "(" + row + "," + col + ")";
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
