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
        print(matrixPadding(c, n));
    }
    public static long[][] matrixPadding(long [][] a, int n){
        // & is a bitwise and operation opration
        if(n > 0 && (n & (n - 1)) == 0)
            return a;
        else{
            int m =1;
            while(m < n)
                // left shift
                m <<= 1;
            long[][] b = new long[m][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    b[i][j] = a [i][j];
                }
            }
            return b;
        }
    }
    // NOTE: it is not efficint to add & subtract m1..m7 during multiplcation step
    public static long[][] strassen(long[][] a, long[][] b, int n, int base){
        // TODO define base case flow
        a = matrixPadding(a, n);
        b = matrixPadding(b, n);
        int m = n/2;
        long[][] a11, a12, a21, a22;
        long[][] b11, b12, b21, b22;
        long[][] m1, m2, m3, m4, m5, m6, m7;
        long[][] c11, c12, c21, c22;
        // initalizations
        m1 = new long[m][m]; m2 = new long[m][m]; m3 = new long[m][m];
        m4 = new long[m][m]; m5 = new long[m][m]; m6 = new long[m][m];
        m7 = new long[m][m];
        a11 = new long[m][m]; a12 = new long[m][m];
        a21 = new long[m][m]; a22 = new long[m][m];
        b11 = new long[m][m]; b12 = new long[m][m];
        b21 = new long[m][m]; b22 = new long[m][m];
        c11 = new long[m][m]; c12 = new long[m][m];
        c21 = new long[m][m]; c22 = new long[m][m];
        
        // TODO partition
        // TODO multiply
        // TODO combine
        
        return null;
    }
    // TODO partition method
    // TODO combine method
    public static long[][] matrixAddition(long[][] a, long[][] b, int n){
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }
    public static long[][] matrixSubtraction(long[][] a, long[][] b, int n){
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }
}