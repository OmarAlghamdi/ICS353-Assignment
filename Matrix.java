public class Matrix{
    public static long[][] iterative(long[][] a, long[][] b, int n){
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++)
                    sum += a[i][k] * b[k][j];
                c[i][j] = sum;
            }
        return c;
    }
    public static void print(long[][] c){
        for (int i = 0; i < c.length; i++) {
            for ( int j = 0; j < c[i].length; j++) {
               System.out.print(c[i][j] + "\t"); 
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int n = 5, x = 1;
        long[][] a = new long[n][n];
        long[][] b = new long[n][n];
        while(x <= 25)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    a[i][j] = x++;
        while(x <= 50)
            for (int i = 0; i < n; i++) 
                for (int j = 0; j < n; j++) 
                    b[i][j] = x++;
        long[][] c = iterative(a, b, n);
        print(c);

    }

}