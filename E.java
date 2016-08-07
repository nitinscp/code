import java.io.*;
import java.util.*;
public class E{
	static Cell path[][] ;
	static Cell start;
	static Cell end;
    public static void main(String[] args)throws IOException {
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
				String t = br.readLine();
				StringBuffer str = new StringBuffer(t);
				int c = 0;
				while(c<str.length()) {
					if(str.charAt(c)==' ') {
						str.deleteCharAt(c);
					}
					c++;
				}
				matrix[i] = str.toString().toCharArray();
           		//for(int j=0;j<m;j++) {
					
				//matrix[i][j] = in.readCharacter();
			
		}
		int t = Integer.parseInt(br.readLine());
		bfs(matrix);
		}
	}
	public static void bfs(char[][] matrix) {		
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
					System.out.print(matrix[i][j]);
				}
				System.out.println();
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
