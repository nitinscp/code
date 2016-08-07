
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

 class B {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        while( test-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String [] line1 = br.readLine().split(" ");
            String [] line2 = br.readLine().split(" ");
            int B[] = new int[N];
            int A[] = new int[N];
            int maxVal = Integer.MIN_VALUE;
            for(int i=0;i<N;i++) {
                B[i] = Integer.parseInt(line1[i]);
                A[i] = Integer.parseInt(line2[i]);
                if(A[i]>maxVal) {
                    maxVal = A[i];
                }
            }
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(maxVal);
            if(A[0]+B[0]>maxVal) {
                list.add(A[0]+B[0]);
                if( N > 1 && ( A[0] + B[1] ) > maxVal ) {
                    list.add( A[0] + B[1] );
                }
            }
            int maxValue = -1;
            int ans = -1;
            boolean found  = false;
            for(int val : list) {
                int cnt = 0;
                boolean[] dropped = new boolean[N];
                for(int i=0;i<N;i++) {
                    ArrayList<Integer> dropList = getDropListIndex(dropped,B,i);
                    for(int index : dropList) {
                        if( A[i] + B[index] == val) {
                            cnt++;
                            dropped[index] = true;
                            break;
                        }
                    }
                }
                if(cnt==N) {
                    found = true;
                    if(val>ans) {
                        ans = val;
                    }
                }
            }
            System.out.println(ans);
        }
    }

    private static ArrayList<Integer> getDropListIndex(boolean[] dropped, int[] b, int post) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = post-1; i<=post + 1; i++) {
            if(i<0 || i>=b.length) {
                continue;
            }
            if(dropped[i]) {
                list.add(i);
            }
        }
        return list;
    }

}