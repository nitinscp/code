import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

 class AA {
	//Chef and Robots Competition
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		
		while(test-->0) {
			String[] line1 = br.readLine().split(" ");
		int n = Integer.parseInt(line1[0]);
		int m = Integer.parseInt(line1[1]);
		int[][] array = new int[n][m];
		int[][] soln = new int[n][m];
		int[][] soln1 = new int[n][m];
		int mxVal = m*n+1;
		for(int i=0;i<n;i++) {
			String line[] = br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				array[i][j] = Integer.parseInt(line[j]);
				if(array[i][j] == 1) {
					soln[i][j] = -1;
					soln1[i][j] = -1;
				}
			}
		}
		int[][][] meetFlag = new int[n][m][2];
		int[][] visited1 = new int[n][m];
		int[][] visited2 = new int[n][m];
		int K1 = Integer.parseInt(line1[2]);
		int K2 = Integer.parseInt(line1[3]);
		meetFlag[0][0][0] = 1;
		meetFlag[0][m-1][1] = 1;
		int ans = -1;
		boolean first = false;
		boolean flag = false;
		int r1 = 0, r2 = 0;
		Queue<Cell> q1 = new LinkedList<Cell>();
		Queue<Cell> q2 = new LinkedList<Cell>();
		Cell start = null;
		Cell end = null;
		if(K1>=K2) {
			first = true;
			soln[0][0] = 1;
			start = new Cell(1,1);
			end = new Cell(1,m);
			q1.add(start);
			q2.add(end);
			
			r1 = K1;
			r2 = K2;
		}
		else {
			soln[0][m-1] = 1;
			start = new Cell(1,m);
			end = new Cell(1,1);
			q1.add(start);
			q2.add(end);
			r1 = K2;
			r2 = K1;
		}
		while( !q1.isEmpty() ) {
				Cell currentCell = q1.remove();
				int level = soln[currentCell.x-1][currentCell.y-1];
				ArrayList<Cell> cells = findRange(currentCell.x,currentCell.y,r1,array,visited1);
				for (Iterator<Cell> iterator = cells.iterator(); iterator.hasNext(); ) {
					Cell c = iterator.next();
					if((c.equals(end))) {
						soln[c.x-1][c.y-1] = level + 1;
						continue;
					}
					if(soln[c.x-1][c.y-1] == 0) {
						q1.add(c);
						soln[c.x-1][c.y-1] = level + 1;
					}
				}/*
				for(Cell c : cells) {
					if((c.x==1&&c.y==1)||(c.x==1&&c.y==m)) {
						soln[c.x-1][c.y-1] = level + 1;
						continue;
					}
					if(soln[c.x-1][c.y-1] == 0) {
						q1.add(c);
						soln[c.x-1][c.y-1] = level + 1;
					}
					
				}*/
		}
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				System.out.print(soln[i-1][j-1]+" ");
			}
			System.out.println();
		}
	/*	 LinkedList < Cell > path = new LinkedList < Cell >();
		 Cell point = end;
		 while(!point.equals(start)) {
			 path.push(point);
			 int level = soln[point.x-1][point.y-1];
			 ArrayList<Cell> cells = findRange(point.x,point.y,r1,array,visited1);
			 for(Cell c : cells) {
				 if (soln[c.x-1][c.y-1] == level - 1)
				    {
				     	point = c;
				     	break;
				    }
			 }
		 }*/
		ans = soln[end.x-1][end.y-1];
		if ( ans == 0 ) {
			System.out.println("-1");
		}
		else  {
			System.out.println( ans / 2 );
		}
		
		}
	}
	public static ArrayList< Cell > findRange( int x, int y, int K, int[][] matrix,int[][] visited) {
		ArrayList< Cell > list = new ArrayList< Cell >();
		int r1 = Math.max( 1, x-K );
		int r2 = Math.min( matrix.length, K+x );
		int c1 = Math.max( 1, y-K );
		int c2 = Math.min( matrix[0].length, K+y );
		for( int i = r1; i <= r2; i++) {
			for( int j = c1; j <= c2; j++) {
				int val = Math.abs(x-i) + Math.abs(y-j);
				if(val<=K && visited[i-1][j-1]==0) {
					if(matrix[i-1][j-1]==0&&val>0)
					list.add(new Cell(i,j));
				}
			}
		}
		return list;
	}
	public static class Cell {
		int x,y;
		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
