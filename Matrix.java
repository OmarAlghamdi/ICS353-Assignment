public class Matrix{
    public static void main(String[] args) {
        int n = 5, x = 1;
        long[][] a = new long[n][n];
        long[][] b = new long[n][n];       // Defined two test nxn test matrices - asaad 
        while(x <= 25)      
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) //populate matrix a until (n^2)th element - asaad
                    a[i][j] = x++;
        while(x <= 50)          // same
            for (int i = 0; i < n; i++) 
                for (int j = 0; j < n; j++) 
                    b[i][j] = x++;

        //long[][] c = iterative(a, b, n);    // Apply the iterative multiplication - asaad
        print(a);
        System.out.println("------xxxxxxxxxx------");
        print(b);
        System.out.println("======================")
        long[][] strassenc = strassen(a, b, 4);  // Apply strassen - asaad
        print(strassenc);
       // print(matrixPadding(c, n)); // No need!
    
    }

    public static long[][] iterative(long[][] a, long[][] b, int n){
        long[][] c = new long[n][n];    // resultant matrix.. ok. - asaad
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++)     
                    sum += a[i][k] * b[k][j];   
                c[i][j] = sum;
            }
            /*
            Analysis:
            Example multiplication of 1x3 by 3x1 yields 1x1
                         [1]     
            [1, 2, 3] *  [2]    = [1+4+9] = [14]
                         [3]
            *Resultant matrix will have the same number of rows as the first and 
            same number of columns as the second.
            * Algorithm looks good! - asaad
            */
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

    public static long[][] matrixPadding(long [][] a, int n){
        // & is a bitwise and operation opration
        /*Analysis - asaad:
        Let's consider n=5, n>0 and 101 AND 100 yields 1.
        Let's consider n=4, n>0 and 100 AND 011 yields 0
        Oh ok good. Very hacky and edgy way to figure out whether a number is odd. cool.
        */
        if(n > 0 && (n & (n - 1)) == 0) 
            return a;
        else{
            int m =1;   // A variable m, the new padded matrix size - asaad
            while(m < n)    // As long as m is smaller than n, keep multiplying by 2. - asaad
                // left shift
                m <<= 1;
            long[][] b = new long[m][m];    // the new padded matrix
            for (int i = 0; i < n; i++) {   // then we copy over the elements.. OK. - asaad
                for (int j = 0; j < n; j++) {
                    b[i][j] = a[i][j];  
                }
            }
            return b;
        }
    }
    // NOTE: it is not efficint to add & subtract m1..m7 during multiplcation step
    public static long[][] strassen(long[][] a, long[][] b, int base){
        // TODO define base case flow
        int n = a.length;
        if(n < base){
            return iterative(a, b, n);
            

        }

        a = matrixPadding(a, n);    // Padding applied if needed to a and b.
        b = matrixPadding(b, n);

        int m = n/2;        // Divide the array into two halves. 
        // NOTE: Trying n/2 yields incorrect division. MIGHT NOT NEED n as a parameter
        long[][] a11, a12, a21, a22;    
        long[][] b11, b12, b21, b22;
        long[][] m1, m2, m3, m4, m5, m6, m7;
        long[][] c11, c12, c21, c22;
        long[][] c;

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
        c = new long[n][n];

        
        // TODO partition into 4 Regions, A B C
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
            a11[i][j] = a[i][j];
            a12[i][j] = a[i][(m)+j];
            a21[i][j] = a[(m)+i][j];
            a22[i][j] = a[(m)+i][(m)+j];
            }

        }
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
            b11[i][j] = b[i][j];
            b12[i][j] = b[i][(m)+j];
            b21[i][j] = b[(m)+i][j];
            b22[i][j] = b[(m)+i][(m)+j];
            }

        }

        




        //// DIVIDE STEP
        m1 = strassen(matrixAddition(a11, a22), matrixAddition(b11, b22), base);
        m2 = strassen(matrixAddition(a21, a22), b11, base);
        m3 = strassen(a11, matrixSubtraction(b12, b22), base);
        m4 = strassen(a22, matrixSubtraction(b21, b11), base);
        m5 = strassen(matrixAddition(a11, a12), b22, base);
        m6 = strassen(matrixSubtraction(a21, a11), matrixAddition(b11, b12), base);
        m7 = strassen(matrixSubtraction(a12, a22), matrixAddition(b21, b22), base);


        c11 = matrixSubtraction(matrixAddition(m1, m4), matrixAddition(m5, m7));
        c12 = matrixAddition(m3, m5);
        c21 = matrixAddition(m2, m4);
        c22 = matrixSubtraction(matrixAddition(m1, m3), matrixAddition(m2, m6));
        for(int i=0;i<c11.length;i++){
            for(int j=0;j<c11.length;j++){
                c[i][j] = c11[i][j];
                c[i][(m)+j] = c12[i][j];
                c[(m)+i][j] = c21[i][j];
                c[(m)+i][(m)+j] = c22[i][j];

            }
        }

        
        return c;
    }

    public static long[][] matrixAddition(long[][] a, long[][] b){
        // Straightforward matrix addition
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }
    public static long[][] matrixSubtraction(long[][] a, long[][] b){
        // Straightforward matrix subtraction
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }
}