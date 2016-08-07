import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
 
class A {
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
            int[][] visited = new int[n][m];
			int[][] visit = new int[n][m];
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
            int K1 = Integer.parseInt(line1[2]);
            int K2 = Integer.parseInt(line1[3]);
 
            int ans = -1;
            boolean first = false;
            boolean flag = false;
            int r1 = 0, r2 = 0;
            Queue< Cell > q1 = new LinkedList< Cell >();
            Queue< Cell > q2 = new LinkedList< Cell >();
            Cell start = new Cell( 1,1 );
            visited[0][0] = 1;
			visit[0][m-1] = 1;
            Cell end = new Cell( 1,m );
            q1.add( start ) ;
            q2.add( end ) ;
 
            soln[0][0] = 0;
            soln1[0][m-1] = 0;
            while( !q1.isEmpty() ) {
                Cell currentCell = q1.remove();
                int level = soln[currentCell.x-1][currentCell.y-1];
                ArrayList<Cell> cells = findRange(currentCell.x,currentCell.y,K1,array);
                for (Iterator<Cell> iterator = cells.iterator(); iterator.hasNext(); ) {
                    Cell c = iterator.next();
 
                    if(visited[c.x-1][c.y-1] == 0 && soln[c.x-1][c.y - 1] != -1) {
                        q1.add(c);
						visited[c.x-1][c.y-1] = 1;
                        soln[c.x-1][c.y-1] = level + 1;
                    }
                }
            }
            while( !q2.isEmpty() ) {
                Cell currentCell = q2.remove();
                int level = soln1[currentCell.x-1][currentCell.y-1];
                ArrayList<Cell> cells = findRange(currentCell.x,currentCell.y,K2,array);
                for (Iterator<Cell> iterator = cells.iterator(); iterator.hasNext(); ) {
                    Cell c = iterator.next();
 
                    if(soln1[ c.x-1 ][ c.y-1 ] != -1 && visit[ c.x-1 ][ c.y-1 ] == 0) {
 
                        soln1[ c.x-1 ][ c.y-1 ] = level + 1;
						visit[ c.x-1 ][ c.y-1 ] = 1;
                        q2.add( c );
 
						
                    }
                }
 
            }
 
			/* LinkedList < Cell > path = new LinkedList < Cell >();
			 Cell cell = end;
			 while( !cell.equals( start ) ) {
				 path.push(cell);
				 int level = soln[cell.x-1][cell.y-1];
				 ArrayList<Cell> cells = findRange(cell.x, cell.y,K1, array);
				 for (Iterator<Cell> iterator = cells.iterator(); iterator.hasNext();) {
						Cell nextCell = iterator.next();
						if( soln[nextCell.x-1][nextCell.y-1] == level-1) {
							cell = nextCell;
							break;
						}
				 }
			 }*/
			 /* LinkedList < Cell > path1 = new LinkedList < Cell >();
			  Cell cell1 = start;
			 while( !cell1.equals( end ) ) {
				 path1.push(cell);
				 int level = soln[cell1.x-1][cell1.y-1];
				 ArrayList<Cell> cells = findRange(cell.x, cell.y,K2, array);
				 for (Iterator<Cell> iterator = cells.iterator(); iterator.hasNext();) {
						Cell nextCell = iterator.next();
						if( soln1[nextCell.x-1][nextCell.y-1] == level-1) {
							cell1 = nextCell;
							break;
						}
				 }
			 }*/
			 /*System.out.println("path1");
			 for(Cell c: path) {
				 System.out.println(c.x+"  "+c.y);
			 }*/
			/* System.out.println("path2");
			 for(Cell c: path1) {
				 System.out.println(c.x+"  "+c.y);
			 }*/
            /*for(int i=1;i<=n;i++) {
                for(int j=1;j<=m;j++) {
                    System.out.print(soln[i-1][j-1]+" ");
                }
                System.out.println();
            }
            for(int i=1;i<=n;i++) {
                for(int j=1;j<=m;j++) {
                    System.out.print(soln1[i-1][j-1]+" ");
                }
                System.out.println();
            }*/
        int minVal = Integer.MAX_VALUE;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				if( visit[ i - 1][ j - 1] == 1 && visited[ i - 1][ j - 1] == 1) {
					flag = true;
					int val = Math.max(soln1[ i - 1][ j - 1],soln[ i - 1][ j - 1]);
					if(val<minVal) {
						minVal = val;
						ans = val;
					}
				}
			}
		}
 
 
            //ans = soln[end.x-1][end.y-1];
            if ( !flag ) {
                System.out.println("-1");
            }
            else  {
                System.out.println( ans );
            }
 
        }
    }
    public static ArrayList< Cell > findRange( int x, int y, int K, int[][] matrix) {
        ArrayList< Cell > list = new ArrayList< Cell >();
        int r1 = Math.max( 1, x-K );
        int r2 = Math.min( matrix.length, K+x );
        int c1 = Math.max( 1, y-K );
        int c2 = Math.min( matrix[0].length, K+y );
        for( int i = r1; i <= r2 ; i++) {
            for( int j = c1; j <= c2; j++) {
                if(i == x && j == y)
                    continue;
                int val = Math.abs( x-i ) + Math.abs( y-j );
                if( val <= K ) {
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
        public String toString() {
            return "(" + x + "," + y + ")";
        }
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if ((obj == null) || (obj.getClass() != this.getClass()))
                return false;
            Cell cell = (Cell) obj;
            if (x == cell.x && y == cell.y)
                return true;
            return false;
        }
    }
}